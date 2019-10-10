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
 * @since 2019-07-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tf_asiainfo_product_mutex")
public class ProductMutex implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("PRODUCT_ID_1")
    private String productId1;

    @TableField("PRODUCT_ID_2")
    private String productId2;

    @TableField("CREATE_DATE")
    private LocalDateTime createDate;


}
