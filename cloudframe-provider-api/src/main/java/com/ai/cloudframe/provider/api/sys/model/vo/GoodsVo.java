package com.ai.cloudframe.provider.api.sys.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class GoodsVo implements Serializable {

//    @TableId(value = "GOOD_ID",type = IdType.UUID)
    private String goodId;

    @TableField("GOOD_NAME")
    private String goodName;

    @TableField("GOOD_TYPE")
    private String goodType;

    private String goodTypeName;

    @TableField("EXAMINE_STATUS")
    private String examineStatus;

    @TableField("GOOD_DETAIL")
    private String goodDetail;

    @TableField("EXAMINE_USER_ID")
    private String examineUserId;

    @TableField("EXAMINE_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS Z")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime examineDate;

    @TableField("EXAMINE_DETAIL")
    private String examineDetail;

    @TableField("IS_EXPERIENCE_PRODUCT")
    private String isExperienceProduct;

    @TableField("ORDER_TYPE")
    private String orderType;

    @TableField("RELEASE_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS Z")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String releaseDate;

    @TableField("UNDER_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS Z")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime underDate;

    @TableField("CREATE_USER_ID")
    private String createUserId;

    @TableField("START_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS Z")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime startDate;

    @TableField("END_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS Z")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime endDate;

    @TableField("CHANNEL_ID")
    private String channelId;

    private String ifOrder;

    private String page;

    private String pageSize;

}
