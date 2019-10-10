package com.ai.cloudframe.provider.cmc.mapper;

import com.ai.cloudframe.provider.cmc.entity.RolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-23
 */
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

  /**
   * 添加权限.
   *
   * @param permissionCodes
   * @return
   */
  int addRolePermission(@Param("permissionCodes") List permissionCodes);

  /**
   * 删除权限.
   *
   * @param permissionCodes
   * @return
   */
  int deleteRolePermission(@Param("permissionCodes") List permissionCodes);

}
