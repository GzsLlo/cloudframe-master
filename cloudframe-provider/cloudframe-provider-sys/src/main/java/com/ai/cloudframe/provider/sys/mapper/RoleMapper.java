package com.ai.cloudframe.provider.sys.mapper;

import com.ai.cloudframe.provider.api.sys.model.dto.RoleDto;
import com.ai.cloudframe.provider.sys.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Automated procedures
 * @since 2019-06-04
 */
public interface RoleMapper extends BaseMapper<Role> {
  List<RoleDto> getUserRolesByName (String username);
}
