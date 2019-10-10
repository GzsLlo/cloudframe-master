package com.ai.cloudframe.common.base.util;

import com.ai.cloudframe.common.base.enums.ErrorCodeEnum;
import com.ai.cloudframe.common.base.exception.BusinessException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * tangsz
 */
public class Utility {

  /**
   * 将request参数值转为json
   */
  public static JSONObject request2Json(HttpServletRequest request) {
    JSONObject requestJson = new JSONObject();
    Enumeration paramNames = request.getParameterNames();
    while (paramNames.hasMoreElements()) {
      String paramName = (String) paramNames.nextElement();
      String[] pv = request.getParameterValues(paramName);
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < pv.length; i++) {
        if (pv[i].length() > 0) {
          if (i > 0) {
            sb.append(",");
          }
          sb.append(pv[i]);
        }
      }
      requestJson.put(paramName, sb.toString());
    }
    return requestJson;
  }

  /**
   * 将request转JSON
   * 并且验证非空字段
   */
  public static JSONObject convert2JsonAndCheckRequiredColumns(HttpServletRequest request, String requiredColumns) {
    JSONObject jsonObject = request2Json(request);
    hasAllRequired(jsonObject, requiredColumns);
    return jsonObject;
  }

  public static JSONObject convert2JsonAndCheckRequiredColumns(Map<String, Object> vueParam, String requiredColumns) {
    String paramStr = (String) vueParam.get("vueJson");
    JSONObject jsonObject = JSON.parseObject(paramStr);
    hasAllRequired(jsonObject, requiredColumns);
    return jsonObject;
  }

  public static JSONArray convert2JSONArray(Map<String, Object> vueParam, String requiredColumns) {
    String paramStr = (String) vueParam.get("vueJson");
    JSONObject jsonObject = JSON.parseObject(paramStr);
    JSONArray jsonArray = jsonObject.getJSONArray(requiredColumns);
    return jsonArray;
  }


  /**
   * 验证是否含有全部必填字段
   *
   * @param requiredColumns 必填的参数字段名称 逗号隔开 比如"userId,name,telephone"
   */
  public static void hasAllRequired(final JSONObject jsonObject, String requiredColumns) {
    if (!StringUtil.isNullOrEmpty(requiredColumns)) {
      String[] columns = requiredColumns.split(",");
      final String[] missCol = {""};
      Arrays.asList(columns).stream().forEach(column -> {
        Object val = jsonObject.get(column.trim());
        if (StringUtil.isNullOrEmpty((String) val)) {
          missCol[0] += column + "  ";
        }
      });

      if (!StringUtil.isNullOrEmpty(missCol[0])) {
        throw new BusinessException(ErrorCodeEnum.E00000002.code(), ErrorCodeEnum.E00000002.msg() + missCol[0].trim());
      }
    }
  }

  public static<F,T> List<T> copyListProperties(List<F> fromList, Class<T> tClass) {
    if(fromList.isEmpty() || fromList == null){
      return null;
    }
    List<T> tList = new ArrayList<>();
    for(F f : fromList){
      T t = null;
      try {
        t = tClass.newInstance();
      } catch (InstantiationException e) {
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
      BeanUtils.copyProperties(f, t);
      tList.add(t);
    }
    return tList;
  }

}
