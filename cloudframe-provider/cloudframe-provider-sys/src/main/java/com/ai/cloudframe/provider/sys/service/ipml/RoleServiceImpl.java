package com.ai.cloudframe.provider.sys.service.ipml;

import com.ai.cloudframe.provider.api.sys.model.dto.RoleDto;
import com.ai.cloudframe.provider.sys.entity.Role;
import com.ai.cloudframe.provider.sys.mapper.RoleMapper;
import com.ai.cloudframe.provider.sys.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Automated procedures
 * @since 2019-06-04
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

  @Override
  public List<RoleDto> getUserRolesByName(String username) {
    return this.baseMapper.getUserRolesByName(username);
  }
}
