package com.ai.cloudframe.common.config.properties;

import lombok.Data;

/**
 * tangsz
 */
@Data
public class RedisProperties{
  private String host = "localhost";
  private String password;
  private int timeout = 2000;
  private int expire = 86400;
  private String sessionId = "sid";
}
