package com.ai.cloudframe.provider.cmc.service.ipml;

import com.ai.cloudframe.provider.api.sys.model.dto.ChargeOffDto;
import com.ai.cloudframe.provider.cmc.entity.ApiStatistics;
import com.ai.cloudframe.provider.cmc.mapper.ApiStatisticsMapper;
import com.ai.cloudframe.provider.cmc.service.IApiStatisticsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-17
 */
@Service
public class ApiStatisticsServiceImpl extends ServiceImpl<ApiStatisticsMapper, ApiStatistics> implements IApiStatisticsService {

  @Override
  public List<ChargeOffDto> getUseCount(Map map) {
    return this.baseMapper.getUseCount(map);
  }

  @Override
  public int updateIschargeoff(Map map) {
    return this.baseMapper.updateIschargeoff(map);
  }

}
