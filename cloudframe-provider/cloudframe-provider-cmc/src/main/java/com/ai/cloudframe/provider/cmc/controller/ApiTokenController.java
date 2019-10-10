package com.ai.cloudframe.provider.cmc.controller;


import com.ai.cloudframe.common.base.constant.GlobalConstant;
import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.common.base.util.DateUtil;
import com.ai.cloudframe.provider.api.sys.model.dto.ChannelAuthorizeDto;
import com.ai.cloudframe.provider.api.sys.service.ApiTokenApi;
import com.ai.cloudframe.provider.cmc.entity.ApiToken;
import com.ai.cloudframe.provider.cmc.service.IApiTokenService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import com.ai.cloudframe.common.base.support.BaseController;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-18
 */
@RestController
@Api(description = "渠道授权相关服务", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiTokenController extends BaseController implements ApiTokenApi {

  @Autowired
  IApiTokenService iApiTokenService;

  @Override
  @Transactional
  public BaseResponse<Map> channelAuthorize(ChannelAuthorizeDto dto) {
    ApiToken apiToken = new ApiToken();
    apiToken.setApiId(dto.getApiId());
    apiToken.setChannelId(dto.getChannelId());
    apiToken.setChannelName(dto.getChannelName());
    apiToken.setToken(dto.getToken());
    apiToken.setStartDate(new Timestamp(DateUtil.nowAbsMilliSeconds()));
    apiToken.setEndDate(new Timestamp(DateUtil.string2Date(GlobalConstant.END_DATE,DateUtil.SEG_YYYYMMDD).getTime()));
    iApiTokenService.save(apiToken);
    return BaseResponse.success();
  }

}
