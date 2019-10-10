package com.ai.cloudframe.provider.cmc.service;

import com.ai.cloudframe.provider.cmc.entity.AirMonitor;
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
  IPage<AirMonitor> getAirMonitorInfo(Map param);

}
