package com.ai.cloudframe.provider.cmc.service;

import com.ai.cloudframe.provider.cmc.entity.ApiFlowControl;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-18
 */
public interface IApiFlowControlService extends IService<ApiFlowControl> {

  int updateApiFlowById(@RequestBody ApiFlowControl control);

}
