package com.ai.cloudframe.provider.sys.service;

import com.ai.cloudframe.provider.api.sys.model.dto.PermissionDto;
import com.ai.cloudframe.provider.api.sys.model.dto.MenuPermissionDto;
import com.ai.cloudframe.provider.api.sys.model.dto.PermissionListDto;
import com.ai.cloudframe.provider.sys.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 通过类型区分菜单、按钮、列查询、列修改、其他等权限 服务类
 * </p>
 *
 * @author Automated procedures
 * @since 2019-05-31
 */
public interface IPermissionService extends IService<Permission> {
  List<PermissionDto> getUserPerms (String username);


  List<MenuPermissionDto> getUserPermission(String username);

  List<PermissionListDto> getPermissionList(String username);

}
