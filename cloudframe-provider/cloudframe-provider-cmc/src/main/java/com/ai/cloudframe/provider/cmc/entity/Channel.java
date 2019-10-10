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
 * @since 2019-07-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tf_asiainfo_channel")
public class Channel implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "SEQ_ID",type = IdType.UUID)
    private String seqId;

    @TableId("CHANNEL_ID")
    private String channelId;

    @TableField("CHANNEL_NAME")
    private String channelName;

//    @TableField("TOKEN")
//    private String token;

    @TableField("START_DATE")
    private Timestamp startDate;

    @TableField("END_DATE")
    private Timestamp endDate;

    @TableField("CONTACTS")
    private String contacts;

    @TableField("CONTACT_PHONE")
    private String contactPhone;


}
