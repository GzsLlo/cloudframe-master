package com.ai.cloudframe.provider.api.sys.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 通过类型区分菜单、按钮、列查询、列修改、其他等权限
 * </p>
 *
 * @author Automated procedures
 * @since 2019-05-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel
public class PermissionDto implements Serializable {

    @ApiModelProperty(value = "权限编码")
    private String permissionCode;

    @ApiModelProperty(value = "权限名称")
    private String permissionName;

    @ApiModelProperty(value = "权限描述")
    private String permissionExplain;

    /**
     * 可以根据使用类型进行定义
            0：菜单权限
            1：界面操作特权
			2：按钮权限
 3：列查询 4:设备状态
     */
    @ApiModelProperty(value = "权限类型")
    private String permissionType;

    /**
     * 存放此权限的一些属性，由具体使用场景决定
     */
    @ApiModelProperty(value = "权限属性")
    private String permissionAttr;

    @ApiModelProperty(value = "开始时间")
    private LocalDateTime startDate;

    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endDate;
}
