/**
 * Copyright 2019 asiainfo Inc.
 **/

package com.ai.cloudframe.web.controller;

import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.common.base.support.BaseController;
import com.ai.cloudframe.provider.api.sys.model.dto.AbilityRegisterDto;
import com.ai.cloudframe.provider.api.sys.model.dto.ApiDto;
import com.ai.cloudframe.provider.api.sys.model.dto.ApiListDto;
import com.ai.cloudframe.provider.api.sys.model.dto.ApiParamDto;
import com.ai.cloudframe.provider.api.sys.model.vo.PageVo;
import com.ai.cloudframe.provider.api.sys.service.ApiServiceApi;
import com.ai.cloudframe.provider.api.sys.service.ParamServiceApi;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: shenbin
 * @CreateDate: [2019/7/24 14:48]
 * @Version: [v1.0]
 */
@RestController
@Api(description = "能力类接口")
@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST}, origins = "*")
public class AbilityRegisterController extends BaseController {

  private static Logger logger = LoggerFactory.getLogger(AbilityRegisterController.class);

  @Autowired
  private ApiServiceApi apiServiceApi;

  @RequestMapping(
      value = "/ability/abilityRegister",
      method = RequestMethod.POST
//      , produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
//      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
  )
  @ApiOperation("能力注册")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "vueJson", value = "提交报文", required = true, paramType = "form")
  })
  public BaseResponse addApi(@RequestBody String input) {
    logger.info("input json:{}",input);
    Map inputMap = JSON.parseObject(input,Map.class);
    AbilityRegisterDto dto = new AbilityRegisterDto();
    ApiDto abilityForm = JSON.parseObject(JSON.toJSON(inputMap.get("abilityForm")).toString(),ApiDto.class);
    List<ApiParamDto> params =JSON.parseArray(inputMap.get("params").toString(),ApiParamDto.class);
     dto.setAbilityForm(abilityForm);
    dto.setParams(params);
    BaseResponse response = apiServiceApi.saveApi(dto);

    return response;
  }

  @RequestMapping(
      value = "/ability/abilityModify",
      method = RequestMethod.POST
//      , produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
//      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
  )
  @ApiOperation("能力修改")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "vueJson", value = "提交报文", required = true, paramType = "form")
  })
  public BaseResponse apiModify(@RequestParam String requestParam) {
    logger.info("input requestParam:{}",requestParam);
    Map requestMap = JSON.parseObject(requestParam,Map.class);
    AbilityRegisterDto dto = new AbilityRegisterDto();
    if(!requestMap.containsKey("param") && !requestMap.containsKey("list")){
      return BaseResponse.fail("","更新失败");
    }
    if(requestMap.containsKey("param")){
      ApiDto apiDto = JSON.parseObject((String) requestMap.get("param"),ApiDto.class);
      dto.setAbilityForm(apiDto);
    }
    if(requestMap.containsKey("list")){
      List<ApiParamDto> paramDtos = ((JSONArray) requestMap.get("list")).toJavaList(ApiParamDto.class) ;
      dto.setParams(paramDtos);
    }
    BaseResponse response = apiServiceApi.apiModify(dto);

    return response;
  }

}
