package com.ai.cloudframe.web.controller;

import com.ai.cloudframe.common.base.constant.GlobalConstant;
import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.common.base.support.BaseController;

import com.ai.cloudframe.common.base.util.StringUtil;
import com.ai.cloudframe.common.base.util.Utility;
import com.ai.cloudframe.provider.api.sys.model.dto.*;
import com.ai.cloudframe.provider.api.sys.model.vo.LoginUserVo;
import com.ai.cloudframe.provider.api.sys.service.PermFeignServiceApi;
import com.ai.cloudframe.provider.api.sys.service.RoleFeignServiceApi;
import com.ai.cloudframe.provider.api.sys.service.UserFeignServiceApi;
import com.alibaba.fastjson.JSONObject;
import com.neusoft.encrypt.sm4.encryptor.SM4Encryptor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;


/**
 * tangsz.
 */
@RestController
@Api(description = "登录/授权相关接口")
@CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = "*")
public class LoginController extends BaseController {


  @Autowired
  private UserFeignServiceApi userFeignServiceApi;

  @Autowired
  private RoleFeignServiceApi roleFeignServiceApi;

  @Autowired
  private PermFeignServiceApi permFeignServiceApi;


  /**
   * 已经注掉了shiroConfig 和 customerSessionManager的redis
   */
  @Value("${cloudframe.web.salt}")
  private String salt = "salt";

  @RequestMapping(value = "/login", method = RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  @ApiOperation("用户登录")
  @ApiImplicitParams({
      @ApiImplicitParam(name="vueJson",value="提交报文",required=true,paramType="form")
  })
  public BaseResponse login(@RequestParam Map<String, Object> vueJson, @RequestHeader HttpHeaders headers){
    logger.info("login.param:{}", vueJson);
    JSONObject info = Utility.convert2JsonAndCheckRequiredColumns(vueJson, "USER_NAME,PASSWORD");
    String username = info.getString("USER_NAME");
    String password = info.getString("PASSWORD");
    Subject subject = SecurityUtils.getSubject();
    String encryptPwd = SM4Encryptor.SM4Encode(username + password, salt);
//    String encryptPwd ="iA16cgiYMDmg7cBy0mGg5Q==";
//    UsernamePasswordToken token = new UsernamePasswordToken(username, encryptPwd);
    BaseResponse response;
    try {
//      subject.login(token);
//      subject.isPermitted(username);

      BaseResponse<UserDto> userDtoBaseResponse = userFeignServiceApi.getLoginUser(username, encryptPwd);
      Optional<UserDto> user = Optional.ofNullable(userDtoBaseResponse.getData());

      if (!user.isPresent() || StringUtil.isNullOrEmpty(user.get().getUserName())) {
        throw new UnknownAccountException();
      }
      List<RoleDto> list = roleFeignServiceApi.getUserRoles(username).getData();
      RoleDto role = new RoleDto();
      Optional.ofNullable(list).ifPresent(li -> li.forEach(item -> {
        BeanUtils.copyProperties(item, role);
      }));

      List<PermissionDto> menuPermissionDto = permFeignServiceApi.getUserPerms(username).getData();
      List<PermissionListDto> permissionListDto = permFeignServiceApi.getPermissionList(username).getData();



//      UserDto user = (UserDto) SecurityUtils.getSubject().getSession().getAttribute(GlobalConstant.SESSION_USER_INFO);
//      RoleDto role = (RoleDto) SecurityUtils.getSubject().getSession().getAttribute(GlobalConstant.SESSION_USER_ROLES);
//      List<MenuPermissionDto> menuPermissionDto = (List<MenuPermissionDto>) SecurityUtils.getSubject()
//          .getSession().getAttribute(GlobalConstant.SESSION_MEAN_PERMISSION);
//      List<PermissionListDto> permissionListDto = (List<PermissionListDto>) SecurityUtils.getSubject().
//          getSession().getAttribute(GlobalConstant.SESSION_PERMISSION_LIST);
      LoginUserVo vo = new LoginUserVo();
      BeanUtils.copyProperties(user.get(), vo);

      vo.setRoleCode(role.getRoleCode());
      vo.setMenuList(menuPermissionDto);
      vo.setPermissionList(permissionListDto);
      if (subject.isAuthenticated()) {
        vo.setToken((String) subject.getSession().getId());
      }
      response = BaseResponse.success(vo);
    } catch (AuthenticationException e) {
      response = BaseResponse.fail();
    }
    return response;
  }
  //{"USER_NAME":"admin","PASSWORD":"admin@123"}


  @PostMapping("/logout")
  @ApiOperation("用户退出")
  public BaseResponse logout() {
    try {
      Subject subject = SecurityUtils.getSubject();
      subject.logout();
    } catch (Exception e) {
    }
    return BaseResponse.success();
  }


  @PostMapping("/menu")
  @ApiOperation("获取菜单")
  public BaseResponse menu(){
    return null;
  }

}
