package com.ai.cloudframe.provider.application.service;

import com.ai.cloudframe.provider.api.sys.model.vo.AirQualityVo;
import com.ai.cloudframe.provider.application.entity.AirQuality;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-19
 */
public interface IAirQualityService extends IService<AirQuality> {

  /**
   * 设备采集数据查询.
   *
   * @param param
   * @return
   */
  IPage<AirQualityVo> getDeviceData(Map param);

  /**
   * 根据时间段监控采集数据.
   *
   * @param param
   * @return
   */
  IPage<AirQualityVo> getAirQualityByTime(Map param);

  /**
   * 根据地域监控采集数据.
   *
   * @param param
   * @return
   */
  IPage<AirQualityVo> getAirQualityByArea(Map param);

}
