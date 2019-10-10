package com.ai.cloudframe.provider.cmc.service;

import com.ai.cloudframe.provider.api.sys.model.vo.DeployVo;
import com.ai.cloudframe.provider.cmc.entity.Deploy;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-24
 */
public interface IDeployService extends IService<Deploy> {

  /**
   * 主机信息查询.
   *
   * @param params
   * @return
   */
  IPage<DeployVo> getDeployInfo(Map params);

  /**
   * 编辑主机信息.
   *
   * @param deployVo
   * @return
   */
  boolean updateDeployInfo(DeployVo deployVo);

}
