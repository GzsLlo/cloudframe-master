package com.ai.cloudframe.provider.cmc.entity;

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
 * @since 2019-07-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tf_asiainfo_air_quality")
public class AirQuality implements Serializable {

    private static final long serialVersionUID = 1L;

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
