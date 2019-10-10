package com.ai.cloudframe.provider.cmc.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.time.LocalDateTime;

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
@TableName("tf_asiainfo_bill_detail")
public class BillDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("BILL_DETAIL_ID")
    private String billdetailId;

    @TableField("BILL_ID")
    private String billId;

    @TableField("CYCLE_CODE")
    private String cycleCode;


    /**
     * 0 未出帐
            1 已出账
     */
    @TableField("BILL_STATUS")
    private String billStatus;

    @TableField("USER_ID")
    private String userId;

    @TableField("ORDER_ID")
    private String orderId;


    @TableField("GOOD_ID")
    private String goodId;


    @TableField("GOOD_PRODUCT_PRICE")
    private String goodProductPrice;


    @TableField("PRODUCT_ID")
    private String productId;


    @TableField("PRODUCT_PRICE")
    private String productPrice;


    @TableField("CREATE_DATE")
    private LocalDateTime createDate;
}
