package com.ai.cloudframe.provider.api.sys.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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
public class AddApplicationVo implements Serializable {

    @TableId("APPLICATION_ID")
    private String applicationId;

    @TableField("APPLICATION_NAME")
    private String applicationName;

    @TableField("APPLICATION_LABEL")
    private String applicationLabel;

    @TableField("APPLICATION_URL")
    private String applicationUrl;

    @TableField("USER_ID")
    private String userId;

    /**
     * 0：未编译
     1：已编译
     2：未发布
     3：已发布
     4：未启动
     5：已启动
     */
    @TableField("APPLICATION_STATUS")
    private String applicationStatus;

    /**
     * 0:未被引入
     1：被引入
     */
    @TableField("QUOTE_STATUS")
    private String quoteStatus;

    @TableField("VERSION_NUM")
    private String versionNum;

    @TableField("IMG")
    private String img;

    @TableField("APPLICATION_DATAIL")
    private String applicationDatail;

    @TableField("CREATE_USER_ID")
    private String createUserId;

    @TableField("CITY_CODE")
    private String cityCode;

    @TableField("AREA_CODE")
    private String areaCode;

    @TableField("PM_WARN")
    private String pmWarn;

    @TableField("START_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime startDate;

    @TableField("END_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime endDate;

    @TableField("COMPILE_STATUS")
    private String compileStatus;

    @TableField("RELEASE_STATUS")
    private String releaseStatus;

    @TableField("RUN_STATUS")
    private String runStatus;

    @TableField("API_URL")
    private String apiUrl;

    @TableField("API_ID")
    private String apiId;

    @TableField("AIR_MONITOR_ID")
    private String airMonitorId;

    @TableField("RULE_NAME")
    private String ruleName;

    @TableField("MONITOR_TARGET")
    private String monitorTarget;

    @TableField("DIMENSION")
    private String dimension;

    @TableField("START_TIME")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime startTime;

    @TableField("END_TIME")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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

    @TableField("ACTIVE_FLAG")
    private String activeFlag;

    @TableField("ROLE_PERMISSIONS")
    private List<String> rolePermissions;

}
