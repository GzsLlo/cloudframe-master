/*
 * Copyright (c) 2019. AI
 */
package com.ai.cloudframe.common.base.constant;

/**

 * @author tangsz
 */
public class GlobalConstant {

  public static final String SESSION_USER_INFO = "userInfo";
  public static final String SESSION_USER_PERMISSION = "userPermission";
  public static final String SESSION_USER_ROLES = "userRoles";
  public static final String SESSION_MEAN_PERMISSION = "menuPermission";
  public static final String SESSION_PERMISSION_LIST = "permissionList";

  public static final String END_DATE = "2099-12-31";

  public static class MODIFY_TAG{
    public static final String INSERT = "1";
    public static final String DELETE = "2";
    public static final String UPDATE = "3";
  }

  public static class API_DATA_FORMAT{
    public static final String JSON ="0";
    public static final String XML ="1";
  }

  //0:String,1:int,2:double,3:date,8:map,9:list
  public static class API_PARAM_TYPE{
    public static final String STRING ="0";
    public static final String INT ="1";
    public static final String DOUBLE ="2";
    public static final String DATE ="3";
    public static final String MAP ="8";
    public static final String LIST ="9";
  }

  public static class YES_OR_NO{
    public static final String NO  ="0";
    public static final String YES ="1";
  }

  public static class API_RARAM_MODE{
    public static final String REQUEST = "0";
    public static final String RESPONSE = "1";
  }

  public static class FLOW_CONTROL_TIME_UNIT{
    public static final String SECONDS = "0";
    public static final String MIMUTE = "1";
    public static final String HOUR = "2";
    public static final String DAY = "3";
  }

  public static class FLOW_CONTROL_TYPE{
    public static final String API = "0";//api级
    public static final String CHANNEL = "1";//渠道级
  }

  public static class API_TYPE{
    public static final String ATOMIC = "0";//原子
    public static final String COMBINATION = "1";//组合
  }

  public static class API_STATUS{
    public static final String CLOSED = "0";//关闭
    public static final String ACTIVE = "1";//启用
  }

  public static class REQUEST_METHOD{
    public static final String POST = "0";
    public static final String GET = "1";
  }
}
