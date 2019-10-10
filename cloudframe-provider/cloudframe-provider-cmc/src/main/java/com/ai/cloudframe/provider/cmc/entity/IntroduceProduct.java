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
@TableName("tf_asiainfo_introduce_product")
public class IntroduceProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("INTRODUCE_PRODUCT_ID")
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

    @TableField("ATOM_ID")
    private String atomId;

    @TableField("CHARGE_TYPE")
    private String chargeType;

    @TableField("PRODUCT_PRICE")
    private String productPrice;

    /**
     * 0：未提交
            1：待审核
            2：待发布
            3：已发布
            4：已下架
     */
    @TableField("INTRODUCE_STATUS")
    private String introduceStatus;

    /**
     * 0：待测试
            1：通过
            2：不通过
     */
    @TableField("TEST_STATUS")
    private String testStatus;

    @TableField("INTRODUCE_PRODUCT_DATAIL")
    private String introduceProductDatail;

    @TableField("VERSION_NUMBER")
    private String versionNumber;

    @TableField("EXAMINE_USER_ID")
    private String examineUserId;

    @TableField("EXAMINE_DATE")
    private LocalDateTime examineDate;

    @TableField("EXAMINE_DETAIL")
    private String examineDetail;

    @TableField("RELEASE_DATE")
    private LocalDateTime releaseDate;

    @TableField("CREATE_USER_ID")
    private String createUserId;

    @TableField("START_DATE")
    private LocalDateTime startDate;

    @TableField("END_DATE")
    private LocalDateTime endDate;

    @TableField("ATOM_URL")
    private String atomUrl;

    @TableField("PRODUCT_ID")
    private String productId;

}
