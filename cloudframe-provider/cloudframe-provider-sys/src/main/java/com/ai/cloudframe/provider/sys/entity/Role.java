package com.ai.cloudframe.provider.sys.entity;

import java.math.BigDecimal;
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
 * @since 2019-06-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("td_m_role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色编码，可以被修改，但是不能重复
     */
    @TableId("ROLE_CODE")
    private String roleCode;

    @TableField("ROLE_NAME")
    private String roleName;

    @TableField("ROLE_EXPLAIN")
    private String roleExplain;

    /**
     * 0:集团   1:机构\社区
     */
    @TableField("ROLE_TYPE")
    private String roleType;

    /**
     * 集团ID
     */
    @TableField("GROUP_ID")
    private BigDecimal groupId;

    /**
     * 表示是否为固定角色，固定角色的权限不能编辑配置
     */
    @TableField("IS_FIXED")
    private String isFixed;

    /**
     * 默认标志：0-非默认，1-默认
     */
    @TableField("SERVICE_PROVIDER_CODE")
    private String serviceProviderCode;

    @TableField("START_DATE")
    private LocalDateTime startDate;

    @TableField("END_DATE")
    private LocalDateTime endDate;

    @TableField("UPDATE_USER_NAME")
    private String updateUserName;

    @TableField("UPDATE_TIME")
    private LocalDateTime updateTime;


}
