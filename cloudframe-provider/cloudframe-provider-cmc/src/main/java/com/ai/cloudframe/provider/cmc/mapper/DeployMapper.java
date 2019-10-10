package com.ai.cloudframe.provider.cmc.mapper;

import com.ai.cloudframe.provider.api.sys.model.vo.DeployVo;
import com.ai.cloudframe.provider.cmc.entity.Deploy;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-24
 */
public interface DeployMapper extends BaseMapper<Deploy> {

  /**
   * 查询主机信息.
   *
   * @param page
   * @param params
   * @return
   */
  Page<DeployVo> getDeployInfo(@Param("pg") Page page, @Param("params") Map params);

  /**
   * 更新主机信息.
   *
   * @param deployVo
   * @return
   */
  int updateDeployInfo(@Param("deployVo") DeployVo deployVo);

}
