/**
 * Copyright 2019 asiainfo Inc.
 **/

package com.ai.cloudframe.web.controller;

import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.common.base.support.BaseController;
import com.ai.cloudframe.common.base.util.Utility;

import com.ai.cloudframe.provider.api.sys.service.ApiServiceApi;

import com.ai.cloudframe.provider.api.sys.service.ParamServiceApi;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: shenbin
 * @CreateDate: [2019/7/17 19:27]
 * @Version: [v1.0]
 */
@RestController
@Api(description = "原子产品材料接口")
@CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = "*")
public class APIOperationController extends BaseController {


  @Autowired
  ParamServiceApi paramServiceApi;

  @Autowired
  private ApiServiceApi apiServiceApi;

  @RequestMapping(value = "/apiQuery", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation("列表查询-根据创建人ID查询API部分信息")
  @ApiImplicitParams({
          @ApiImplicitParam(name = "vueJson", value = "提交报文",required=true,paramType="form")
  })
  public BaseResponse apiQuery(@RequestParam Map<String, Object> vueJson, @RequestHeader HttpHeaders headers) {
    logger.info("vueJson{}", vueJson);
    JSONObject map = Utility.convert2JsonAndCheckRequiredColumns(vueJson, "");

    BaseResponse<Map> baseResponse = apiServiceApi.apiQuery(map);
    logger.info("baseResponse:",baseResponse.getData());
    List<Map<String,Object>> dataList = (List<Map<String,Object>> ) baseResponse.getData().get("data");
    Map<String,Object> param = new HashMap<>();

    Map resultMap = new HashMap();
    resultMap.put("dataList",dataList);
    resultMap.put("total",baseResponse.getData().get("total"));
    resultMap.put("page",baseResponse.getData().get("page"));
    resultMap.put("size",baseResponse.getData().get("size"));
    return baseResponse.success(resultMap);
  }


  @RequestMapping(value = "/apiAppQuery", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
          consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  @ApiOperation("列表查询-根据创建人ID查询API以及APP部分信息")
  @ApiImplicitParams({
          @ApiImplicitParam(name = "vueJson", value = "提交报文",required=true,paramType="form")
  })
  public BaseResponse apiAppQuery(@RequestParam Map<String, Object> vueJson, @RequestHeader HttpHeaders headers) {
    logger.info("vueJson{}", vueJson);
    JSONObject map = Utility.convert2JsonAndCheckRequiredColumns(vueJson, "");

    BaseResponse<Map> baseResponse = apiServiceApi.apiAppQuery(map);
    logger.info("baseResponse:",baseResponse.getData());
    List<Map<String,Object>> dataList = (List<Map<String,Object>> ) baseResponse.getData().get("data");
    Map<String,Object> param = new HashMap<>();


    for(Map dataMap:dataList){

      if(!StringUtils.isEmpty(dataMap.get("introduction"))) {
        BaseResponse<String> codeToName2 = paramServiceApi
            .codeToName("introductionType", (String) dataMap.get("introduction"));
        dataMap.put("introduction", codeToName2.getData());
      }

      dataMap.put("merchants", "某信科技");

    }

    Map resultMap = new HashMap();
    resultMap.put("dataList",dataList);
    resultMap.put("total",baseResponse.getData().get("total"));
    return baseResponse.success(resultMap);
  }
}
