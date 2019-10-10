package com.ai.cloudframe.provider.api.sys.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
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
public class AirQualityDto implements Serializable {

    @TableField("AIR_ID")
    private String airId;

    @TableField("DEVICE_ID")
    private String deviceId;

    @TableField("CITY_CODE")
    private String cityCode;

    @TableField("AREA_CODE")
    private String areaCode;

    @TableField("AREA_NAME")
    private String areaName;

    @TableField("COLLECT_DATE")
    private LocalDateTime collectDate;

    @TableField("PM")
    private String pm;
}
