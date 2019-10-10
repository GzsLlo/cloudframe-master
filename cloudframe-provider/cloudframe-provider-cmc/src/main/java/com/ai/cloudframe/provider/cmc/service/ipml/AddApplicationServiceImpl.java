package com.ai.cloudframe.provider.cmc.service.ipml;

import com.ai.cloudframe.provider.api.sys.model.vo.AddApplicationVo;
import com.ai.cloudframe.provider.cmc.entity.AirMonitor;
import com.ai.cloudframe.provider.cmc.entity.Application;
import com.ai.cloudframe.provider.cmc.service.IAddApplicationService;
import com.ai.cloudframe.provider.cmc.service.IAirMonitorService;
import com.ai.cloudframe.provider.cmc.service.IApplicationService;

import com.ai.cloudframe.provider.cmc.service.IRolePermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * <p>
 * 新建应用服务入口.
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-19
 */
@Service
public class AddApplicationServiceImpl implements IAddApplicationService {

  private Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  IApplicationService iApplicationService;

  @Autowired
  IAirMonitorService iAirMonitorService;

  @Autowired
  IRolePermissionService iRolePermissionService;

  /**
   * 新建应用.
   *
   * @param addApplicationVos
   * @return
   */
  @Override
  @Transactional(rollbackFor = {Exception.class})
  public boolean addApplication(List<AddApplicationVo> addApplicationVos) {

    logger.debug("AddApplicationServiceImpl.addApplication addApplicationVos : {}", addApplicationVos);

    List<Application> applications = new ArrayList<>();
    List permissionCodes = new ArrayList();

    AirMonitor airMonitor = new AirMonitor();
    for (AddApplicationVo addApplicationVo : addApplicationVos) {

      UUID uuid = UUID.randomUUID();
      String applicationId = uuid.toString();
      Application application = new Application();
      BeanUtils.copyProperties(addApplicationVo, application);
      application.setApplicationId(applicationId);
      applications.add(application);

      String airMonitorId = "26b709c2-6bd3-45b3-8c96-0c8403e40ea3";

      BeanUtils.copyProperties(addApplicationVo, airMonitor);
      airMonitor.setAirMonitorId(airMonitorId);

      permissionCodes = addApplicationVo.getRolePermissions();
    }

    logger.debug("AddApplicationServiceImpl.addApplication applications : {}", applications);
    logger.debug("AddApplicationServiceImpl.addApplication airMonitor : {}", airMonitor);

    boolean applicationFlag = iApplicationService.saveBatch(applications);
    logger.debug("AddApplicationServiceImpl.addApplication applicationFlag : {}", applicationFlag);

    boolean monitorFlag = iAirMonitorService.saveOrUpdate(airMonitor);
    logger.debug("AddApplicationServiceImpl.addApplication monitorFlag : {}", monitorFlag);

    boolean roleFlag = iRolePermissionService.updateRolePermission(permissionCodes);
    logger.debug("AddApplicationServiceImpl.addApplication roleFlag : {}", roleFlag);

    return applicationFlag && monitorFlag;
  }
}
