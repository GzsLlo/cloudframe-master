package com.ai.cloudframe.common.base.exception;


import com.ai.cloudframe.common.base.enums.ErrorCodeEnum;
import com.ai.cloudframe.common.base.response.BaseResponse;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 全局异常.
 *
 * @author tangsz
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(value = Exception.class)
  public BaseResponse<Object> handleBadRequest(Exception e) {
    logger.error("Error: handleBadRequest StackTrace : {}", e);
    return BaseResponse.fail(e, e.getMessage());
  }

  @ExceptionHandler(AuthorizationException.class)
  public JSONObject handleAuthorizationException(AuthorizationException e){
    JSONObject json = new JSONObject();
    json.put("code", ErrorCodeEnum.E00000403.code());
    json.put("message", ErrorCodeEnum.E00000403.msg());
    return json;
  }
}
