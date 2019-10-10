package com.ai.cloudframe.provider.api.sys.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * tangsz
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel
public class RoleDto implements Serializable {

  /**
   * 角色编码，可以被修改，但是不能重复
   */
  @ApiModelProperty(value = "角色编码")
  private String roleCode;

  @ApiModelProperty(value = "角色名称")
  private String roleName;

  @ApiModelProperty(value = "角色描述")
  private String roleExplain;

  /**
   * 0:集团   1:机构\社区
   */
  @ApiModelProperty(value = "角色类型")
  private String roleType;

  private LocalDateTime startDate;

  private LocalDateTime endDate;

}
