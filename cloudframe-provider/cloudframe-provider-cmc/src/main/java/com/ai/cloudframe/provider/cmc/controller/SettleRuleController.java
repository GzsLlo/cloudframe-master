package com.ai.cloudframe.provider.cmc.controller;


import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.provider.api.sys.model.dto.GoodProductSettleDto;
import com.ai.cloudframe.provider.api.sys.service.SettleRuleServiceApi;
import com.ai.cloudframe.provider.cmc.service.IIntroduceProductService;
import com.ai.cloudframe.provider.cmc.service.ISettleRuleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import com.ai.cloudframe.common.base.support.BaseController;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-17
 */
@RestController
@Api(description = "匹配表相关接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SettleRuleController extends BaseController implements SettleRuleServiceApi {


  @Autowired
  ISettleRuleService iSettleRuleService;


  @Override
  public BaseResponse newSettleRule(List<GoodProductSettleDto> goodProductSettleDtoList) {
    logger.debug("input param : {}", goodProductSettleDtoList);
    BaseResponse result = null;
    try {
      result =  iSettleRuleService.newSettleRule(goodProductSettleDtoList) == true ? BaseResponse.success() : BaseResponse.fail();
    } catch (Exception e) {
      result = BaseResponse.fail();
    }
    return result;
  }
}
