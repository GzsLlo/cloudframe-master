package com.ai.cloudframe.provider.cmc.mapper;

import com.ai.cloudframe.provider.cmc.entity.AirMonitor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-20
 */
public interface AirMonitorMapper extends BaseMapper<AirMonitor> {

  /**
   * 监控配置查询.
   *
   * @param page
   * @param param
   * @return
   */
  IPage<AirMonitor> getAirMonitorInfo(@Param("pg") Page page, @Param("param") Map param);

}
