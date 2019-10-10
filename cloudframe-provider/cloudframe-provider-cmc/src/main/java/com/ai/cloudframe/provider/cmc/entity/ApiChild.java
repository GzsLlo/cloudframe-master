package com.ai.cloudframe.provider.cmc.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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
@TableName("tf_asiainfo_api_child")
public class ApiChild implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("API_ID")
    private String apiId;

    @TableField("CHILD_API_ID")
    private String childApiId;

    @TableField("CALL_SEQ")
    private String callSeq;

    @TableField("START_DATE")
    private Timestamp startDate;

    @TableField("END_DATE")
    private Timestamp endDate;


}
