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
public class GoodsDto implements Serializable {

    @TableField("GOOD_ID")
    private String goodId;

    @TableField("GOOD_NAME")
    private String goodName;


    @TableField("EXAMINE_STATUS")
    private String examineStatus;

    @TableField("GOOD_DETAIL")
    private String goodDetail;

    @TableField("CREATE_USER_ID")
    private String createUserId;

    @TableField("START_DATE")
    private LocalDateTime startDate;

    @TableField("END_DATE")
    private LocalDateTime endDate;

    @TableField("UNDER_DATE")
    private LocalDateTime underDate;

    @TableField("RELEASE_DATE")
    private LocalDateTime releaseDate;

    @TableField("EXAMINE_USER_ID")
    private String examineUserId;

    @TableField("EXAMINE_DATE")
    private LocalDateTime examineDate;

    @TableField("EXAMINE_DETAIL")
    private String examineDetail;

    @TableField("GOOD_TYPE")
    private String goodType;

    @TableField("ORDER_TYPE")
    private String orderType;

    @TableField("IS_EXPERIENCE_PRODUCT")
    private String isExperienceProduct;

    @TableField("CHANNEL_ID")
    private String channelId;

}
