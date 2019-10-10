package com.ai.cloudframe.provider.param.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2019-07-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("td_asiainfo_param")
public class Param implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("DICT_CODE")
    private String dictCode;

    @TableField("DICT_TYPE_CODE")
    private String dictTypeCode;

    @TableField("DICT_NAME")
    private String dictName;

    @TableField("LOCALE")
    private String locale;

    @TableField("DICT_TYPE_DESC")
    private String dictTypeDesc;

    @TableField("DICT_ORDER")
    private Integer dictOrder;

    /**
     * ??Чʱ??
     */
    @TableField("START_DATE")
    private LocalDateTime startDate;

    /**
     * ʧЧʱ??
     */
    @TableField("END_DATE")
    private LocalDateTime endDate;

    @TableField("UPDATE_USER_NAME")
    private String updateUserName;

    @TableField("UPDATE_DATE")
    private LocalDateTime updateDate;

    /**
     * ??ע
     */
    @TableField("REMARK")
    private String remark;


}
