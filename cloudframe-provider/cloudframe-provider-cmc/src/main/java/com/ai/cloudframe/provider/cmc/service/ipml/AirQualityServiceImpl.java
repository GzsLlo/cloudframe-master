package com.ai.cloudframe.provider.cmc.service.ipml;

import com.ai.cloudframe.provider.api.sys.model.vo.AirQualityVo;
import com.ai.cloudframe.provider.cmc.entity.AirQuality;
import com.ai.cloudframe.provider.cmc.mapper.AirQualityMapper;
import com.ai.cloudframe.provider.cmc.service.IAirQualityService;
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

    logger.debug("AirQualityServiceImpl getDeviceData airQualityVos : {}", airQualityVos);

    return airQualityVos;
  }


}
