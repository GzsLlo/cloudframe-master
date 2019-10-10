/**
 * Copyright 2019 asiainfo Inc.
 **/

package com.ai.cloudframe.web.controller;

import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.common.base.support.BaseController;
import com.ai.cloudframe.provider.api.sys.model.dto.ApiFlowControlDto;
import com.ai.cloudframe.provider.api.sys.model.dto.ChannelDto;
import com.ai.cloudframe.provider.api.sys.model.vo.PageVo;
import com.ai.cloudframe.provider.api.sys.service.ApiFlowControlServiceApi;
import com.ai.cloudframe.provider.api.sys.service.ChannelServiceApi;
import com.ai.cloudframe.provider.api.sys.service.ParamServiceApi;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: shenbin
 * @CreateDate: [2019/7/17 19:27]
 * @Version: [v1.0]
 */
@RestController
@Api(description = "流控接口")
@CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = "*")
public class ApiFlowControlController extends BaseController {
  private static Logger logger = LoggerFactory.getLogger(ApiFlowControlController.class);

  @Autowired
  private ApiFlowControlServiceApi apiFlowControlServiceApi;

  @Autowired
  private ParamServiceApi paramServiceApi;

  @RequestMapping(value = "/apiFlowControlService/queryApiFlowControlList", method = {RequestMethod.POST})
  @ApiOperation("新增渠道")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "vueJson", value = "提交报文", required = true, dataType = "form")
  })
  public BaseResponse queryApiFlowControlList(@RequestBody String in, @RequestHeader HttpHeaders headers, HttpServletRequest request) {
    logger.info("api url:{}",request.getRequestURI());
    logger.info("input:{}",in);
    logger.info("requestMethod:{}",request.getMethod());
    logger.info("input String:{}",in);
    ApiFlowControlDto dto = JSON.parseObject(in,ApiFlowControlDto.class);
    logger.info("input dto:{}",dto);
    BaseResponse<PageVo<ApiFlowControlDto>> response = apiFlowControlServiceApi.queryApiFlowControlList(dto);
    if(response.getData() != null &&response.getData().getData() != null){
      response.getData().getData().forEach(apiFlowControlDto ->{
        apiFlowControlDto.setLimitUnitTimeName(StringUtils.isEmpty(apiFlowControlDto.getLimitUnitTime())?"":paramServiceApi.codeToName("limit_unit_time",apiFlowControlDto.getLimitUnitTime()).getData());
        apiFlowControlDto.setFlowControlTypeName(StringUtils.isEmpty(apiFlowControlDto.getFlowControlType())?"":paramServiceApi.codeToName("flow_control_type",apiFlowControlDto.getFlowControlType()).getData());
      });
    }

    logger.info("api end result:{}.",response);

    return response;
  }

  @RequestMapping(value = "/apiFlowControlService/saveApiFlowControl", method = {RequestMethod.POST})
  @ApiOperation("新增渠道")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "vueJson", value = "提交报文", required = true, dataType = "form")
  })
  public BaseResponse saveApiFlowControl(@RequestBody String in, @RequestHeader HttpHeaders headers, HttpServletRequest request) {
    logger.info("api url:{}",request.getRequestURI());
    logger.info("input:{}",in);
    logger.info("requestMethod:{}",request.getMethod());
    logger.info("input String:{}",in);
    ApiFlowControlDto dto = JSON.parseObject(in,ApiFlowControlDto.class);
    logger.info("input dto:{}",dto);
    BaseResponse response = apiFlowControlServiceApi.saveApiFlowControl(dto);
    logger.info("api end result:{}.",response);
    return response;
  }

  @RequestMapping(value = "/apiFlowControlService/modifyApiFlowControl", method = {RequestMethod.POST})
  @ApiOperation("新增渠道")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "vueJson", value = "提交报文", required = true, dataType = "form")
  })
  public BaseResponse modifyApiFlowControl(@RequestBody String in, @RequestHeader HttpHeaders headers, HttpServletRequest request) {
    logger.info("input :{}",in);
    ApiFlowControlDto dto = JSON.parseObject(in,ApiFlowControlDto.class);
//    dto.setFlag(flag);
//    dto.setControlId(controlId);
    logger.info("input dto:{}",dto);
    BaseResponse response = apiFlowControlServiceApi.modifyApiFlowControl(dto);
    logger.info("api end result:{}.",response);
    return response;
  }


}
