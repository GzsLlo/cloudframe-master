package com.ai.cloudframe.provider.sys.controller;


import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.common.base.support.BaseController;
import com.ai.cloudframe.provider.api.sys.model.dto.PermissionDto;
import com.ai.cloudframe.provider.api.sys.model.dto.MenuPermissionDto;
import com.ai.cloudframe.provider.api.sys.model.dto.PermissionListDto;
import com.ai.cloudframe.provider.api.sys.service.PermFeignServiceApi;
import com.ai.cloudframe.provider.sys.service.IPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 通过类型区分菜单、按钮、列查询、列修改、其他等权限 前端控制器
 * </p>
 *
 * @author Automated procedures
 * @since 2019-05-31
 */
@RestController
@Api(description = "权限相关接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PermissionController extends BaseController implements PermFeignServiceApi {

  @Autowired
  IPermissionService permissionService;



  @ApiOperation("根据用户名获取用户权限")
  @Override
  public BaseResponse<List<PermissionDto>> getUserPerms(@PathVariable("username") String username) {
    List<PermissionDto> permissions = permissionService.getUserPerms(username);
    BaseResponse response = permissions.size() > 0 ? BaseResponse.success(permissions) : BaseResponse.success();
    return response;
  }

  @Override
  public BaseResponse<List<MenuPermissionDto>> getUserPermission(String username) {
    List<MenuPermissionDto> userPermissionDtos = permissionService.getUserPermission(username);
    BaseResponse response = userPermissionDtos.size() > 0 ? BaseResponse.success(userPermissionDtos) : BaseResponse.success();
    return response;
  }

  @Override
  public BaseResponse<List<PermissionListDto>> getPermissionList(String username) {
    List<PermissionListDto> permissionListDtos = permissionService.getPermissionList(username);
    BaseResponse response = permissionListDtos.size() > 0 ? BaseResponse.success(permissionListDtos) : BaseResponse.success();
    return response;
  }


}
