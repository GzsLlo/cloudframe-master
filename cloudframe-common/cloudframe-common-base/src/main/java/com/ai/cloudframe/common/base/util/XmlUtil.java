/**
 * Copyright 2019 asiainfo Inc.
 **/

package com.ai.cloudframe.common.base.util;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author: shenbin
 * @CreateDate: [2019/7/20 17:02]
 * @Version: [v1.0]
 */
public class XmlUtil {

  /**
   * map转xml map中含有根节点的键
   * @param map
   * @throws DocumentException
   * @throws IOException
   */
  public static Document map2xml(Map<String, Object> map,String rootName) throws DocumentException, IOException {
    Document doc = DocumentHelper.createDocument();
    Element root = DocumentHelper.createElement(rootName);
    doc.add(root);
    map2xml(map, root);

    return doc;
  }

  /**
   * map转xml
   * @param map
   * @param body xml元素
   * @return
   */
  private static Element map2xml(Map<String, Object> map, Element body) {
    Iterator<Map.Entry<String, Object>> entries = map.entrySet().iterator();
    while (entries.hasNext()) {
      Map.Entry<String, Object> entry = entries.next();
      String key = entry.getKey();
      Object value = entry.getValue();
      if(key.startsWith("@")){    //属性
        body.addAttribute(key.substring(1, key.length()), value.toString());
      } else if(key.equals("#text")){ //有属性时的文本
        body.setText(value.toString());
      } else {
        if(value instanceof java.util.List ){
          List list = (List)value;
          Object obj;
          for(int i=0; i<list.size(); i++){
            obj = list.get(i);
            //list里是map或String，不会存在list里直接是list的，
            if(obj instanceof java.util.Map){
              Element subElement = body.addElement(key);
              map2xml((Map)list.get(i), subElement);
            } else {
              body.addElement(key).setText((String)list.get(i));
            }
          }
        } else if(value instanceof java.util.Map ){
          Element subElement = body.addElement(key);
          map2xml((Map)value, subElement);
        } else {
          body.addElement(key).setText(value.toString());
        }
      }
      //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
    }
    return body;
  }

  public static Map xmlToMap(String xmlStr, boolean needRootKey) throws DocumentException {
    Document doc = DocumentHelper.parseText(xmlStr);
    Element root = doc.getRootElement();
    Map<String, Object> map = (Map<String, Object>) xmlToMap(root);
    if(root.elements().size()==0 && root.attributes().size()==0){
      return map;
    }
    if(needRootKey){
      //在返回的map里加根节点键（如果需要）
      Map<String, Object> rootMap = new HashMap<String, Object>();
      rootMap.put(root.getName(), map);
      return rootMap;
    }
    return map;
  }

  private static Object xmlToMap(Element element) {
    // System.out.println(element.getName());
    Map<String, Object> map = new LinkedHashMap<String, Object>();
    List<Element> elements = element.elements();
    if (elements.size() == 0) {
      map.put(element.getName(), element.getText());
      if (!element.isRootElement()) {
        return element.getText();
      }
    } else if (elements.size() == 1) {
      map.put(elements.get(0).getName(), xmlToMap(elements.get(0)));
    } else if (elements.size() > 1) {
      // 多个子节点的话就得考虑list的情况了，比如多个子节点有节点名称相同的
      // 构造一个map用来去重
      Map<String, Element> tempMap = new LinkedHashMap<String, Element>();
      for (Element ele : elements) {
        tempMap.put(ele.getName(), ele);
      }
      Set<String> keySet = tempMap.keySet();
      for (String string : keySet) {
        Namespace namespace = tempMap.get(string).getNamespace();
        List<Element> elements2 = element.elements(new QName(string,
            namespace));
        // 如果同名的数目大于1则表示要构建list
        if (elements2.size() > 1) {
          List<Object> list = new ArrayList<Object>();
          for (Element ele : elements2) {
            if(StringUtils.isEmpty(ele.getText())) continue;
            list.add(xmlToMap(ele));
          }
          map.put(string, list);
        } else {
          // 同名的数量不大于1则直接递归去
          map.put(string, xmlToMap(elements2.get(0)));
        }
      }
    }

    return map;
  }

}
