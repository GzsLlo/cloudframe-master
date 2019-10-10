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
public class GoodsOrderVo implements Serializable {

    private String orderId;

    private String goodId;

    @TableField("GOOD_NAME")
    private String goodName;

    @TableField("GOOD_TYPE")
    private String goodType;

    private String goodTypeName;

    @TableField("ORDER_TYPE")
    private String orderType;

    @TableField("START_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS Z")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime startDate;

    @TableField("END_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS Z")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime endDate;

    @TableField("END_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS Z")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime orderDate;


    private String orderDateStr;

    private String orderStatus;

    private String userId;

    private String page;

    private String pageSize;

}
