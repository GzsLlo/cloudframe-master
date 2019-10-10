package com.ai.cloudframe.provider.cmc.mapper;

import com.ai.cloudframe.provider.api.sys.model.vo.AirQualityVo;
import com.ai.cloudframe.provider.cmc.entity.AirQuality;
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
 * @since 2019-07-19
 */
public interface AirQualityMapper extends BaseMapper<AirQuality> {

  /**
   * 设备采集数据查询.
   *
   * @param page
   * @param param
   * @return
   */
  IPage<AirQualityVo> getDeviceData(@Param("pg") Page page, @Param("param") Map param);

}
