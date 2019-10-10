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
 * @since 2019-05-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("td_asiainfo_user")
public class User implements Serializable {

    @TableId("USER_ID")
    private String userId;

    @TableId("USER_NAME")
    private String userName;

    @TableField("NICKNAME")
    private String nickname;

    @TableField("PASSWORD")
    private String password;

    @TableField("USER_TYPE")
    private String userType;

    @TableField("OPEN_ID")
    private String openId;

    @TableField("EMAIL")
    private String email;

    @TableField("PHONE")
    private String phone;

    @TableField("LAST_LOGIN_TIME")
    private LocalDateTime lastLoginTime;

    @TableField("START_DATE")
    private LocalDateTime startDate;

    @TableField("END_DATE")
    private LocalDateTime endDate;

    @TableField("UPDATE_USER_NAME")
    private String updateUserName;

    @TableField("UPDATE_TIME")
    private LocalDateTime updateTime;


}
