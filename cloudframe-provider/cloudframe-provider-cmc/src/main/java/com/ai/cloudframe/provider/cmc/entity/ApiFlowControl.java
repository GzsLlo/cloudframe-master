package com.ai.cloudframe.provider.cmc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;

import java.sql.Timestamp;
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
 * @since 2019-07-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tf_asiainfo_api_flow_control")
public class ApiFlowControl implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId( value ="CONTROL_ID" ,type = IdType.UUID)
    private String controlId;

    @TableField("FLOW_CONTROL_TYPE")
    private String flowControlType;

    @TableField("ABILITY_NAME")
    private String abilityName;

    @TableField("API_ID")
    private String apiId;

    @TableField("CHANNEL_ID")
    private String channelId;

    @TableField("LIMIT_UNIT_TIME")
    private String limitUnitTime;

    @TableField("TIME_INTERVAL")
    private Integer timeInterval;

    @TableField("LIMIT_COUNT")
    private Integer limitCount;

    @TableField("'COMMENT'")
    private String comment;

    @TableField("START_TIME")
    private Timestamp startTime;

    @TableField("END_TIME")
    private Timestamp endTime;


}
