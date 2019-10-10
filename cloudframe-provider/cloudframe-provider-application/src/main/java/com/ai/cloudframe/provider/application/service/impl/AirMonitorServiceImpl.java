package com.ai.cloudframe.provider.application.service.impl;


import com.ai.cloudframe.provider.api.sys.model.vo.AirMonitorVo;
import com.ai.cloudframe.provider.api.sys.model.vo.AirQualityVo;
import com.ai.cloudframe.provider.application.entity.AirMonitor;
import com.ai.cloudframe.provider.application.mapper.AirMonitorMapper;
import com.ai.cloudframe.provider.application.service.IAirMonitorService;
import com.ai.cloudframe.provider.application.service.IAirQualityService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-20
 */
@Service
public class AirMonitorServiceImpl extends ServiceImpl<AirMonitorMapper, AirMonitor> implements IAirMonitorService {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private IAirQualityService airQualityService;

  @Autowired
  IAirMonitorService iAirMonitorService;

  /**
   * 监控配置查询.
   *
   * @param param
   * @return
   */
  @Override
  public IPage<AirMonitorVo> getAirMonitorInfo(Map param) {
    logger.debug("AirMonitorServiceImpl getAirMonitorInfo param : {}", param);

    Long pageNum = (null != param.get("page")) ? Long.valueOf(param.get("page").toString()) : null;
    Long pageSize = (null != param.get("pageSize"))
        ? Long.valueOf(param.get("pageSize").toString()) : null;

    Page<AirMonitorVo> page = new Page<>(pageNum, pageSize);

    IPage<AirMonitorVo> airMonitors = this.baseMapper.getAirMonitorInfo(page, param);

    logger.debug("AirMonitorServiceImpl getAirMonitorInfo airMonitors : {}", airMonitors);

    return airMonitors;
  }

  /**
   * 修改监控配置.
   *
   * @param airMonitorVo
   * @return
   */
  @Override
  public boolean updateAirMonitor(AirMonitorVo airMonitorVo) {
    logger.debug("AirMonitorServiceImpl.updateAirMonitor airMonitorVo : {}", airMonitorVo);

//    int ret = this.baseMapper.updateAirMonitor(airMonitorVo);
    String airMonitorId = "26b709c2-6bd3-45b3-8c96-0c8403e40ea3";
    AirMonitor airMonitor = new AirMonitor();
    BeanUtils.copyProperties(airMonitorVo, airMonitor);
    airMonitor.setAirMonitorId(airMonitorId);
    boolean monitorFlag = iAirMonitorService.saveOrUpdate(airMonitor);
    logger.debug("AddApplicationServiceImpl.updateAirMonitor monitorFlag : {}", monitorFlag);

    return monitorFlag;
  }

  /**
   * 监控告警.
   *
   * @param param
   * @return
   */
  @Override
  public IPage<AirQualityVo> airMonitorAlarm(Map param) {
    logger.debug("AirMonitorServiceImpl.airMonitorAlarm param : {}", param);
    logger.info("开始获取设备采集数据...............");
    logger.info("开始获取告警配置..............");
    IPage<AirMonitorVo> airMonitorVoPage = getAirMonitorInfo(param);
    List<AirMonitorVo> airMonitorVos = airMonitorVoPage.getRecords();
    AirMonitorVo airMonitorVo = (null != airMonitorVos && !airMonitorVos.isEmpty())
        ? airMonitorVos.get(0) : new AirMonitorVo();
    logger.info("告警配置 : {}", airMonitorVo);
    String dimension = airMonitorVo.getDimension().trim();
    //0 大于阈值报警, 1 小于阈值报警
    String thresholdType = airMonitorVo.getThresholdType().trim();
    String pm = airMonitorVo.getThreshold().trim();
    param.put("thresholdType", thresholdType);
    param.put("PM", pm);

    logger.info("开始检查设备是否达到告警数值................");
    IPage<AirQualityVo> airMonitors;
    if ("0".equals(dimension)) {//根据时间段查询
      param.put("startTime", airMonitorVo.getStartTime());
      param.put("endTime", airMonitorVo.getEndTime());
      logger.debug("AirMonitorServiceImpl.airMonitorAlarm getAirQualityByTimeParam : {}", param);
      airMonitors = airQualityService.getAirQualityByTime(param);
    } else {//根据地域查询
      String region = airMonitorVo.getRegion();
      List regions = (!StringUtils.isEmpty(region)) ? Arrays.asList(region.split(",")) : new ArrayList();
      param.put("regions", regions);
      logger.debug("AirMonitorServiceImpl.airMonitorAlarm getAirQualityByAreaParam : {}", param);
      airMonitors = airQualityService.getAirQualityByArea(param);
    }

    logger.info("需要告警的设备 : {}", airMonitors);
    logger.debug("AirMonitorServiceImpl.airMonitorAlarm airMonitors : {}", airMonitors);
    return airMonitors;
  }
}
