package com.ai.cloudframe.provider.sys.entity;

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
 * 通过类型区分菜单、按钮、列查询、列修改、其他等权限
 * </p>
 *
 * @author Automated procedures
 * @since 2019-05-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("td_m_permission")
public class Permission implements Serializable {


    @TableId("PERMISSION_CODE")
    private String permissionCode;

    @TableField("PERMISSION_NAME")
    private String permissionName;

    @TableField("PERMISSION_EXPLAIN")
    private String permissionExplain;

    /**
     * 可以根据使用类型进行定义
            0：菜单权限
            1：界面操作特权
			2：按钮权限
 3：列查询 4:设备状态
     */
    @TableField("PERMISSION_TYPE")
    private String permissionType;

    /**
     * 存放此权限的一些属性，由具体使用场景决定
     */
    @TableField("PERMISSION_ATTR")
    private String permissionAttr;

    @TableField("START_DATE")
    private LocalDateTime startDate;

    @TableField("END_DATE")
    private LocalDateTime endDate;

    @TableField("UPDATE_USER_NAME")
    private String updateUserName;

    @TableField("UPDATE_TIME")
    private LocalDateTime updateTime;


}
