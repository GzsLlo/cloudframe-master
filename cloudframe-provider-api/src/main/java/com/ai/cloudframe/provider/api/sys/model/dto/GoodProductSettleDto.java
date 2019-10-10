package com.ai.cloudframe.provider.api.sys.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class GoodProductSettleDto implements Serializable {




    @TableField("RULE_ID")
    private String ruleId;

    @TableId("GOOD_ID")
    private String goodId;

    @TableField("GOOD_NAME")
    private String goodName;


    @TableField("GOOD_TYPE")
    private String goodType;

    @TableField("GOOD_PRODUCT_PRICE")
    private String goodProductPrice;

    @TableId("PRODUCT_ID")
    private String productId;

    @TableField("PRODUCT_NAME")
    private String productName;


    @TableField("PRODUCT_PRICE")
    private String productPrice;

    @TableField("CHARGE_TYPE")
    private String chargeType;

    @TableField("SETTLE_SCALE")
    private String settleScale;

    @TableField("SETTLER_TYPE")
    private String settlerType;


    @TableField("SETTLER_CYCLE")
    private String settlerCycle;


    @TableField("START_DATE")
    private Timestamp startDate;

    @TableField("END_DATE")
    private Timestamp endDate;



}
