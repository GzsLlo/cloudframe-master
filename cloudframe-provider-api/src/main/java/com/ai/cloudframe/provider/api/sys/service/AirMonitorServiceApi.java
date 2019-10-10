/*
 * Copyright (c) 2019.  AI.
 */

package com.ai.cloudframe.provider.api.sys.service;


import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.provider.api.sys.model.vo.AirMonitorVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


/**
 * 监控配置.
 *
 * @author tangsz
 */

@FeignClient(value = "${cloudframe.microservice.application}")
@Component
public interface AirMonitorServiceApi {

  /**
   * 监控配置查询.
   *
   * @param params
   * @return
   */
  @PostMapping(value = "/airMonitor/getAirMonitorInfo")
  @ResponseBody
  BaseResponse<Map> getAirMonitorInfo(@RequestBody Map params);


  /**
   * 修改监控配置.
   *
   * @param airMonitorVo
   * @return
   */
  @PostMapping(value = "/application/updateAirMonitor")
  @ResponseBody
  BaseResponse<Map> updateAirMonitor(@RequestBody AirMonitorVo airMonitorVo);

  /**
   * 监控告警.
   *
   * @param params
   * @return
   */
  @PostMapping(value = "/airMonitor/airMonitorAlarm")
  @ResponseBody
  BaseResponse<Map> airMonitorAlarm(@RequestBody Map params);
}
