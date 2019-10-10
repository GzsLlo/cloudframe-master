package com.ai.cloudframe.provider.cmc.service.ipml;

import com.ai.cloudframe.provider.cmc.entity.RolePermission;
import com.ai.cloudframe.provider.cmc.mapper.RolePermissionMapper;
import com.ai.cloudframe.provider.cmc.service.IRolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-23
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements IRolePermissionService {

  private Logger logger = LoggerFactory.getLogger(getClass());

  /**
   * 添加权限.
   *
   * @param permissionCodes
   * @return
   */
  @Override
  public boolean addRolePermission(List permissionCodes) {
    logger.debug("RolePermissionServiceImpl.addRolePermission : {}", permissionCodes);

    int ret = this.baseMapper.addRolePermission(permissionCodes);
    logger.debug("RolePermissionServiceImpl.ret : {}", ret);

    return ret > 0;
  }

  /**
   * 删除权限.
   *
   * @param permissionCodes
   * @return
   */
  @Override
  public boolean deleteRolePermission(List permissionCodes) {
    logger.debug("RolePermissionServiceImpl.addRolePermission : {}", permissionCodes);

    int ret = this.baseMapper.deleteRolePermission(permissionCodes);
    logger.debug("RolePermissionServiceImpl.ret : {}", ret);

    return ret > 0;
  }

  /**
   * 修改权限.
   *
   * @param permissionCodes
   * @return
   */
  @Override
  @Transactional(rollbackFor = {Exception.class})
  public boolean updateRolePermission(List permissionCodes) {
    logger.debug("RolePermissionServiceImpl.updateRolePermission permissionCodes : {}", permissionCodes);

    boolean flag = true;

    if (null != permissionCodes && !permissionCodes.isEmpty()) {
      List deleteCode = new ArrayList();
      deleteCode.add("home:application:1000");
      deleteCode.add("home:application:1001");
      deleteCode.add("home:application:1002");
      boolean delFlag = deleteRolePermission(deleteCode);
      boolean addFlag = addRolePermission(permissionCodes);
      flag = delFlag && addFlag;
    }

    return flag;
  }
}
