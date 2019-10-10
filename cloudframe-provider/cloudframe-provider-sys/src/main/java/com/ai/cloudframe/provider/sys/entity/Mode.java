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
 * 菜单模块信息表
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("td_asiainfo_mode")
public class Mode implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("MODE_CODE")
    private String modeCode;

    /**
     * 不同的模块类型
            0：内部模块
            1：外部模块
     */
    @TableField("MODE_TYPE")
    private String modeType;

    /**
     * 记录菜单模块信息，比如URL信息
     */
    @TableField("MODE_PATH")
    private String modePath;

    @TableField("MODE_COMPONENT")
    private String modeComponent;

    /**
     * 模块属性信息，比如图标、色彩等信息
     */
    @TableField("MODE_ATTR")
    private String modeAttr;

    /**
     * 模块属性信息，比如图标、色彩等信息
     */
    @TableField("SYS_ATTR")
    private String sysAttr;

    @TableField("PERMISSION_CODE")
    private String permissionCode;

    @TableField("START_DATE")
    private LocalDateTime startDate;

    @TableField("END_DATE")
    private LocalDateTime endDate;

    @TableField("UPDATE_USER_NAME")
    private String updateUserName;

    @TableField("UPDATE_TIME")
    private LocalDateTime updateTime;


}
