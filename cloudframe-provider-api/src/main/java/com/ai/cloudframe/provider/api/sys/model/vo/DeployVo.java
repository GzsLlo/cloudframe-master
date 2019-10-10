package com.ai.cloudframe.provider.api.sys.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
public class DeployVo implements Serializable {

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
