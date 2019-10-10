package com.ai.cloudframe.provider.cmc.entity;

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
    private LocalDateTime startTime;

    @TableField("END_TIME")
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
    private LocalDateTime startDate;

    @TableField("END_DATE")
    private LocalDateTime endDate;

}
