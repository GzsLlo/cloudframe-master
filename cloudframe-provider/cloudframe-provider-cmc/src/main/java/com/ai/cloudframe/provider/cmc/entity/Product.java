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
 * @since 2019-07-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tf_asiainfo_product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("PRODUCT_ID")
    private String productId;

    @TableField("PRODUCT_NAME")
    private String productName;

    @TableField("INTRODUCE_PRODUCT_ID")
    private String introduceProductId;

    @TableField("INTRODUCE_PRODUCT_NAME")
    private String introduceProductName;

    /**
     * 0：能力类
            1：语音类
            2：数据类
            3：应用平台
            4：第三方内容
            5：终端
     */
    @TableField("INTRODUCE_PRODUCT_TYPE")
    private String introduceProductType;

    @TableField("CHARGE_TYPE")
    private String chargeType;

    @TableField("PRODUCT_PRICE")
    private String productPrice;

    @TableField("START_DATE")
    private LocalDateTime startDate;

    @TableField("END_DATE")
    private LocalDateTime endDate;


}
