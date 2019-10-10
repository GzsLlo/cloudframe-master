package com.ai.cloudframe.provider.cmc.service.ipml;

import com.ai.cloudframe.provider.api.sys.model.vo.DeployVo;
import com.ai.cloudframe.provider.cmc.entity.Deploy;
import com.ai.cloudframe.provider.cmc.mapper.DeployMapper;
import com.ai.cloudframe.provider.cmc.service.IDeployService;
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
 * @since 2019-07-24
 */
@Service
public class DeployServiceImpl extends ServiceImpl<DeployMapper, Deploy> implements IDeployService {

  private Logger logger = LoggerFactory.getLogger(getClass());

  /**
   * 主机信息查询.
   *
   * @param params
   * @return
   */
  @Override
  public IPage<DeployVo> getDeployInfo(Map params) {
    logger.debug("DeployServiceImpl.getDeployInfo params : {}", params);

    Long pageNum = (null != params.get("page")) ? Long.valueOf(params.get("page").toString()) : null;
    Long pageSize = (null != params.get("pageSize"))
        ? Long.valueOf(params.get("pageSize").toString()) : null;

    Page<DeployVo> page = new Page<>(pageNum, pageSize);
    IPage<DeployVo> deployVos = this.baseMapper.getDeployInfo(page, params);

    logger.debug("DeployServiceImpl.getDeployInfo deployVos : {}", deployVos);

    return deployVos;
  }

  /**
   * 编辑主机信息.
   *
   * @param deployVo
   * @return
   */
  @Override
  public boolean updateDeployInfo(DeployVo deployVo) {
    int i = this.baseMapper.updateDeployInfo(deployVo);
    return i == 1;
  }
}
