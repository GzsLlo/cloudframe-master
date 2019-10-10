package com.ai.cloudframe.provider.cmc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2019-07-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tf_asiainfo_settle_rule")
public class SettleRule implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "RULE_ID",type = IdType.UUID)
    private String ruleId;

    @TableField("GOOD_ID")
    private String goodId;


    @TableField("PRODUCT_ID")
    private String productId;

    @TableField("SETTLE_SCALE")
    private String settleScale;

    @TableField("CHARGE_TYPE")
    private String chargeType;


    /**
     * 0：产品提供者
            1：产品使用者
     */
    @TableField("SETTLER_TYPE")
    private String settlerType;

    @TableField("SETTLER_CYCLE")
    private String settlerCycle;


    @TableField("START_DATE")
    private LocalDateTime startDate;

    @TableField("END_DATE")
    private LocalDateTime endDate;


}
