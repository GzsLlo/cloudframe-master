package com.ai.cloudframe.provider.application.controller;

import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.common.base.support.BaseController;
import com.ai.cloudframe.provider.api.sys.model.dto.AirQualityDto;
import com.ai.cloudframe.provider.api.sys.model.vo.AirQualityVo;
import com.ai.cloudframe.provider.api.sys.service.DeviceDataAccessServiceApi;
import com.ai.cloudframe.provider.application.entity.AirQuality;
import com.ai.cloudframe.provider.application.service.IAirQualityService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-17
 */
@RestController
@Api(description = "数据采集相关接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DeviceDataAccessProviderController extends BaseController implements DeviceDataAccessServiceApi {

  private Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private IAirQualityService iAirQualityService;

  /**
   * 设备数据采集.
   *
   * @param airQualityDto
   * @return
   */
  @Override
  public BaseResponse deviceDataAccess(AirQualityDto airQualityDto) {

    logger.info("开始设备数据采集.................");
    logger.info("DeviceDataAccessController.deviceDataAccess airQualityDto = {}", airQualityDto);
    UUID uuid = UUID.randomUUID();
    String airId = uuid.toString();
    airQualityDto.setAirId(airId);

    AirQuality airQuality = new AirQuality();
    BeanUtils.copyProperties(airQualityDto, airQuality);
    logger.info("设备数据采集完成.........................");

    logger.info("开始数据加载.....................");
    logger.info("DeviceDataAccessProviderController.deviceDataAccess airQuality : {}", airQuality);
    boolean flag = iAirQualityService.save(airQuality);
    logger.info("数据加载完成..........................");
    return flag == true ? BaseResponse.success() : BaseResponse.fail();
  }

  /**
   * 设备采集数据查询.
   *
   * @param param
   * @return
   */
  @Override
  public BaseResponse<Map> getDeviceData(@RequestBody Map param) {
    logger.debug("DeviceDataAccessController.getDeviceData param : {}", param);

    logger.info("开始获取设备采集数据................");
    IPage<AirQualityVo> airQualityVoList = iAirQualityService.getDeviceData(param);
    logger.debug("DeviceDataAccessController.getDeviceData airQualityVoList : {}", airQualityVoList);

    Map resultMap = new HashMap();
    resultMap.put("records", airQualityVoList.getRecords());
    resultMap.put("total", airQualityVoList.getTotal());
    resultMap.put("page", (null != param.get("page")) ? Long.valueOf(param.get("page").toString()) : null);
    resultMap.put("size", airQualityVoList.getSize());

    logger.info("获取设备采集数据结束................");
    return BaseResponse.success(resultMap);
  }
}
