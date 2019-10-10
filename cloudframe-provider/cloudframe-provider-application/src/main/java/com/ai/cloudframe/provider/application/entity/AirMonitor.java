package com.ai.cloudframe.provider.application.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2019-07-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tf_asiainfo_air_monitor")
public class AirMonitor implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("AIR_MONITOR_ID")
    private String airMonitorId;

    @TableField("RULE_NAME")
    private String ruleName;

    @TableField("MONITOR_TARGET")
    private String monitorTarget;

    @TableField("DIMENSION")
    private String dimension;

    @TableField("START_TIME")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS Z")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime startTime;

    @TableField("END_TIME")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS Z")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime endTime;

    @TableField("REGION")
    private String region;

    @TableField("THRESHOLD_TYPE")
    private String thresholdType;

    @TableField("THRESHOLD")
    private String threshold;

    @TableField("ALARM_CODE")
    private String alarmCode;

    @TableField("ALARM_TYPE")
    private String alarmType;

    @TableField("START_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS Z")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime startDate;

    @TableField("END_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS Z")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime endDate;
}
