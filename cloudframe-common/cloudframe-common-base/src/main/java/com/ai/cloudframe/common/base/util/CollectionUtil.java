/**
 * Copyright 2019 asiainfo Inc.
 **/

package com.ai.cloudframe.common.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author: shenbin
 * @CreateDate: [2019/7/22 11:10]
 * @Version: [v1.0]
 */
public class CollectionUtil {

  private static Logger logger = LoggerFactory.getLogger(CollectionUtil.class);

  /**
   * 同名参数不进行覆盖，map进行合并
   * @param map1 主map
   * @param map2 参数map
   * @param listAddOrNot 是否合并list
   * @return
   */
  public static Map<String,Object> mapConbine(Map<String,Object> map1, Map<String,Object> map2,boolean listAddOrNot){
    Set<Map.Entry<String,Object>> all = new HashSet<>();
    all.addAll(map1.entrySet());
    all.addAll(map2.entrySet());
    return all.stream().collect(Collectors.toMap(item -> item.getKey(), item -> item.getValue(), (oldVal, currVal) -> {
      logger.info("oldval:{},currVal:{}",oldVal.getClass(),currVal.getClass());
      if(oldVal instanceof Map && currVal instanceof Map){
        return mapConbine((Map)oldVal,(Map)currVal,listAddOrNot);
      }else if( oldVal instanceof List && currVal instanceof List){
        logger.info("oldval:{},currVal:{}",oldVal.getClass(),currVal.getClass());
        if(listAddOrNot){
          //需要合并list
          logger.info("list add");
          ((List)oldVal).addAll((List)currVal);
        }else {
          //不需要合并list
        }
      }

      return oldVal;
    }));
  }

}
