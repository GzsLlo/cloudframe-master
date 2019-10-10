package com.ai.cloudframe.provider.cmc.service.ipml;

import com.ai.cloudframe.provider.cmc.entity.AirMonitor;
import com.ai.cloudframe.provider.cmc.mapper.AirMonitorMapper;
import com.ai.cloudframe.provider.cmc.service.IAirMonitorService;
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
 * @since 2019-07-20
 */
@Service
public class AirMonitorServiceImpl extends ServiceImpl<AirMonitorMapper, AirMonitor> implements IAirMonitorService {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  /**
   * 监控配置查询.
   *
   * @param param
   * @return
   */
  @Override
  public IPage<AirMonitor> getAirMonitorInfo(Map param) {
    logger.debug("AirMonitorServiceImpl getAirMonitorInfo param : {}", param);

    Long pageNum = (null != param.get("page")) ? Long.valueOf(param.get("page").toString()) : null;
    Long pageSize = (null != param.get("pageSize"))
        ? Long.valueOf(param.get("pageSize").toString()) : null;

    Page<AirMonitor> page = new Page<>(pageNum, pageSize);

    IPage<AirMonitor> airMonitors = this.baseMapper.getAirMonitorInfo(page, param);

    logger.debug("AirMonitorServiceImpl getAirMonitorInfo airMonitors : {}", airMonitors);

    return airMonitors;
  }

}
