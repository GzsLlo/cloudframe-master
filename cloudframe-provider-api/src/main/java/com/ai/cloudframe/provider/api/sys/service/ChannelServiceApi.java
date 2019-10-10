/**
 * Copyright 2019 asiainfo Inc.
 **/

package com.ai.cloudframe.provider.api.sys.service;

import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.provider.api.sys.model.dto.CallApiDto;
import com.ai.cloudframe.provider.api.sys.model.dto.ChannelAuthorizeDto;
import com.ai.cloudframe.provider.api.sys.model.dto.ChannelDto;
import com.ai.cloudframe.provider.api.sys.model.vo.PageVo;
import com.ai.cloudframe.provider.api.sys.service.fallback.CallApiServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @Author: shenbin
 * @CreateDate: [2019/7/25 15:43]
 * @Version: [v1.0]
 */
@FeignClient(value = "${cloudframe.microservice.cmc}")
@Component
public interface ChannelServiceApi {

  @PostMapping(value = "/channelService/saveChannel")
  @ResponseBody
  BaseResponse<Map> saveChannel(@RequestBody ChannelDto dto);

  @PostMapping(value = "/channelService/channelList")
  @ResponseBody
  BaseResponse<PageVo<ChannelDto>> channelList(@RequestBody ChannelDto dto);

  @PostMapping(value = "/channelService/channelInfoList")
  @ResponseBody
  BaseResponse channelInfoList(@RequestBody ChannelDto dto);


  @PostMapping(value = "/queryTable/getTableByChannelId")
  @ResponseBody
  BaseResponse getTableByChannelId(@RequestBody Map selectParams);


  @PostMapping(value = "/channelService/channelByChanId")
  @ResponseBody
  BaseResponse channelByChanId(@RequestBody ChannelDto param);



}
