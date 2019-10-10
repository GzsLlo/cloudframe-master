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
@TableName("tf_asiainfo_goods_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="ORDER_ID")
    private String orderId;

    @TableField("GOOD_ID")
    private String goodId;

    @TableField("USER_ID")
    private String userId;

    @TableField("ORDER_STATUS")
    private String orderStatus;

    @TableField("ORDER_DATE")
    private LocalDateTime orderDate;


}
