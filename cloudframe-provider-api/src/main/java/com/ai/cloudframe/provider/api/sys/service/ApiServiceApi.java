/**
 * Copyright 2019 asiainfo Inc.
 **/

package com.ai.cloudframe.provider.api.sys.service;

import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.provider.api.sys.model.dto.AbilityRegisterDto;
import com.ai.cloudframe.provider.api.sys.model.dto.ApiDto;
import com.ai.cloudframe.provider.api.sys.model.dto.ApiListDto;
import com.ai.cloudframe.provider.api.sys.model.vo.PageVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @Author: shenbin
 * @CreateDate: [2019/7/17 19:56]
 * @Version: [v1.0]
 */
@FeignClient(value = "${cloudframe.microservice.cmc}")
@Component
public interface ApiServiceApi {

  @PostMapping(value = "/apiService/apiQuery")
  @ResponseBody
  BaseResponse<Map> apiQuery(@RequestBody Map map);

  @PostMapping(value = "/apiService/apiAppQuery")
  @ResponseBody
  BaseResponse<Map> apiAppQuery(@RequestBody Map map);

  @PostMapping(value = "/apiService/queryApiList")
  @ResponseBody
  BaseResponse<PageVo<ApiDto>> queryApiList(@RequestBody ApiListDto dto);


  @PostMapping(value = "/apiService/queryApiDetail")
  @ResponseBody
  BaseResponse<ApiDto> queryApiDetail(@RequestBody ApiDto dto);

  @PostMapping(value = "/apiService/saveApi")
  @ResponseBody
  BaseResponse<Map> saveApi(@RequestBody AbilityRegisterDto dto);

  @PostMapping(value = "/apiService/apiModify")
  @ResponseBody
  BaseResponse<Map> apiModify(@RequestBody AbilityRegisterDto dto);
}
