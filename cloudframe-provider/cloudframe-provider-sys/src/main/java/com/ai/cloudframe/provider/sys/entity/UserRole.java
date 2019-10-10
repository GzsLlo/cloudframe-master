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
 * 
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("td_asiainfo_user_role")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("USER_NAME")
    private String userName;

    /**
     * 角色编码，可以被修改，但是不能重复
     */
    @TableField("ROLE_CODE")
    private String roleCode;

    @TableField("START_DATE")
    private LocalDateTime startDate;

    @TableField("END_DATE")
    private LocalDateTime endDate;

    @TableField("UPDATE_USER_NAME")
    private String updateUserName;

    @TableField("UPDATE_TIME")
    private LocalDateTime updateTime;


}
