package com.ai.cloudframe.provider.sys.service.ipml;

import com.ai.cloudframe.provider.api.sys.model.dto.PermissionDto;
import com.ai.cloudframe.provider.api.sys.model.dto.MenuPermissionDto;
import com.ai.cloudframe.provider.api.sys.model.dto.PermissionListDto;
import com.ai.cloudframe.provider.sys.entity.Permission;
import com.ai.cloudframe.provider.sys.mapper.PermissionMapper;
import com.ai.cloudframe.provider.sys.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 通过类型区分菜单、按钮、列查询、列修改、其他等权限 服务实现类
 * </p>
 *
 * @author Automated procedures
 * @since 2019-05-31
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

  @Override
  public List<PermissionDto> getUserPerms(String username) {
    return this.baseMapper.getUserPerms(username);
  }

  @Override
  public List<MenuPermissionDto> getUserPermission(String username) {
    return this.baseMapper.getMenuPermission(username);
  }

  @Override
  public List<PermissionListDto> getPermissionList(String username) {
    return this.baseMapper.getPermissionList(username);
  }
}
