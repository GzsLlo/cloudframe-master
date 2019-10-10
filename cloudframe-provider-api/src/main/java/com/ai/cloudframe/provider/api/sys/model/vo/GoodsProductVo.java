package com.ai.cloudframe.provider.api.sys.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2019-07-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class GoodsProductVo implements Serializable {

    private String goodId;

    private String productId;

    private String productName;

    private String introduceProductDatail;

    private String introduceProductType;

    private String productPrice;

    private String goodProductPrice;

    private String chargeType;

    private String chargeName;

}
