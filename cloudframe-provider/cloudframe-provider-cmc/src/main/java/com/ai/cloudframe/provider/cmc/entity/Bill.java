package com.ai.cloudframe.provider.cmc.entity;

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
@TableName("tf_asiainfo_bill")
public class Bill implements Serializable {

    private static final long serialVersionUID = 1L;

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


}
