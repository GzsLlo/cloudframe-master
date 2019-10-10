package com.ai.cloudframe.provider.api.sys.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

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
public class UserDto implements Serializable {


  @ApiModelProperty(value = "用户ID")
  private String userId;

  @ApiModelProperty(value = "用户名")
  private String userName;

  @ApiModelProperty(value = "用户昵称")
  private String nickname;

  @ApiModelProperty(value = "用户类型")
  private String userType;

  @ApiModelProperty(value = "opend")
  private String openId;

  @ApiModelProperty(value = "邮箱")
  private String email;

  @ApiModelProperty(value = "电话")
  private String phone;

  @ApiModelProperty(value = "最后登录时间")
  private LocalDateTime lastLoginTime;


}
