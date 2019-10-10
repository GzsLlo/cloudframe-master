package com.ai.cloudframe.provider.api.sys.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
public class ApiAppDto implements Serializable {


    @TableField("AUTOID")
    private String autoId;

    @TableField("AUTONAME")
    private String autoName;

    @TableField("AUTOTAG")
    private String autoTag;

    @TableField("URL")
    private String url;

    @TableField("CREATEPERSON")
    private String createPerson;

    @TableField("CREATETIME")
    private String createTime;

    @TableField("INTRODUCTION")
    private String introduction;

    @TableField("LABEL")
    private String label;

}
