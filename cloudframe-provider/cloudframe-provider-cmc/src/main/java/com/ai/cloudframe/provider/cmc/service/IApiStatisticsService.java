package com.ai.cloudframe.provider.cmc.service;

import com.ai.cloudframe.provider.api.sys.model.dto.ChargeOffDto;
import com.ai.cloudframe.provider.cmc.entity.ApiStatistics;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-17
 */
public interface IApiStatisticsService extends IService<ApiStatistics> {
  List<ChargeOffDto> getUseCount(Map map);


  int updateIschargeoff(Map map);
}
