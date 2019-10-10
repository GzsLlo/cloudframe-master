package com.ai.cloudframe.provider.api.sys.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Timestamp;

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
public class ApiFlowControlDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String controlId;

    private String flowControlType;

    private String flowControlTypeName;

    private String abilityName;

    private String apiId;

    private String channelId;

    private String channelName;

    private String limitUnitTime;

    private String limitUnitTimeName;

    private int timeInterval;

    private int limitCount;

    private String comment;

    private String startTime;

    private String endTime;

    private long pageSize;

    private long index;

    private String flag;//2:删除，3：修改

}
