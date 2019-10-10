/**
 * Copyright 2019 asiainfo Inc.
 **/

package com.ai.cloudframe.provider.api.sys.service;

import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.provider.api.sys.model.dto.ApiStatisticsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @Author: shenbin
 * @CreateDate: [2019/7/24 10:17]
 * @Version: [v1.0]
 */
@FeignClient(value = "${cloudframe.microservice.cmc}")
@Component
public interface ApiStatisticsServiceApi {

  @PostMapping(value = "/apiStatisticsService/insertStatistics")
  @ResponseBody
  BaseResponse<Map> insertStatistics(@RequestBody ApiStatisticsDto dto);

}
