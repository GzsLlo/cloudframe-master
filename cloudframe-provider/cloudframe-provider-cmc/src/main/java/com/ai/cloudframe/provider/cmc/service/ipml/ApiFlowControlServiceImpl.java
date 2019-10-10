package com.ai.cloudframe.provider.cmc.service.ipml;

import com.ai.cloudframe.provider.cmc.entity.ApiFlowControl;
import com.ai.cloudframe.provider.cmc.mapper.ApiFlowControlMapper;
import com.ai.cloudframe.provider.cmc.service.IApiFlowControlService;
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
public class ApiFlowControlServiceImpl extends ServiceImpl<ApiFlowControlMapper, ApiFlowControl> implements IApiFlowControlService {

  @Autowired(required = false)
  private ApiFlowControlMapper apiFlowControlMapper = null;

  @Override
  public int updateApiFlowById(ApiFlowControl control) {
    return apiFlowControlMapper.updateApiFlowById(control);
  }
}
