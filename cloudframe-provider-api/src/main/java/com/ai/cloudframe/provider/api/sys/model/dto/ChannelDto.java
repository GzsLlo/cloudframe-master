package com.ai.cloudframe.provider.api.sys.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

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
public class ChannelDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String seqId;

    private String channelId;

    private String channelName;

    private String startDate;

    private String endDate;

    private String contacts;

    private String contactPhone;

    private Long pageSize;

    private long index;

    private String page;

}
