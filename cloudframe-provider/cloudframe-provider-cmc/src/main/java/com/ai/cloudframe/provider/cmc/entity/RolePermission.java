package com.ai.cloudframe.provider.cmc.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("td_asiainfo_rolepermission")
public class RolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 系统自动生成的ID
     */
    @TableId("ROLE_CODE")
    private String roleCode;

    @TableField("PERMISSION_CODE")
    private String permissionCode;

    /**
     * 1 菜单按钮权限 2、列权限  4、状态权限  5、API权限
     */
    @TableField("PERMISSION_TYPE")
    private String permissionType;

    @TableField("START_DATE")
    private LocalDateTime startDate;

    @TableField("END_DATE")
    private LocalDateTime endDate;

    @TableField("UPDATE_USER_NAME")
    private String updateUserName;

    @TableField("UPDATE_TIME")
    private LocalDateTime updateTime;


}
