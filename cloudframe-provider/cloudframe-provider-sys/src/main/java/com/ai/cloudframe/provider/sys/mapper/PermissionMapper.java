package com.ai.cloudframe.provider.sys.mapper;

import com.ai.cloudframe.provider.api.sys.model.dto.PermissionDto;
import com.ai.cloudframe.provider.api.sys.model.dto.MenuPermissionDto;
import com.ai.cloudframe.provider.api.sys.model.dto.PermissionListDto;
import com.ai.cloudframe.provider.sys.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 通过类型区分菜单、按钮、列查询、列修改、其他等权限 Mapper 接口
 * </p>
 *
 * @author Automated procedures
 * @since 2019-05-31
 */
public interface PermissionMapper extends BaseMapper<Permission> {
  List<PermissionDto> getUserPerms (String username);


  List<MenuPermissionDto> getMenuPermission(String username);

  List<PermissionListDto> getPermissionList(String username);
}
