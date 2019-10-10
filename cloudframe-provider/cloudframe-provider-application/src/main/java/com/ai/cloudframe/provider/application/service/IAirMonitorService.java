package com.ai.cloudframe.provider.application.service;


import com.ai.cloudframe.provider.api.sys.model.vo.AirMonitorVo;
import com.ai.cloudframe.provider.api.sys.model.vo.AirQualityVo;
import com.ai.cloudframe.provider.application.entity.AirMonitor;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-20
 */
public interface IAirMonitorService extends IService<AirMonitor> {

  /**
   * 查询监控配置.
   *
   * @param param
   * @return
   */
  IPage<AirMonitorVo> getAirMonitorInfo(Map param);

  /**
   * 修改监控配置.
   *
   * @param airMonitorVo
   * @return
   */
  boolean updateAirMonitor(AirMonitorVo airMonitorVo);

  /**
   * 监控告警.
   *
   * @param param
   * @return
   */
  IPage<AirQualityVo> airMonitorAlarm(Map param);


}
