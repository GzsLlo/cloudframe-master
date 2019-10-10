package com.ai.cloudframe.provider.api.sys.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

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
public class OrderDto implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableField("ORDER_STATUS")
    private String orderStatus;

    @TableField("ORDER_DATE")
    private LocalDateTime orderDate;


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



    @TableField("PRODUCT_PRICE")
    private String productPrice;


    @TableField("CREATE_DATE")
    private String createDate;



    @TableField("PRODUCT_ID")
    private String productId;

    @TableField("PRODUCT_NAME")
    private String productName;

    @TableField("PRODUCT_TYPE")
    private String productType;

    @TableField("ATOM_ID")
    private String atomId;

    @TableField("POSTAGE")
    private String postage;

    @TableField("EXAMINE_STATUS")
    private String examineStatus;

    @TableField("START_DATE")
    private LocalDateTime startDate;

    @TableField("END_DATE")
    private LocalDateTime endDate;

    @TableField("PRODUCT_DESCRIPTION")
    private  String productDescription;
}
