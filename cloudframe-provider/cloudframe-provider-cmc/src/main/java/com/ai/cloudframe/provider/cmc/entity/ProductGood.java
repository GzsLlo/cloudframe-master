package com.ai.cloudframe.provider.cmc.entity;

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
@TableName("tf_asiainfo_product_good")
public class ProductGood implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("GOOD_ID")
    private String goodId;

    @TableField("PRODUCT_ID")
    private String productId;

    @TableField("GOOD_PRODUCT_PRICE")
    private String goodProductPrice;

    @TableField("START_DATE")
    private LocalDateTime startDate;

    @TableField("END_DATE")
    private LocalDateTime endDate;


}
