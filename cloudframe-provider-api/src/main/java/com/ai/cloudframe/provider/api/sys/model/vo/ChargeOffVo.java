package com.ai.cloudframe.provider.api.sys.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ChargeOffVo  implements Serializable {
  @TableField("BILL_DETAIL_ID")
  private String billdetailId;
  /**
   * 0 未出帐
   1 已出账
   */
  @TableField("BILL_STATUS")
  private String billStatus;


  @TableField("GOOD_PRODUCT_PRICE")
  private String goodProductPrice;

  @TableField("CREATE_DATE")
  private LocalDateTime createDate;

  @TableId("BILL_ID")
  private String billId;

  @TableField("CYCLE_CODE")
  private String cycleCode;

  @TableField("ORDER_ID")
  private String orderId;




  @TableField("PRODUCT_PRICE")
  private String productPrice;

  @TableField("API_NAME")
  private String apiName;

  @TableField("APPLICATION_NAME")
  private String applicationName;


  @TableField("PRODUCT_NUM")
  private String productNum;



  @TableField("SETTLE_MONEY")
  private String settleMoney;

  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS Z")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @TableField("SETTLE_DATE")
  private LocalDateTime settleDate;

  @TableField("GOOD_NAME")
  private String goodName;

  @TableField("PRODUCT_NAME")
  private String productName;


  private String salemoney;


  private String summoney;


  @TableField("countmoney")
  private String countmoney;


  @TableField("USE_COUNT")
  private int useCount;


  @TableField("title")
  private String title;


  @TableField("code")
  private String code;

  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS Z")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @TableField("CALL_START_TIME")
  private Timestamp callStartTime;



  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS Z")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @TableField("CALL_END_TIME")
  private Timestamp callEndTime;

  @TableField("API_ID")
  private String apiId;



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


  @TableField("IS_CHARGEOFF")
  private String isChargeoff;

  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS Z")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @TableField("ORDER_DATE")
  private LocalDateTime orderDate;


  @TableField("RULE_ID")
  private String ruleId;

  @TableField("GOOD_ID")
  private String goodId;


  @TableField("GOOD_TYPE")
  private String goodType;

  @TableField("PRODUCT_ID")
  private String productId;

  @TableField("SETTLE_SCALE")
  private String settleScale;

  @TableField("CHARGE_TYPE")
  private String chargeType;


  @TableField("USER_NAME")
  private String userName;

  /**
   * 0：产品提供者
   1：产品使用者
   */
  @TableField("SETTLER_TYPE")
  private String settlerType;

  @TableField("SETTLER_CYCLE")
  private String settlerCycle;

  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS Z")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @TableField("START_DATE")
  private LocalDateTime startDate;

  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS Z")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @TableField("END_DATE")
  private LocalDateTime endDate;
}
