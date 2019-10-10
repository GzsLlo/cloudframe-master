package com.ai.cloudframe.provider.cmc.service.ipml;

import com.ai.cloudframe.provider.cmc.entity.ApiParam;
import com.ai.cloudframe.provider.cmc.mapper.ApiMapper;
import com.ai.cloudframe.provider.cmc.mapper.ApiParamMapper;
import com.ai.cloudframe.provider.cmc.service.IApiParamService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-18
 */
@Service
public class ApiParamServiceImpl extends ServiceImpl<ApiParamMapper, ApiParam> implements IApiParamService {


  @Autowired(required = false)
  private ApiParamMapper apiParamMapper = null;

  @Override
  public int updateApiParam(ApiParam param) {
    return apiParamMapper.updateApiParam(param);
  }
}
