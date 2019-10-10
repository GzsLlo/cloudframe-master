/**
 * Copyright 2019 asiainfo Inc.
 **/

import com.ai.cloudframe.common.base.util.CollectionUtil;
import com.ai.cloudframe.common.base.util.XmlUtil;
import org.dom4j.DocumentException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: shenbin
 * @CreateDate: [2019/7/22 11:13]
 * @Version: [v1.0]
 */
public class TestUtils {

  private static Logger logger = LoggerFactory.getLogger(TestUtils.class);
  @Test
  public void test(){
    Map<String,Object> mapA = new HashMap(){{
      this.put("a",'a');
    }};
    Map<String,Object> mapB = new HashMap(){{
      this.put("a",'b');
      this.put("b",'b');
    }};
    List<String> l1 = new ArrayList(){{
      this.add("1");
      this.add("2");
    }};
    List<String> l2 = new ArrayList(){{
      this.add("2");
      this.add("3");
    }};
    Map<String,Object> map1 = new HashMap<>();
    Map<String,Object> map2 = new HashMap<>();
    map1.put("1",1);
    map1.put("2","2");
    map1.put("3",mapA);
    map1.put("4",l1);
    map2.put("5",5);
    map2.put("6",6);
    map2.put("1",22);
    map2.put("3",mapB);
    map2.put("4",l2);
    Map map3= CollectionUtil.mapConbine(map1,map2,false);
    logger.info("map3:{}",map3);
    logger.info("map2:{}",map2);
    logger.info("map1:{}",map1);
  }

  @Test
  public void testXml(){
    Map<String,Object> root = new HashMap<>();

    Map<String,Object> resultMap = new HashMap<>();
    root.put("root",resultMap);
    resultMap.put("key","123");
    resultMap.put("test",1);
    resultMap.put("channelId",2);
    resultMap.put("token",3);
    String result = null;
//    try {
////      result = XmlUtil.map2xml(root).asXML();
//      logger.info("result:{}",result);
//    } catch (DocumentException e) {
//      e.printStackTrace();
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
  }

}
