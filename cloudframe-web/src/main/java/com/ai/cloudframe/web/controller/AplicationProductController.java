/**
 * Copyright 2019 asiainfo Inc.
 **/

package com.ai.cloudframe.web.controller;

import com.ai.cloudframe.common.base.constant.GlobalConstant;
import com.ai.cloudframe.common.base.enums.StatusEnum;
import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.common.base.support.BaseController;
import com.ai.cloudframe.provider.api.sys.model.dto.ApiDto;
import com.ai.cloudframe.provider.api.sys.model.dto.ApiListDto;
import com.ai.cloudframe.provider.api.sys.model.dto.ApiParamDto;
import com.ai.cloudframe.provider.api.sys.model.dto.ApiStatisticsDto;
import com.ai.cloudframe.provider.api.sys.model.dto.ApplicationDto;
import com.ai.cloudframe.provider.api.sys.model.dto.CallApiDto;
import com.ai.cloudframe.provider.api.sys.model.vo.ApplicationProductVo;
import com.ai.cloudframe.provider.api.sys.model.vo.PageVo;
import com.ai.cloudframe.provider.api.sys.service.ApiServiceApi;
import com.ai.cloudframe.provider.api.sys.service.ApiStatisticsServiceApi;
import com.ai.cloudframe.provider.api.sys.service.ApplicationServiceApi;
import com.ai.cloudframe.provider.api.sys.service.CallApiServiceApi;
import com.ai.cloudframe.provider.api.sys.service.ParamServiceApi;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author: shenbin
 * @CreateDate: [2019/7/17 19:27]
 * @Version: [v1.0]
 */
@RestController
@Api(description = "应用产品接口")
@CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = "*")
public class AplicationProductController extends BaseController {
  private static Logger logger = LoggerFactory.getLogger(AplicationProductController.class);

  @Autowired
  ApplicationServiceApi applicationServiceApi;

//  @RequestMapping(
//      value = "/applicationProduct/applicationProductRelation",
//      method = RequestMethod.POST
////      , produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
////      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
//  )
//  @ApiOperation("能力列表")
//  @ApiImplicitParams({
//      @ApiImplicitParam(name = "vueJson", value = "提交报文", required = true, paramType = "form")
//  })
//  public BaseResponse abilityList(@RequestParam String applicationId) {
//    logger.info("input applicationId:{}",applicationId);
//    ApplicationDto applicationDto = new ApplicationDto();
//    applicationDto.setApplicationId(applicationId);
//
//    BaseResponse<ApplicationProductVo> response = applicationServiceApi.applicationProductRelation(applicationDto);
//
//    return response;
//  }
}
