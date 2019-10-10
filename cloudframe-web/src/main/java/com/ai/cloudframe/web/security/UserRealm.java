package com.ai.cloudframe.web.security;

import com.ai.cloudframe.common.base.constant.GlobalConstant;
import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.common.base.util.StringUtil;
import com.ai.cloudframe.provider.api.sys.model.dto.*;
import com.ai.cloudframe.provider.api.sys.service.PermFeignServiceApi;
import com.ai.cloudframe.provider.api.sys.service.RoleFeignServiceApi;
import com.ai.cloudframe.provider.api.sys.service.UserFeignServiceApi;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * tangsz
 */
public class UserRealm extends AuthorizingRealm {

  private Logger logger = LoggerFactory.getLogger(UserRealm.class);

  @Autowired
  private UserFeignServiceApi userFeignServiceApi;

  @Autowired
  private RoleFeignServiceApi roleFeignServiceApi;

  @Autowired
  private PermFeignServiceApi permFeignServiceApi;


  @Override
//  doGetAuthenticationInfo
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    String username = (String) principals.getPrimaryPrincipal();
    Session session = SecurityUtils.getSubject().getSession();
    List<RoleDto> roles = (ArrayList<RoleDto>) session.getAttribute(GlobalConstant.SESSION_USER_ROLES);
    logger.info("roles:" + roles);
    Optional<List<RoleDto>> optional = Optional.ofNullable(roles);
    SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
    optional.ifPresent(val -> {
      val.forEach(item -> {
        authorizationInfo.addRole(item.getRoleCode());
      });
    });

    if (!optional.isPresent()) {
      List<RoleDto> list = roleFeignServiceApi.getUserRoles(username).getData();
      RoleDto roleDto = new RoleDto();
      Optional.ofNullable(list).ifPresent(li -> li.forEach(item -> {
        BeanUtils.copyProperties(item, roleDto);
        authorizationInfo.addRole(item.getRoleCode());
      }));

      session.setAttribute(GlobalConstant.SESSION_USER_ROLES, roleDto);
    }

    List<PermissionDto> perms = (ArrayList<PermissionDto>) session.getAttribute(GlobalConstant.SESSION_USER_PERMISSION);
    Optional<List<PermissionDto>> opl = Optional.ofNullable(perms);
    opl.ifPresent(val -> {
      val.forEach(item -> {
        authorizationInfo.addStringPermission(item.getPermissionCode());
      });
    });

    if (!opl.isPresent()) {
      List<PermissionDto> list = permFeignServiceApi.getUserPerms(username).getData();
      List<MenuPermissionDto> menuPermissionDtoList = permFeignServiceApi.getUserPermission(username).getData();
      List<PermissionListDto> permissionListDtoList = permFeignServiceApi.getPermissionList(username).getData();
      Optional.ofNullable(list).ifPresent(li -> li.forEach(item -> {
        authorizationInfo.addStringPermission(item.getPermissionCode());
      }));
      session.setAttribute(GlobalConstant.SESSION_MEAN_PERMISSION, menuPermissionDtoList);
      session.setAttribute(GlobalConstant.SESSION_PERMISSION_LIST, permissionListDtoList);
    }

    return authorizationInfo;
  }


  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
    String username = (String) authcToken.getPrincipal();
    String password = new String((char[]) authcToken.getCredentials());
    BaseResponse<UserDto> response = userFeignServiceApi.getLoginUser(username, password);
    Optional<UserDto> user = Optional.ofNullable(response.getData());

    if (!user.isPresent() || StringUtil.isNullOrEmpty(user.get().getUserName())) {
      throw new UnknownAccountException();
    }

    //查角色（因为要做重复登陆）
    Session session = SecurityUtils.getSubject().getSession();
    List<RoleDto> list = roleFeignServiceApi.getUserRoles(username).getData();
    RoleDto roleDto = new RoleDto();
    Optional.ofNullable(list).ifPresent(li -> li.forEach(item -> {
      BeanUtils.copyProperties(item, roleDto);
    }));
    session.setAttribute(GlobalConstant.SESSION_USER_ROLES, roleDto);

    //权限（因为要做重复登陆）
//    List<MenuPermissionDto> menuPermissionDtoList = permFeignServiceApi.getUserPermission(username).getData();
    List<PermissionDto> menuPermissionDtoList = permFeignServiceApi.getUserPerms(username).getData();
    List<PermissionListDto> permissionListDtoList = permFeignServiceApi.getPermissionList(username).getData();
    session.setAttribute(GlobalConstant.SESSION_MEAN_PERMISSION, menuPermissionDtoList);
    session.setAttribute(GlobalConstant.SESSION_PERMISSION_LIST, permissionListDtoList);


    SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
        user.get().getUserName(),
        password,
        ByteSource.Util.bytes("cloudframe"),
        getName()
    );
    SecurityUtils.getSubject().getSession().setAttribute(GlobalConstant.SESSION_USER_INFO, user.get());
    return authenticationInfo;
  }
}
