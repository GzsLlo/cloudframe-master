package com.ai.cloudframe.web.controller;

import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.common.base.support.BaseController;
import com.ai.cloudframe.common.base.util.Utility;
import com.ai.cloudframe.provider.api.sys.model.dto.AirQualityDto;
import com.ai.cloudframe.provider.api.sys.service.DeviceDataAccessServiceApi;
import com.ai.cloudframe.web.Util.DateUtil;

import com.alibaba.fastjson.JSONObject;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@Api(description = "数据采集接口")
@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST}, origins = "*")
public class DeviceDataAccessConsumerController extends BaseController {

  @Autowired
  DeviceDataAccessServiceApi deviceDataAccessServiceApi;

  @ApiOperation("设备数据采集")
  @RequestMapping(value = "/airQuality/deviceDataAccess", method = RequestMethod.POST)
  @ResponseBody
  public Map deviceDataAccess(HttpServletRequest request) {

    String params = request.getParameter("deviceData");
    logger.info("DeviceDataAccessConsumerController.deviceDataAccess params : {}", params);

    Map deviceData = new HashMap();
    Map returnMap = new HashMap();
    if (!StringUtils.isEmpty(params) && params.contains("{")) {
      logger.debug("json param");
      deviceData = JSONObject.parseObject(params);
    } else if (!StringUtils.isEmpty(params) && params.contains("<")) {
      logger.debug("xml param");
      try {
        deviceData = DateUtil.string2XmlByDom4j(params);
      } catch (Exception e) {
        logger.error("parse xml error : {}", e);
      }
    } else {
      returnMap.put("resultCode", "E001");
      returnMap.put("resultDesc", "请输入正确格式的json或xml参数");
    }

    logger.info("DeviceDataAccessConsumerController.deviceDataAccess deviceData : {}", deviceData);

    AirQualityDto airQualityDto = putAirQualityDto(deviceData);
    BaseResponse baseResponse = deviceDataAccessServiceApi.deviceDataAccess(airQualityDto);
    returnMap.put("resultCode", baseResponse.getCode());
    returnMap.put("resultDesc", baseResponse.getMessage());
    logger.info("DeviceDataAccessConsumerController.deviceDataAccess baseResponse : {}", baseResponse);
    return returnMap;
  }

  /**
   * 填值.
   *
   * @param deviceData
   * @return
   */
  private AirQualityDto putAirQualityDto(Map deviceData) {
    AirQualityDto airQualityDto = new AirQualityDto();
    LocalDateTime collectDate = null != (deviceData.get("collectDate"))
        ? DateUtil.string2LocalDateTime(deviceData.get("collectDate").toString(), "") : null;
    airQualityDto.setAirId(null != (deviceData.get("airId")) ? deviceData.get("airId").toString() : null);
    airQualityDto.setDeviceId(null != (deviceData.get("deviceId")) ? deviceData.get("deviceId").toString() : null);
    airQualityDto.setCityCode(null != (deviceData.get("cityCode")) ? deviceData.get("cityCode").toString() : null);
    airQualityDto.setAreaCode(null != (deviceData.get("areaCode")) ? deviceData.get("areaCode").toString() : null);
    airQualityDto.setAreaName(null != (deviceData.get("areaName")) ? deviceData.get("areaName").toString() : null);
    airQualityDto.setCollectDate(collectDate);
    airQualityDto.setPm(null != (deviceData.get("PM")) ? deviceData.get("PM").toString() : null);

    return airQualityDto;
  }

  /**
   * 数据呈现.
   *
   * @param vueJson
   * @param headers
   * @return
   */
  @RequestMapping(value = "/airQuality/getDeviceData", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  @ApiOperation("数据呈现")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "vueJson", value = "查询条件", required = true, paramType = "form")
  })
  public BaseResponse getDeviceData(@RequestParam Map<String, Object> vueJson, @RequestHeader HttpHeaders headers) {

    logger.debug("DeviceDataAccessConsumerController.getDeviceData param : {}", vueJson);

    Map selectParams = Utility.convert2JsonAndCheckRequiredColumns(vueJson, "");

    BaseResponse<Map> baseResponse = deviceDataAccessServiceApi.getDeviceData(selectParams);
    logger.info("DeviceDataAccessConsumerController getDeviceData baseResponse : {}", baseResponse);

    Map resultMap = baseResponse.getData();
    List<Map> records = (null != resultMap.get("records")) ? (List) resultMap.get("records") : new ArrayList<>();

    List cityList = new ArrayList();
    List dataList = new ArrayList();

    for (Map each : records) {
      if (cityList.contains(each.get("cityName"))) {
        Map temp = (Map) dataList.get(cityList.indexOf(each.get("cityName")));
        List data = (List) temp.get("data");
        data.add(each);
      } else {
        cityList.add(each.get("cityName"));
        Map dataMap = new HashMap();
        dataMap.put("cityName", each.get("cityName"));
        List eachList = new ArrayList();
        eachList.add(each);
        dataMap.put("data", eachList);
        dataList.add(dataMap);
      }
    }

    resultMap.put("records", dataList);
    BaseResponse response = BaseResponse.success(resultMap);

    logger.debug("DeviceDataAccessConsumerController getDeviceData response : {}", response);

    return response;
  }

}
