package com.ai.cloudframe.provider.sys.service;

import com.ai.cloudframe.provider.api.sys.model.dto.RoleDto;
import com.ai.cloudframe.provider.sys.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Automated procedures
 * @since 2019-06-04
 */
public interface IRoleService extends IService<Role> {
  List<RoleDto> getUserRolesByName (String username);
}
