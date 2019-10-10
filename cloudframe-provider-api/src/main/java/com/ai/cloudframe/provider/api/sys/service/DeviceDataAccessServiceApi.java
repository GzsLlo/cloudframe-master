/*
 * Copyright (c) 2019.  AI.
 */

package com.ai.cloudframe.provider.api.sys.service;


import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.provider.api.sys.model.dto.AirQualityDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


/**
 * 数据采集.
 *
 * @author tangsz
 */

@FeignClient(value = "${cloudframe.microservice.application}")
@Component
public interface DeviceDataAccessServiceApi {

  /**
   * 设备数据采集.
   *
   * @param airQualityDto
   * @return
   */
  @PostMapping(
      value = "/airQuality/deviceDataAccess",
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  @ResponseBody
  BaseResponse deviceDataAccess(@RequestBody AirQualityDto airQualityDto);


  /**
   * 数据呈现接口.
   *
   * @param params
   * @return
   */
  @PostMapping(value = "/airQuality/getDeviceData")
  @ResponseBody
  BaseResponse<Map> getDeviceData(@RequestBody Map params);
}
