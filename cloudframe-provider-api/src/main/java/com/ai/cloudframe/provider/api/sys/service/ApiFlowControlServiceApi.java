/**
 * Copyright 2019 asiainfo Inc.
 **/

package com.ai.cloudframe.provider.api.sys.service;

import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.provider.api.sys.model.dto.AbilityRegisterDto;
import com.ai.cloudframe.provider.api.sys.model.dto.ApiFlowControlDto;
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
public interface ApiFlowControlServiceApi {

  @PostMapping(value = "/apiFlowControlService/queryApiFlowControlList")
  @ResponseBody
  BaseResponse<PageVo<ApiFlowControlDto>> queryApiFlowControlList(@RequestBody ApiFlowControlDto dto);

  @PostMapping(value = "/apiFlowControlService/saveApiFlowControl")
  @ResponseBody
  BaseResponse<Map> saveApiFlowControl(@RequestBody ApiFlowControlDto dto);

  @PostMapping(value = "/apiFlowControlService/modifyApiFlowControl")
  @ResponseBody
  BaseResponse<Map> modifyApiFlowControl(@RequestBody ApiFlowControlDto dto);
}
