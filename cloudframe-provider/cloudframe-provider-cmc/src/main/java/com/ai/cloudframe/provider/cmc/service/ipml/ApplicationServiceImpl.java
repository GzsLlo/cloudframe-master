package com.ai.cloudframe.provider.cmc.service.ipml;

import com.ai.cloudframe.provider.api.sys.model.dto.ApiAppDto;
import com.ai.cloudframe.provider.api.sys.model.dto.ApiDto;
import com.ai.cloudframe.provider.api.sys.model.dto.ApplicationDto;
import com.ai.cloudframe.provider.api.sys.model.vo.AddApplicationVo;
import com.ai.cloudframe.provider.api.sys.model.vo.ApplicationProductVo;
import com.ai.cloudframe.provider.api.sys.model.vo.DeployVo;
import com.ai.cloudframe.provider.cmc.entity.Application;
import com.ai.cloudframe.provider.cmc.entity.Product;
import com.ai.cloudframe.provider.cmc.mapper.ApiMapper;
import com.ai.cloudframe.provider.cmc.mapper.ApplicationMapper;
import com.ai.cloudframe.provider.cmc.service.IApplicationService;
import com.ai.cloudframe.provider.cmc.service.IDeployService;
import com.ai.cloudframe.provider.cmc.utils.ScpCommandUtil;
import com.ai.cloudframe.provider.cmc.utils.ShellExecUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
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
public class ApplicationServiceImpl extends ServiceImpl<ApplicationMapper, Application> implements IApplicationService {

  private Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired(required = false)
  private ApplicationMapper applicationMapper = null;

  @Autowired
  private IDeployService iDeployService;

  @Override
  public IPage<ApiAppDto> applicationQuery(Map map) {
    Long pageNum = ((Integer) map.get("page")).longValue();
    Long pageSize = ((Integer) map.get("pageSize")).longValue();
    Page<Product> page = new Page<>(pageNum, pageSize);
    return applicationMapper.applicationQuery(page, map);
  }

  /**
   * 应用查询.
   *
   * @param param
   * @return
   */
  @Override
  public IPage<Application> getApplicationInfo(Map param) {
    logger.debug("ApplicationServiceImpl.getDeviceData param = {}", param);

    Long pageNum = (null != param.get("page")) ? Long.valueOf(param.get("page").toString()) : null;
    Long pageSize = (null != param.get("pageSize"))
        ? Long.valueOf(param.get("pageSize").toString()) : null;
    Page<Application> page = new Page<>(pageNum, pageSize);
    IPage<Application> applications = this.baseMapper.getApplicationInfo(page, param);

    logger.debug("ApplicationServiceImpl.getDeviceData applications : {}", applications);
    return applications;
  }

  /**
   * 更新应用.
   *
   * @param applicationVo
   * @return
   */
  @Override
  public boolean updateApplication(AddApplicationVo applicationVo) {
    int i = this.baseMapper.updateApplication(applicationVo);
    return i == 1;
  }

  /**
   * 部署应用.
   *
   * @return
   */
  @Override
  public boolean deployApplication() {
    logger.debug("ApplicationServiceImpl.deployApplication begin");
    boolean rtn = true;

    DeployVo deployVo = getDeployInfo();
    String fileName = deployVo.getOriginalUrl();
    String[] strs = fileName.split("/");
    fileName = strs[strs.length - 1];

    try {
      ScpCommandUtil.scpCommand(deployVo);
      ShellExecUtil.sshExecCmd(deployVo.getOriginalIp(), deployVo.getOriginalUser(), deployVo.getOriginalPwd(),
          -1, null, null, "tar -zxvf " + deployVo.getTargetUrl() + "/" +
              fileName + " -C " + deployVo.getTargetUrl(), true);
    } catch (Exception e) {
      rtn = false;
      logger.error("DeployController.getDeployInfo e : {}", e);
    }

    logger.debug("ApplicationServiceImpl.deployApplication rtn : {}", rtn);
    return rtn;
  }

  /**
   * 启动/停止应用.
   *
   * @return
   */
  @Override
  public boolean startOrStopApplication(String tag) {
    logger.debug("ApplicationServiceImpl.startOrStopApplication tag : {}", tag);
    boolean rtn = true;

    DeployVo deployVo = getDeployInfo();
    String command = "cd " + deployVo.getTargetUrl() + "/8091/springJar && ./" + deployVo.getStartFile() + " start";
    //0启动，1停止
    if ("1".equals(tag)) {
      command = "cd " + deployVo.getTargetUrl() + "/8091/springJar && ./" + deployVo.getStartFile() + " stop";
    }

    try {
      ShellExecUtil.sshExecCmd(deployVo.getOriginalIp(), deployVo.getOriginalUser(), deployVo.getOriginalPwd(),
          -1, null, null, command, true);
    } catch (Exception e) {
      rtn = false;
      logger.error("ApplicationServiceImpl.startOrStopApplication e : {}", e);
    }

    logger.debug("ApplicationServiceImpl.startOrStopApplication rtn : {}", rtn);
    return rtn;
  }

  @Override
  public ApplicationProductVo getApplicationProductRelation(ApiDto apiDto) {
    ApplicationProductVo vo = applicationMapper.getApplicationProductRelation(apiDto);
    return vo;
  }

  /**
   * 获取主机信息.
   *
   * @return
   */
  private DeployVo getDeployInfo() {
    Map params = new HashMap();
    params.put("page", "1");
    params.put("pageSize", "10");
    IPage<DeployVo> deployVoPage = iDeployService.getDeployInfo(params);
    List<DeployVo> deployVoList = deployVoPage.getRecords();
    logger.info("DeployController.getDeployInfo deployVoList : {}", deployVoList);

    DeployVo deployVo = (null != deployVoList && !deployVoList.isEmpty()) ? deployVoList.get(0) : new DeployVo();
    return deployVo;
  }

}
