package com.ai.cloudframe.provider.api.sys.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ChannelVo implements Serializable {
  private static final long serialVersionUID = 1L;

  @TableField("API_ID")
  private String apiId;

  @TableField("CHANNEL_ID")
  private String channelId;

  @TableField("CHANNEL_NAME")
  private String channelName;

  @TableField("TOKEN")
  private String token;

  @TableField("API_TYPE")
  private String apiType;

  @TableField("ABILITY_LABEL")
  private String abilityLabel;

  @TableField("API_NAME")
  private String apiName;

  @TableField("QUOTE_STATUS")
  private String quoteStatus;

  @TableField("REQUEST_TYPE")
  private String requestType;

  /**
   * 0：关闭
   1：开启
   */
  @TableField("API_STATUS")
  private String apiStatus;

  @TableField("ACCESS_AGREE")
  private String accessAgree;

  @TableField("ACCESS_URL")
  private String accessUrl;

  @TableField("API_AGREE")
  private String apiAgree;

  @TableField("API_URL")
  private String apiUrl;

  @TableField("DATA_FORMAT")
  private String dataFormat;

  @TableField("XML_HEAD")
  private String xmlHeader;

  @TableField("CREATE_USER_ID")
  private String createUserId;

  @TableField("START_DATE")
  private Date startDate;

  @TableField("END_DATE")
  private Date endDate;

  @TableField("ACCESS_DATA_FORMAT")
  private String accessDataFormat;

  @TableField("API_DETAIL")
  private String apiDetail;

  @TableField("CREATE_TIME")
  private Timestamp createTime;
}
