/**
 * Copyright 2019 asiainfo Inc.
 **/

package com.ai.cloudframe.provider.api.sys.service;

import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.provider.api.sys.model.dto.CallApiDto;
import com.ai.cloudframe.provider.api.sys.service.fallback.CallApiServiceFallback;
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
@FeignClient(value = "${cloudframe.microservice.cmc}", fallback = CallApiServiceFallback.class)
@Component
public interface CallApiServiceApi {

  @PostMapping(value = "/callApiService/callApi")
  @ResponseBody
  BaseResponse<Map> callApi(@RequestBody CallApiDto param);

  @PostMapping(value = "/callApiService/callApiByChanId")
  @ResponseBody
  BaseResponse callApiByChanId(@RequestBody CallApiDto param);
}
