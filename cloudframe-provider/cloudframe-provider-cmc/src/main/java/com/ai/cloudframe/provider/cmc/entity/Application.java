package com.ai.cloudframe.provider.cmc.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

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
@TableName("tf_asiainfo_application")
public class Application implements Serializable {

    private static final long serialVersionUID = 1L;

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

    @TableField("START_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS Z")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime startDate;

    @TableField("END_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS Z")
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
}
