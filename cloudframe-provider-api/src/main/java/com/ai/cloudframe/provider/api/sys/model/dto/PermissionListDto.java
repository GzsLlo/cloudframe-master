package com.ai.cloudframe.provider.api.sys.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
public class PermissionListDto implements Serializable {

    @TableId("PERMISSION_CODE")
    private String permissionCode;

    @TableField("PERMISSION_NAME")
    private String permissionName;

    @TableField("MENU_ID")
    private String menuId;

    @TableId("BUTTON_ID")
    private String buttonId;

    @TableField("BUTTON_TITLE")
    private String buttonTitle;
}
