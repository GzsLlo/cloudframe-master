/**
 * Copyright 2019 asiainfo Inc.
 **/

package com.ai.cloudframe.web.controller;

import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.common.base.support.BaseController;
import com.ai.cloudframe.common.base.util.Utility;
import com.ai.cloudframe.provider.api.sys.model.vo.AirMonitorVo;
import com.ai.cloudframe.provider.api.sys.service.AirMonitorServiceApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: shenbin
 * @CreateDate: [2019/7/17 19:27]
 * @Version: [v1.0]
 */
@RestController
@Api(description = "监控配置类接口")
@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST}, origins = "*")
public class AirMonitorConsumerController extends BaseController {
  private static Logger logger = LoggerFactory.getLogger(AirMonitorConsumerController.class);

  @Autowired
  private AirMonitorServiceApi airMonitorServiceApi;

  /**
   * 监控配置查询.
   *
   * @param vueJson
   * @return
   */
  @RequestMapping(
      value = "/airMonitor/getAirMonitorInfo",
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  @ApiOperation("监控配置查询")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "vueJson", value = "查询条件", required = true, paramType = "form")
  })
  public BaseResponse getAirMonitorInfo(@RequestParam Map<String, Object> vueJson) {
    logger.debug("AirMonitorConsumerController.getAirMonitorInfo vueJson : {}", vueJson);

    Map selectParams = Utility.convert2JsonAndCheckRequiredColumns(vueJson, "");

    BaseResponse<Map> baseResponse = airMonitorServiceApi.getAirMonitorInfo(selectParams);
    logger.info("AirMonitorConsumerController getAirMonitorInfo baseResponse : {}", baseResponse);

    Map resultMap = baseResponse.getData();
    BaseResponse response = BaseResponse.success(resultMap);

    logger.debug("AirMonitorConsumerController getAirMonitorInfo response : {}", response);

    return response;
  }

  /**
   * 修改监控配置.
   *
   * @param airMonitorVo
   * @param headers
   * @return
   */
  @RequestMapping(
      value = "/airMonitor/updateAirMonitor",
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation("修改监控配置")
  public BaseResponse updateAirMonitor(@RequestBody AirMonitorVo airMonitorVo, @RequestHeader HttpHeaders headers) {
    logger.info("AirMonitorConsumerController updateAirMonitor airMonitorVo : {}", airMonitorVo);

    BaseResponse baseResponse = airMonitorServiceApi.updateAirMonitor(airMonitorVo);
    logger.info("AirMonitorConsumerController updateAirMonitor baseResponse : {}", baseResponse);

    return baseResponse;
  }

  /**
   * 监控告警.
   *
   * @param vueJson
   * @return
   */
  @RequestMapping(
      value = "/airMonitor/airMonitorAlarm",
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  @ApiOperation("监控告警")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "vueJson", value = "查询条件", required = true, paramType = "form")
  })
  public BaseResponse airMonitorAlarm(@RequestParam Map<String, Object> vueJson) {
    logger.debug("AirMonitorConsumerController.airMonitorAlarm vueJson : {}", vueJson);

    Map selectParams = Utility.convert2JsonAndCheckRequiredColumns(vueJson, "");

    BaseResponse<Map> baseResponse = airMonitorServiceApi.airMonitorAlarm(selectParams);
    logger.info("AirMonitorConsumerController airMonitorAlarm baseResponse : {}", baseResponse);

    Map resultMap = (null != baseResponse.getData()) ? baseResponse.getData() : new HashMap();
    BaseResponse response = BaseResponse.success(resultMap);

    logger.debug("AirMonitorConsumerController airMonitorAlarm response : {}", response);

    return response;
  }


}
