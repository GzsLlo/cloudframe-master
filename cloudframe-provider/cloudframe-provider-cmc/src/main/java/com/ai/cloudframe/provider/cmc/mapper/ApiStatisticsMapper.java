package com.ai.cloudframe.provider.cmc.mapper;

import com.ai.cloudframe.provider.api.sys.model.dto.ChargeOffDto;
import com.ai.cloudframe.provider.cmc.entity.ApiStatistics;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-17
 */
public interface ApiStatisticsMapper extends BaseMapper<ApiStatistics> {
  List<ChargeOffDto> getUseCount(@Param("map") Map map);

 int updateIschargeoff(@Param("map") Map map);
}
