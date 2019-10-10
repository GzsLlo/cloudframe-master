/**
 * Copyright 2019 asiainfo Inc.
 **/

package com.ai.cloudframe.web.controller;

import com.ai.cloudframe.common.base.support.BaseController;
import com.ai.cloudframe.common.base.util.XmlUtil;
import com.ai.cloudframe.web.Util.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: shenbin
 * @CreateDate: [2019/7/17 19:02]
 * @Version: [v1.0]
 */
@RestController
@Api(description = "测试接口A")
@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST}, origins = "*")
public class TestApiAController extends BaseController {

  private static Logger logger = LoggerFactory.getLogger(TestApiAController.class);

  @RequestMapping(value = "/jsonApiA", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public String jsonApiA(@RequestBody String in, @RequestHeader HttpHeaders headers) {
    logger.info("api A start.");
    logger.info("input:{}", in);
    Map<String, Object> resultMap = new HashMap<>();
    resultMap.put("resultA", "123");
    Map<String, Object> mapA = new HashMap<>();
    resultMap.put("mapA", mapA);
    mapA.put("strA", "str");
    mapA.put("intA", 111);
    String result = JSON.toJSONString(resultMap);
    logger.info("api A end.");

    return result;
  }

  @RequestMapping(value = "/jsonApiB", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public String jsonApiB(@RequestBody String in, @RequestHeader HttpHeaders headers) {
    logger.info("api A start.");
    logger.info("input:{}", in);
    Map<String, Object> resultMap = new HashMap<>();
    resultMap.put("resultB", "7788123");
    Map<String, Object> mapB = new HashMap<>();
    resultMap.put("mapB", mapB);
    mapB.put("strB", "strB");
    mapB.put("intB", 2222);
    String result = JSON.toJSONString(resultMap);
    logger.info("api B end.");

    return result;
  }

  @RequestMapping(value = "/xmlApiA", method = RequestMethod.POST, produces = MediaType.APPLICATION_XML_VALUE,
      consumes = MediaType.APPLICATION_XML_VALUE)
  public String xmlApiA(@RequestBody String in, @RequestHeader HttpHeaders headers) {
    logger.info("api A start.");
    logger.info("input:{}", in);
    Map<String, Object> root = new HashMap<>();
    Map<String, Object> resultMap = new HashMap<>();
    root.put("root", resultMap);
    resultMap.put("resultA", "123");
    Map<String, Object> mapA = new HashMap<>();
    resultMap.put("mapA", mapA);
    mapA.put("strA", "str");
    mapA.put("intA", 111);
    String result = null;
    try {
      result = XmlUtil.map2xml(root, "rootA").asXML();
    } catch (DocumentException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    logger.info("api A end.");

    return result;
  }

  @RequestMapping(value = "/xmlApiB", method = RequestMethod.POST, produces = MediaType.APPLICATION_XML_VALUE,
      consumes = MediaType.APPLICATION_XML_VALUE)
  public String xmlApiB(@RequestBody String in, @RequestHeader HttpHeaders headers) {
    logger.info("api B start.");
    logger.info("input:{}", in);
    Map<String, Object> root = new HashMap<>();
    Map<String, Object> resultMap = new HashMap<>();
    root.put("root", resultMap);
    resultMap.put("resultB", "7788123");
    Map<String, Object> mapB = new HashMap<>();
    resultMap.put("mapA", mapB);
    mapB.put("strB", "strB");
    mapB.put("intB", 2222);
    String result = null;
    try {
      result = XmlUtil.map2xml(root, "rootB").asXML();
    } catch (DocumentException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    logger.info("api B end.");

    return result;
  }

  @ApiOperation("通过json格式的MSISDN查询终端编号")
  @RequestMapping(value = "/api/getDeviceIdByMsisdn", method = RequestMethod.POST)
  @ResponseBody
  public Map getDeviceIdByMsisdn(@RequestBody String msisdn) {
    logger.info("TestApiAController.getDeviceIdByMsisdn msisdn : {}", msisdn);
    Map returnMap = new HashMap();
    returnMap.put("resultCode", "0000");
    returnMap.put("resultDesc", "success");
    returnMap.put("deviceId", "100101001");
    return returnMap;
  }

  @ApiOperation("通过json格式的终端编号查询归属地市区域")
  @RequestMapping(value = "/api/getAreaByDeviceId",  method = RequestMethod.POST)
  @ResponseBody
  public Map getAreaByDeviceId(@RequestBody String deviceId) throws IOException {
    logger.info("TestApiAController.getAreaByDeviceId deviceId : {}", deviceId);

    Map returnMap = new HashMap();
    returnMap.put("resultCode", "0000");
    returnMap.put("resultDesc", "success");
    returnMap.put("cityId", "0025");
    returnMap.put("cityName", "南京");
    returnMap.put("areaId", "01");
    returnMap.put("areaName", "鼓楼区");
    return returnMap;
  }

  @ApiOperation("通过XML格式的MSISDN查询终端编号")
  @RequestMapping(value = "/api/getDeviceIdByXmlMsisdn", produces = "application/x-www-form-urlencoded; charset=UTF-8"
      , method = RequestMethod.POST)
//  @ResponseBody
  public String getDeviceIdByXmlMsisdn(@RequestBody String msisdn) throws IOException {
    logger.info("TestApiAController.getDeviceIdByXmlMsisdn msisdn : {}", msisdn);

    Map deviceData = new HashMap();
    try {
      deviceData = DateUtil.string2XmlByDom4j(msisdn);
    } catch (Exception e) {
      logger.error("param error e : {}", e);
    }

    logger.debug("getAreaByDeviceId deviceData : {}", deviceData);

    if (StringUtils.isEmpty(deviceData.get("msisdn"))) {
      throw new IOException("参数错误");
    }

    String rtnStr =
        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
            "<device>" +
            "<result>" +
            "<resultCode>0000</resultCode>" +
            "<resultDesc>success</resultDesc>" +
            "</result>" +
            "<deviceId>100101001</deviceId>" +
            "</device>";
    return rtnStr;
  }

  @ApiOperation("通过XML格式的终端编号查询归属地市区域")
  @RequestMapping(value = "/api/getAreaByXmlDeviceId", method = RequestMethod.POST)
//  @ResponseBody
  public String getAreaByXmlDeviceId(@RequestBody String deviceId) throws IOException {
    logger.info("TestApiAController.getAreaByXmlDeviceId deviceId : {}", deviceId);


    Map deviceData = new HashMap();
    try {
      deviceData = DateUtil.string2XmlByDom4j(deviceId);
    } catch (Exception e) {
      logger.error("param error e : {}", e);
    }

    logger.debug("getAreaByDeviceId deviceData : {}", deviceData);

    if (StringUtils.isEmpty(deviceData.get("deviceId"))) {
      throw new IOException("参数错误");
    }

    String rtnStr =
        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
            "<device>" +
            "<result>" +
            "<resultCode>0000</resultCode>" +
            "<resultDesc>success</resultDesc>" +
            "</result>" +
            "<position>" +
            "<city>" +
            "<cityCode>0025</cityCode>" +
            "<cityName>南京</cityName>" +
            "</city>" +
            "<area>" +
            "<areaCode>01</areaCode>" +
            "<areaName>鼓楼区</areaName>" +
            "</area>" +
            "</position>" +
            "</device>";
    return rtnStr;
  }
}
