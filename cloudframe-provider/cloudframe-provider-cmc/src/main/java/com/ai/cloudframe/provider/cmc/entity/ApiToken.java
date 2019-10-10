package com.ai.cloudframe.provider.cmc.entity;

import com.baomidou.mybatisplus.annotation.TableName;

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
 * @since 2019-07-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tf_asiainfo_api_token")
public class ApiToken implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("API_ID")
    private String apiId;

    @TableField("CHANNEL_ID")
    private String channelId;

    @TableField("CHANNEL_NAME")
    private String channelName;

    @TableField("TOKEN")
    private String token;

    @TableField("START_DATE")
    private Timestamp startDate;

    @TableField("END_DATE")
    private Timestamp endDate;


}
