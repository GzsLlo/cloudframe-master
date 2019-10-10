package com.ai.cloudframe.provider.cmc.service;

import com.ai.cloudframe.provider.cmc.entity.RolePermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-23
 */
public interface IRolePermissionService extends IService<RolePermission> {

  /**
   * 添加权限.
   *
   * @param permissionCodes
   * @return
   */
  boolean addRolePermission(List permissionCodes);

  /**
   * 删除权限.
   *
   * @param permissionCodes
   * @return
   */
  boolean deleteRolePermission(List permissionCodes);

  /**
   * 修改权限.
   *
   * @param permissionCodes
   * @return
   */
  boolean updateRolePermission(List permissionCodes);

}
