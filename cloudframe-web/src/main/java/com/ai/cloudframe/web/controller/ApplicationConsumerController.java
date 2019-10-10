/**
 * Copyright 2019 asiainfo Inc.
 **/

package com.ai.cloudframe.web.controller;

import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.common.base.support.BaseController;
import com.ai.cloudframe.common.base.util.Utility;
import com.ai.cloudframe.provider.api.sys.model.vo.AddApplicationVo;
import com.ai.cloudframe.provider.api.sys.model.vo.DeployVo;
import com.ai.cloudframe.provider.api.sys.service.ApplicationServiceApi;
import com.ai.cloudframe.provider.api.sys.service.fallback.DeployServiceApi;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
@Api(description = "应用类接口")
@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST}, origins = "*")
public class ApplicationConsumerController extends BaseController {
  private static Logger logger = LoggerFactory.getLogger(ApplicationConsumerController.class);

  @Autowired
  private ApplicationServiceApi applicationServiceApi;

  @Autowired
  private DeployServiceApi deployServiceApi;

  @RequestMapping(value = "/applicationQuery", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation("列表查询-根据创建人ID查询API部分信息")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "vueJson", value = "提交报文", required = true, paramType = "form")
  })
  public BaseResponse applicationQuery(@RequestParam Map<String, Object> vueJson, @RequestHeader HttpHeaders headers) {
    logger.info("vueJson{}", vueJson);
    JSONObject map = Utility.convert2JsonAndCheckRequiredColumns(vueJson, "");

    BaseResponse<Map> baseResponse = applicationServiceApi.applicationQuery(map);
    logger.info("baseResponse:", baseResponse.getData());
    List<Map<String, Object>> dataList = (List<Map<String, Object>>) baseResponse.getData().get("data");
    Map<String, Object> param = new HashMap<>();

    Map resultMap = new HashMap();
    resultMap.put("dataList", dataList);
    resultMap.put("total", baseResponse.getData().get("total"));
    return baseResponse.success(resultMap);
  }

  /**
   * 新建应用.
   *
   * @param addApplicationVos
   * @param headers
   * @return
   */
  @RequestMapping(
      value = "/application/createApplication",
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation("新建应用")
  public BaseResponse createApplication(@RequestBody List<AddApplicationVo> addApplicationVos,
                                        @RequestHeader HttpHeaders headers) {
    logger.debug("ApplicationConsumerController.createApplication addApplicationVos : {}", addApplicationVos);

    BaseResponse<Map> baseResponse = applicationServiceApi.createApplication(addApplicationVos);

    logger.info("ApplicationConsumerController.createApplication baseResponse : {}", baseResponse.getData());

    return baseResponse;
  }

  /**
   * 应用查询.
   *
   * @param vueJson
   * @return
   */
  @RequestMapping(value = "/application/getApplicationInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  @ApiOperation("应用查询")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "vueJson", value = "查询条件", required = true, paramType = "form")
  })
  public BaseResponse getApplicationInfo(@RequestParam Map<String, Object> vueJson) {
    logger.debug("ApplicationServiceApi.getApplicationInfo vueJson : {}", vueJson);

    Map selectParams = Utility.convert2JsonAndCheckRequiredColumns(vueJson, "");

    BaseResponse<Map> baseResponse = applicationServiceApi.getApplicationInfo(selectParams);
    logger.info("ApplicationServiceApi getDeviceData baseResponse : {}", baseResponse);

    Map resultMap = baseResponse.getData();
    BaseResponse response = BaseResponse.success(resultMap);

    logger.debug("ApplicationServiceApi getApplicationInfo response : {}", response);

    return response;
  }

  /**
   * 应用更新.
   *
   * @param applicationVo
   * @param headers
   * @return
   */
  @RequestMapping(
      value = "/application/updateApplication",
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation("应用更新")
  public BaseResponse updateApplication(@RequestBody AddApplicationVo applicationVo, @RequestHeader HttpHeaders headers) {
    logger.info("ApplicationServiceApi updateApplication applicationVo : {}", applicationVo);

    BaseResponse baseResponse = applicationServiceApi.updateApplication(applicationVo);
    logger.info("ApplicationServiceApi updateApplication baseResponse : {}", baseResponse);

    return baseResponse;
  }

  /**
   * 查询主机信息.
   *
   * @param vueJson
   * @return
   */
  @RequestMapping(value = "/application/getDeployInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  @ApiOperation("查询主机信息")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "vueJson", value = "查询条件", required = true, paramType = "form")
  })
  public BaseResponse getDeployInfo(@RequestParam Map<String, Object> vueJson) {
    logger.debug("ApplicationServiceApi.getDeployInfo vueJson : {}", vueJson);

    Map selectParams = Utility.convert2JsonAndCheckRequiredColumns(vueJson, "");

    BaseResponse<Map> baseResponse = deployServiceApi.getDeployInfo(selectParams);
    logger.info("ApplicationServiceApi getDeployInfo baseResponse : {}", baseResponse);

    Map resultMap = baseResponse.getData();
    BaseResponse response = BaseResponse.success(resultMap);

    logger.debug("ApplicationServiceApi getDeployInfo response : {}", response);

    return response;
  }

  /**
   * 修改主机信息.
   *
   * @param deployVo
   * @param headers
   * @return
   */
  @RequestMapping(
      value = "/application/updateDeployInfo",
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation("修改主机信息")
  public BaseResponse updateDeployInfo(@RequestBody DeployVo deployVo, @RequestHeader HttpHeaders headers) {
    logger.info("ApplicationServiceApi updateDeployInfo deployVo : {}", deployVo);

    BaseResponse baseResponse = deployServiceApi.updateDeployInfo(deployVo);
    logger.info("ApplicationServiceApi updateDeployInfo baseResponse : {}", baseResponse);

    return baseResponse;
  }

  /**
   * 应用权限更新.
   *
   * @param applicationVo
   * @param headers
   * @return
   */
  @RequestMapping(
      value = "/application/updateApplicationRolePermission",
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation("应用权限更新")
  public BaseResponse updateApplicationRolePermission(@RequestBody AddApplicationVo applicationVo, @RequestHeader HttpHeaders headers) {
    logger.info("ApplicationServiceApi updateApplicationRolePermission applicationVo : {}", applicationVo);

    BaseResponse baseResponse = applicationServiceApi.updateApplicationRolePermission(applicationVo);
    logger.info("ApplicationServiceApi updateApplicationRolePermission baseResponse : {}", baseResponse);

    return baseResponse;
  }

  /**
   * 应用部署.
   *
   * @return
   */
  @RequestMapping(
      value = "/application/deployApplication",
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation("应用部署")
  public BaseResponse deployApplication(@RequestBody DeployVo deployVo) {
    logger.info("ApplicationServiceApi deployApplication deployVo : {}", deployVo);

    BaseResponse baseResponse = applicationServiceApi.deployApplication();
    logger.info("ApplicationServiceApi deployApplication deployVo : {}", baseResponse);

    return baseResponse;
  }

  /**
   * 应用启动/停止.
   *
   * @return
   */
  @RequestMapping(
      value = "/application/startOrStopApplication",
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation("应用启动/停止")
  public BaseResponse startOrStopApplication(@RequestBody DeployVo deployVo) {
    logger.info("ApplicationServiceApi startOrStopApplication deployVo : {}", deployVo);

    BaseResponse baseResponse = applicationServiceApi.startOrStopApplication(deployVo.getDeployId());
    logger.info("ApplicationServiceApi startOrStopApplication deployVo : {}", baseResponse);

    return baseResponse;
  }
}
