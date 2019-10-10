/**
 * Copyright 2019 asiainfo Inc.
 **/

package com.ai.cloudframe.web.controller;

import com.ai.cloudframe.common.base.enums.StatusEnum;
import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.common.base.support.BaseController;
import com.ai.cloudframe.provider.api.sys.model.dto.ApiStatisticsDto;
import com.ai.cloudframe.provider.api.sys.model.dto.CallApiDto;
import com.ai.cloudframe.provider.api.sys.model.dto.ChannelAuthorizeDto;
import com.ai.cloudframe.provider.api.sys.model.dto.ChannelDto;
import com.ai.cloudframe.provider.api.sys.service.ApiStatisticsServiceApi;
import com.ai.cloudframe.provider.api.sys.service.ApiTokenApi;
import com.ai.cloudframe.provider.api.sys.service.CallApiServiceApi;
import com.ai.cloudframe.provider.api.sys.service.ChannelServiceApi;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

/**
 * @Author: shenbin
 * @CreateDate: [2019/7/17 19:27]
 * @Version: [v1.0]
 */
@RestController
@Api(description = "渠道接口")
@CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = "*")
public class ChannelController extends BaseController {
  private static Logger logger = LoggerFactory.getLogger(ChannelController.class);

  @Autowired
  private ChannelServiceApi channelServiceApi;

  @Autowired
  private ApiTokenApi apiTokenApi;

  @RequestMapping(value = "/channelService/addChannel", method = {RequestMethod.POST})
  @ApiOperation("新增渠道")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "vueJson", value = "提交报文", required = true, dataType = "form")
  })
  public BaseResponse addChannel(@RequestBody String in, @RequestHeader HttpHeaders headers, HttpServletRequest request) {
    logger.info("api url:{}",request.getRequestURI());
    logger.info("input:{}",in);
    logger.info("requestMethod:{}",request.getMethod());
    logger.info("input String:{}",in);
    ChannelDto dto = JSON.parseObject(in,ChannelDto.class);
    logger.info("input dto:{}",dto);
    BaseResponse response = channelServiceApi.saveChannel(dto);
    logger.info("api end result:{}.",response);

    return response;
  }

  @RequestMapping(value = "/channelService/channelList", method = {RequestMethod.POST})
  @ApiOperation("新增渠道")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "vueJson", value = "提交报文", required = true, dataType = "form")
  })
  public BaseResponse channelList(@RequestBody String in, @RequestHeader HttpHeaders headers, HttpServletRequest request) {
    logger.info("api url:{}",request.getRequestURI());
    logger.info("input:{}",in);
    logger.info("requestMethod:{}",request.getMethod());
    logger.info("input String:{}",in);
    ChannelDto dto = JSON.parseObject(in,ChannelDto.class);
    logger.info("input dto:{}",dto);
    BaseResponse response = channelServiceApi.channelList(dto);
    logger.info("api end result:{}.",response);
    return response;
  }

  @RequestMapping(value = "/channelService/channelAuthorize", method = {RequestMethod.POST})
  @ApiOperation("新增渠道")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "vueJson", value = "提交报文", required = true, dataType = "form")
  })
  public BaseResponse channelAuthorize(@RequestBody String in, @RequestHeader HttpHeaders headers, HttpServletRequest request) {
    logger.info("api url:{}",request.getRequestURI());
    logger.info("input:{}",in);
    logger.info("requestMethod:{}",request.getMethod());
    logger.info("input String:{}",in);
    ChannelAuthorizeDto dto = JSON.parseObject(in,ChannelAuthorizeDto.class);
    logger.info("input dto:{}",dto);
    BaseResponse response = apiTokenApi.channelAuthorize(dto);
    logger.info("api end result:{}.",response);
    return response;
  }

  @RequestMapping(value = "/queryTable/channelInfoList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation("渠道列表查询-商品发布使用")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "vueJson", value = "提交报文", required = true, dataType = "form")
  })
  public BaseResponse goodInfoList(@RequestBody ChannelDto channelDto, @RequestHeader HttpHeaders headers) {
    logger.info("channelDto{}", channelDto);

    BaseResponse<Map> baseResponse = channelServiceApi.channelInfoList(channelDto);
    logger.info("baseResponse:", baseResponse.getData());

    return  baseResponse;
  }


  /*
   根据渠道ID查询列表
    */
  @RequestMapping(value = "/queryTable/getTableByChannelId", method = RequestMethod.POST ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  @ApiOperation("根据渠道ID查询列表")
  @ApiImplicitParams({
      @ApiImplicitParam(name="vueJson",value="提交报文",required=true,paramType="form")
  })
  public BaseResponse getTableByChannelId(@RequestParam Map<String, Object> vueJson, @RequestHeader HttpHeaders headers){
    //传参：账期和商品名称产品名称
    logger.info("login.param:{}", vueJson);
    BaseResponse<Map> baseResponse = channelServiceApi.getTableByChannelId(vueJson);
    return baseResponse;
  }





}
