package com.ai.cloudframe.provider.application.controller;


import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.common.base.support.BaseController;
import com.ai.cloudframe.provider.api.sys.model.vo.AirMonitorVo;
import com.ai.cloudframe.provider.api.sys.model.vo.AirQualityVo;
import com.ai.cloudframe.provider.api.sys.service.AirMonitorServiceApi;

import com.ai.cloudframe.provider.application.service.IAirMonitorService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-19
 */
@RestController
@Api(description = "监控配置相关服务", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AirMonitorProviderController extends BaseController implements AirMonitorServiceApi {

  private static Logger logger = LoggerFactory.getLogger(AirMonitorProviderController.class);

  @Autowired
  private IAirMonitorService iAirMonitorService;

  /**
   * 查询监控配置.
   *
   * @param param
   * @return
   */
  @Override
  public BaseResponse<Map> getAirMonitorInfo(@RequestBody Map param) {
    logger.info("AirMonitorProviderController.getAirMonitorInfo param : {}", param);

    IPage<AirMonitorVo> airMonitorList = iAirMonitorService.getAirMonitorInfo(param);
    logger.debug("AirMonitorProviderController.getApplicationInfo airMonitorList : {}", airMonitorList);

    Map resultMap = new HashMap();
    resultMap.put("records", airMonitorList.getRecords());
    resultMap.put("total", airMonitorList.getTotal());
    resultMap.put("page", (null != param.get("page")) ? Long.valueOf(param.get("page").toString()) : null);
    resultMap.put("size", airMonitorList.getSize());
    logger.debug("AirMonitorProviderController.getAirMonitorInfo resultMap : {}", resultMap);

    return BaseResponse.success(resultMap);
  }

  /**
   * 修改监控配置.
   *
   * @param airMonitorVo
   * @return
   */
  @Override
  public BaseResponse<Map> updateAirMonitor(AirMonitorVo airMonitorVo) {
    logger.debug("AirMonitorProviderController.getAirMonitorInfo airMonitorVo : {}", airMonitorVo);

    boolean flag = iAirMonitorService.updateAirMonitor(airMonitorVo);
    logger.debug("AirMonitorProviderController.getAirMonitorInfo flag : {}", flag);

    return flag == true ? BaseResponse.success() : BaseResponse.fail();
  }

  /**
   * 监控告警.
   *
   * @param params
   * @return
   */
  @Override
  public BaseResponse<Map> airMonitorAlarm(Map params) {
    logger.debug("AirMonitorProviderController.airMonitorAlarm param : {}", params);
    logger.info("开始监控告警扫描.........................");
    IPage<AirQualityVo> airQualityList = iAirMonitorService.airMonitorAlarm(params);
    logger.debug("AirMonitorProviderController.airMonitorAlarm airQualityList : {}", airQualityList);

    Map resultMap = new HashMap();
    resultMap.put("records", airQualityList.getRecords());
    resultMap.put("total", airQualityList.getTotal());
    resultMap.put("page", (null != params.get("page")) ? Long.valueOf(params.get("page").toString()) : null);
    resultMap.put("size", airQualityList.getSize());
    logger.debug("AirMonitorProviderController.airMonitorAlarm resultMap : {}", resultMap);
    logger.info("监控告警扫描结束.........................");
    return BaseResponse.success(resultMap);
  }
}
