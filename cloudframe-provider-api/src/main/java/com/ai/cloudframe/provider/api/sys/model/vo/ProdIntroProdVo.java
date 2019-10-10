package com.ai.cloudframe.provider.api.sys.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

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
public class ProdIntroProdVo implements Serializable {


    //产品编码
    @TableId("PRODUCT_ID")
    private String productId;

    //原子产品名称
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
    private String introduceProductTypeName;

    //计费类型
    @TableField("CHARGE_TYPE")
    private String chargeType;
    private String chargeTypeName;

    //建议价格
    @TableField("PRODUCT_PRICE")
    private String productPrice;

    //产品销售价
    private String productSalePrice;

    @TableField("START_DATE")
    private LocalDateTime startDate;

    @TableField("END_DATE")
    private LocalDateTime endDate;

    //引入产品描述
    @TableField("INTRODUCE_PRODUCT_DATAIL")
    private String introduceProductDatail;

    //商户
    private String  business;

    //版本号
    @TableField("VERSION_NUMBER")
    private String versionNumber;

    private String atomUrl;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS Z")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime releaseDate;

    private String releaseDateStr;

    //用于查询产品名称、产品编码
    private String prodNameCodeSearch;

    private String page;

    private String pageSize;

    //用于产品打包的多个子产品查询
    private String productIdsSearch;

}
