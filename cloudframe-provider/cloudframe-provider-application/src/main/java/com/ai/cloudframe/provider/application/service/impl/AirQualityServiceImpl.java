package com.ai.cloudframe.provider.application.service.impl;

import com.ai.cloudframe.provider.api.sys.model.vo.AirQualityVo;
import com.ai.cloudframe.provider.application.entity.AirQuality;
import com.ai.cloudframe.provider.application.mapper.AirQualityMapper;
import com.ai.cloudframe.provider.application.service.IAirQualityService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-19
 */
@Service
public class AirQualityServiceImpl extends ServiceImpl<AirQualityMapper, AirQuality> implements IAirQualityService {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  /**
   * 设备采集数据查询.
   *
   * @param param
   * @return
   */
  @Override
  public IPage<AirQualityVo> getDeviceData(Map param) {

    logger.debug("AirQualityServiceImpl getDeviceData param : {}", param);

    Long pageNum = (null != param.get("page")) ? Long.valueOf(param.get("page").toString()) : null;
    Long pageSize = (null != param.get("pageSize"))
        ? Long.valueOf(param.get("pageSize").toString()) : null;

    Page<AirQualityVo> page = new Page<>(pageNum, pageSize);

    IPage<AirQualityVo> airQualityVos = this.baseMapper.getDeviceData(page, param);
    logger.info("数据呈现........ : {}", airQualityVos);
    logger.info("数据呈现........ : {}", airQualityVos);
    logger.debug("AirQualityServiceImpl getDeviceData airQualityVos : {}", airQualityVos);

    return airQualityVos;
  }

  /**
   * 根据时间段监控采集数据.
   *
   * @param param
   * @return
   */
  @Override
  public IPage<AirQualityVo> getAirQualityByTime(Map param) {
    logger.info("AirQualityServiceImpl getAirQualityByTime param : {}", param);

    Long pageNum = (null != param.get("page")) ? Long.valueOf(param.get("page").toString()) : null;
    Long pageSize = (null != param.get("pageSize"))
        ? Long.valueOf(param.get("pageSize").toString()) : null;

    Page<AirQualityVo> page = new Page<>(pageNum, pageSize);

    IPage<AirQualityVo> airQualityVos = this.baseMapper.getAirQualityByTime(page, param);

    logger.info("AirQualityServiceImpl getAirQualityByTime airQualityVos : {}", airQualityVos);

    return airQualityVos;
  }

  /**
   * 根据地域监控采集数据.
   *
   * @param param
   * @return
   */
  @Override
  public IPage<AirQualityVo> getAirQualityByArea(Map param) {
    logger.info("AirQualityServiceImpl getAirQualityByArea param : {}", param);

    Long pageNum = (null != param.get("page")) ? Long.valueOf(param.get("page").toString()) : null;
    Long pageSize = (null != param.get("pageSize"))
        ? Long.valueOf(param.get("pageSize").toString()) : null;

    Page<AirQualityVo> page = new Page<>(pageNum, pageSize);

    IPage<AirQualityVo> airQualityVos = this.baseMapper.getAirQualityByArea(page, param);

    logger.info("AirQualityServiceImpl getAirQualityByArea airQualityVos : {}", airQualityVos);

    return airQualityVos;
  }


}
