package com.ai.cloudframe.web.controller;/**
 * Copyright 2019 asiainfo Inc.
 **/


import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.common.base.support.BaseController;
import com.ai.cloudframe.provider.api.sys.service.PrimaryKeyFeginServiceApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenxin
 * created on 2019/06/25
 */
@RestController
@Api(description = "生成uuid主键")
@CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = "*")
public class PrimaryKeyController extends BaseController{

  @Autowired
  PrimaryKeyFeginServiceApi primaryKeyFeginServiceApi;


  @RequestMapping(value = "/service/createPrimaryKey", method = RequestMethod.POST )
  @ApiOperation("生成uuid主键")
  public BaseResponse createPrimaryKey(){
    BaseResponse<String> baseResponse = primaryKeyFeginServiceApi.createPrimaryKey();
    String uuidStr = baseResponse.getData();
    logger.debug("createPrimaryKey:{}",uuidStr);

    return BaseResponse.success(uuidStr);
  };
}
