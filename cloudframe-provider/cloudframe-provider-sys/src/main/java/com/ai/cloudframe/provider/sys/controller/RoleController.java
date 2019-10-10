package com.ai.cloudframe.provider.sys.controller;


import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.provider.api.sys.model.dto.RoleDto;
import com.ai.cloudframe.provider.api.sys.service.RoleFeignServiceApi;
import com.ai.cloudframe.provider.sys.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import com.ai.cloudframe.common.base.support.BaseController;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Automated procedures
 * @since 2019-06-04
 */
@RestController
@Api(description = "角色相关接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RoleController extends BaseController implements RoleFeignServiceApi {

  @Autowired
  IRoleService roleService;

  @Override
  @ApiOperation("根据用户名获取用户角色")
  public BaseResponse<List<RoleDto>> getUserRoles(@PathVariable("username") String username) {
    List<RoleDto> roles = roleService.getUserRolesByName(username);
    BaseResponse response = roles.size() > 0 ? BaseResponse.success(roles) : BaseResponse.success();
    return response;
  }
}