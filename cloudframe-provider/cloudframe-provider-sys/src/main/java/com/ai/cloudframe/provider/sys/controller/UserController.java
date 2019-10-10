package com.ai.cloudframe.provider.sys.controller;


import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.common.base.support.BaseController;
import com.ai.cloudframe.provider.api.sys.model.dto.RoleDto;
import com.ai.cloudframe.provider.api.sys.model.dto.UserDto;
import com.ai.cloudframe.provider.api.sys.service.UserFeignServiceApi;
import com.ai.cloudframe.provider.sys.entity.User;
import com.ai.cloudframe.provider.sys.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tangsz
 * @since 2019-05-22
 */
@RestController
@Api(description = "用户相关接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController extends BaseController implements UserFeignServiceApi {

  @Autowired
  IUserService userService;


  @ApiOperation("根据用户名获取用户信息")
  @Override
  public BaseResponse<UserDto> getLoginUser(@PathVariable("username") String username,
                                                 @PathVariable("password") String password) {
    LambdaQueryWrapper<User> queryWrapper = new QueryWrapper<User>()
            .lambda()
            .eq(User::getUserName, username)
            .eq(User::getPassword, password);
    User user = userService.getOne(queryWrapper);
    Optional<User> optional = Optional.ofNullable(user);
    final UserDto[] userDto = {null};
    optional.ifPresent(val -> {
      userDto[0] = new UserDto();
      BeanUtils.copyProperties(user, userDto[0]);
    });
    return BaseResponse.success(userDto[0]);
  }

  @Override
  public BaseResponse<UserDto> getUserNameById(String usernameId) {
    LambdaQueryWrapper<User> queryWrapper = new QueryWrapper<User>()
        .lambda()
        .eq(User::getUserId, usernameId);
    User user = userService.getOne(queryWrapper);
    Optional<User> optional = Optional.ofNullable(user);
    final UserDto[] userDto = {null};
    optional.ifPresent(val -> {
      userDto[0] = new UserDto();
      BeanUtils.copyProperties(user, userDto[0]);
    });
    return BaseResponse.success(userDto[0]);
  }
}
