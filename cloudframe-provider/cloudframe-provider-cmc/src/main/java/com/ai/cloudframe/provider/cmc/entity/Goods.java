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
@TableName("tf_asiainfo_goods")
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "GOOD_ID",type = IdType.UUID)
    private String goodId;

    @TableField("GOOD_NAME")
    private String goodName;

    @TableField("GOOD_TYPE")
    private String goodType;

    @TableField("EXAMINE_STATUS")
    private String examineStatus;

    @TableField("GOOD_DETAIL")
    private String goodDetail;

    @TableField("EXAMINE_USER_ID")
    private String examineUserId;

    @TableField("EXAMINE_DATE")
    private LocalDateTime examineDate;

    @TableField("EXAMINE_DETAIL")
    private String examineDetail;

    @TableField("IS_EXPERIENCE_PRODUCT")
    private String isExperienceProduct;

    @TableField("ORDER_TYPE")
    private String orderType;

    @TableField("RELEASE_DATE")
    private LocalDateTime releaseDate;

    @TableField("UNDER_DATE")
    private LocalDateTime underDate;

    @TableField("CREATE_USER_ID")
    private String createUserId;

    @TableField("START_DATE")
    private LocalDateTime startDate;

    @TableField("END_DATE")
    private LocalDateTime endDate;

    @TableField("CHANNEL_ID")
    private String channelId;

}
