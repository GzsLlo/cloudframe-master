package com.ai.cloudframe.provider.cmc.service;

import com.ai.cloudframe.provider.api.sys.model.vo.AddApplicationVo;

import java.util.List;

/**
 * <p>
 * 新建应用服务入口.
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-19
 */
public interface IAddApplicationService {

  /**
   * 新建应用.
   *
   * @param addApplicationVos
   * @return
   */
  boolean addApplication(List<AddApplicationVo> addApplicationVos);

}
