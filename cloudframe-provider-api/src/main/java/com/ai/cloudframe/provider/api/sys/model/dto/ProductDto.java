package com.ai.cloudframe.provider.api.sys.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
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
public class ProductDto implements Serializable {

    @TableField("PRODUCT_ID_2")
    private List productId2;

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


    @TableField("INTRODUCE_PRODUCT_DATAIL")
    private String introduceProductDatail;

    @TableField("RELEASE_DATE")
    private LocalDateTime releaseDate;

    @TableField("ATOM_URL")
    private String atomUrl;

    @TableField("VERSION_NUMBER")
    private String versionNumber;

}
