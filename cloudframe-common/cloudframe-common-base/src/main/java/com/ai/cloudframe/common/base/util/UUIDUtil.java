/**
 * Copyright 2019 asiainfo Inc.
 **/

package com.ai.cloudframe.common.base.util;

import java.util.UUID;

/**
 * @Author: shenbin
 * @CreateDate: [2019/7/24 15:22]
 * @Version: [v1.0]
 */
public class UUIDUtil {

  public static String getUUID(){
    String uuid = UUID.randomUUID().toString();
    //带‘-’36位 8-4-4-4-12，去‘-’32位
    return uuid.replaceAll("-", "");
  }



}
