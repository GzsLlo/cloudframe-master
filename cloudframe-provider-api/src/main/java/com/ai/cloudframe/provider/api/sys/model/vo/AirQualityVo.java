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
public class AirQualityVo implements Serializable {

    @TableField("AIR_ID")
    private String airId;

    @TableField("DEVICE_ID")
    private String deviceId;

    @TableField("CITY_CODE")
    private String cityCode;

    @TableField("CITY_NAME")
    private String cityName;

    @TableField("AREA_CODE")
    private String areaCode;

    @TableField("AREA_NAME")
    private String areaName;

    @TableField("COLLECT_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS Z")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime collectDate;

    @TableField("PM")
    private String pm;
}
