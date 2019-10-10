package com.ai.cloudframe.provider.api.sys.model.vo;

import com.ai.cloudframe.provider.api.sys.model.dto.MenuPermissionDto;
import com.ai.cloudframe.provider.api.sys.model.dto.PermissionDto;
import com.ai.cloudframe.provider.api.sys.model.dto.PermissionListDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author tangsz
 * @since 2019-05-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel
public class LoginUserVo implements Serializable {

  @ApiModelProperty(value = "用户ID")
  private String userId;

  @ApiModelProperty(value = "用户名")
  private String userName;

  @ApiModelProperty(value = "用户昵称")
  private String nickname;

  @ApiModelProperty(value = "用户类型")
  private String userType;

  @ApiModelProperty(value = "openId")
  private String openId;

  @ApiModelProperty(value = "邮箱")
  private String email;

  @ApiModelProperty(value = "电话")
  private String phone;

  @ApiModelProperty(value = "token")
  private String token;

  @ApiModelProperty(value = "角色编码")
  private String roleCode;

  @ApiModelProperty(value = "菜单权限")
  private List<PermissionDto> menuList;

  @ApiModelProperty(value = "按钮权限")
  private List<PermissionListDto> permissionList;

}
