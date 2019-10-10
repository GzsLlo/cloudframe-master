package com.ai.cloudframe.provider.cmc.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.sql.Timestamp;

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
 * @since 2019-07-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tf_asiainfo_api_statistics")
public class ApiStatistics implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableField("PRODUCT_ID")
  private String productId;

  @TableField("USE_COUNT")
  private int useCount;

  @TableField("CALL_START_TIME")
  private Timestamp callStartTime;

  @TableField("CALL_END_TIME")
  private Timestamp callEndTime;

  @TableField("API_ID")
  private String apiId;

  @TableField("GOOD_ID")
  private String goodId;

  @TableField("USER_ID")
  private String userId;

  @TableField("CHANNEL_ID")
  private String channelId;

  @TableField("token")
  private String token;

  @TableField("RESULT_CODE")
  private String resultCode;

  @TableField("RESULT_MESSAGE")
  private String resultMessage;


  @TableField("APPLICATION_ID")
  private String applicationId;

  @TableField("IS_CHARGEOFF")
  private String isChargeoff;

}
