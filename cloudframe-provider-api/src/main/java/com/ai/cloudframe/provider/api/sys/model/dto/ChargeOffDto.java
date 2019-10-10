package com.ai.cloudframe.provider.api.sys.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ChargeOffDto implements Serializable {
  @TableField("BILL_DETAIL_ID")
  private String billdetailId;
  /**
   * 0 未出帐
   1 已出账
   */
  @TableField("BILL_STATUS")
  private String billStatus;


  @TableField("PRODUCT_TYPE")
  private String productType;

  @TableField("CREATE_DATE")
  private LocalDateTime createDate;

  @TableId("BILL_ID")
  private String billId;

  @TableField("CYCLE_CODE")
  private String cycleCode;

  @TableField("USER_ID")
  private String userId;

  @TableField("ORDER_ID")
  private String orderId;

  @TableField("SETTLER_CYCLE")
  private String settlerCycle;

  @TableField("GOOD_ID")
  private String goodId;

  @TableField("GOOD_PRODUCT_PRICE")
  private String goodProductPrice;

  @TableField("PRODUCT_ID")
  private String productId;

  @TableField("PRODUCT_PRICE")
  private String productPrice;

  @TableField("PRODUCT_NUM")
  private String productNum;

  @TableField("SETTLE_SCALE")
  private String settleScale;

  @TableField("SETTLE_MONEY")
  private String settleMoney;

  @TableField("SETTLE_DATE")
  private LocalDateTime settleDate;


  @TableField("CHARGE_TYPE")
  private String chargeType;


  @TableField("USE_COUNT")
  private String UseCount;


  @TableField("USER_NAME")
  private String userName;
}
