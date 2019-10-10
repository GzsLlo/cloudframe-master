package com.ai.cloudframe.provider.cmc.controller;


import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.common.base.support.BaseController;
import com.ai.cloudframe.provider.api.sys.model.vo.AirMonitorVo;
import com.ai.cloudframe.provider.api.sys.service.AirMonitorServiceApi;
import com.ai.cloudframe.provider.cmc.entity.AirMonitor;
import com.ai.cloudframe.provider.cmc.service.IAirMonitorService;
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

    IPage<AirMonitor> airMonitorList = iAirMonitorService.getAirMonitorInfo(param);
    logger.debug("AirMonitorProviderController.getApplicationInfo airMonitorList : {}", airMonitorList);

    Map resultMap = new HashMap();
    resultMap.put("records", airMonitorList.getRecords());
    resultMap.put("total", airMonitorList.getTotal());
    resultMap.put("page", (null != param.get("page")) ? Long.valueOf(param.get("page").toString()) : null);
    resultMap.put("size", airMonitorList.getSize());
    logger.debug("AirMonitorProviderController.getAirMonitorInfo resultMap : {}", resultMap);

    return BaseResponse.success(resultMap);
  }

  @Override
  public BaseResponse<Map> updateAirMonitor(AirMonitorVo airMonitorVo) {
    return null;
  }

  @Override
  public BaseResponse<Map> airMonitorAlarm(Map params) {
    return null;
  }
}
