package com.ai.cloudframe.provider.cmc.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tf_f_asiainfo_deploy")
public class Deploy implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableField("DEPLOY_ID")
  private String deployId;

  @TableField("ORIGINAL_IP")
  private String originalIp;

  @TableField("ORIGINAL_USER")
  private String originalUser;

  @TableField("ORIGINAL_PWD")
  private String originalPwd;

  @TableField("ORIGINAL_URL")
  private String originalUrl;

  @TableField("TARGET_IP")
  private String targetIp;

  @TableField("TARGET_USER")
  private String targetUser;

  @TableField("TARGET_PWD")
  private String targetPwd;

  @TableField("TARGET_URL")
  private String targetUrl;

  @TableField("START_FILE")
  private String startFile;

  @TableField("STOP_FILE")
  private String stopFile;

}
