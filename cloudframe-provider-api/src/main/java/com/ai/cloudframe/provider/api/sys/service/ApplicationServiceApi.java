/*
 * Copyright (c) 2019.  AI.
 */

package com.ai.cloudframe.provider.api.sys.service;


import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.provider.api.sys.model.dto.ApiDto;
import com.ai.cloudframe.provider.api.sys.model.dto.ApplicationDto;
import com.ai.cloudframe.provider.api.sys.model.vo.AddApplicationVo;
import com.ai.cloudframe.provider.api.sys.model.vo.ApplicationProductVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author tangsz
 */

@FeignClient(value = "${cloudframe.microservice.cmc}")
@Component
public interface ApplicationServiceApi {


  @PostMapping(value = "/apiService/applicationQuery")
  @ResponseBody
  BaseResponse<Map> applicationQuery(@RequestBody Map map);

  /**
   * 新增应用.
   *
   * @param addApplicationVos
   * @return
   */
  @PostMapping(value = "/application/createApplication")
  @ResponseBody
  BaseResponse<Map> createApplication(@RequestBody List<AddApplicationVo> addApplicationVos);

  /**
   * 查询应用.
   *
   * @param map
   * @return
   */
  @PostMapping(value = "/application/getApplicationInfo")
  @ResponseBody
  BaseResponse<Map> getApplicationInfo(@RequestBody Map map);

  /**
   * 编辑应用.
   *
   * @param applicationVo
   * @return
   */
  @PostMapping(value = "/application/updateApplication")
  @ResponseBody
  BaseResponse<Map> updateApplication(@RequestBody AddApplicationVo applicationVo);

  /**
   * 变更应用权限.
   *
   * @param applicationVo
   * @return
   */
  @PostMapping(value = "/application/updateApplicationRolePermission")
  @ResponseBody
  BaseResponse<Map> updateApplicationRolePermission(@RequestBody AddApplicationVo applicationVo);

  /**
   * 部署应用.
   *
   * @return
   */
  @PostMapping(value = "/application/deployApplication")
  @ResponseBody
  BaseResponse<Map> deployApplication();

  /**
   * 启动应用.
   *
   * @return
   */
  @PostMapping(value = "/application/startOrStopApplication")
  @ResponseBody
  BaseResponse<Map> startOrStopApplication(@RequestBody String tag);


  /**
   * 启动应用.
   *
   * @return
   */
  @PostMapping(value = "/applicationProduct/applicationProductRelation")
  @ResponseBody
  BaseResponse<ApplicationProductVo> applicationProductRelation(@RequestBody ApiDto dto);

}
