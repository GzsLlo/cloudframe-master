package com.ai.cloudframe.provider.api.sys.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2019-07-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ApiParamDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String apiId;

    private String flag;

    private String paramMode;

    private String paramModeName;

    private String seqId;

    private String paramId;

    private String paramName;

    /**
     * 0:String,1:int,2:double,3:date,8:map,9:list
     */

    private String paramType;

    private String paramTypeName;

    private String regex;

    private String isEmpty;

    private String isEmptyName;

    private String paramLength;

    private String paramIndex;

    private String headType;

    private String completionType;

    private String defaultValue;

    private String xpath;

    private String nameSpace;

    private String paramDetail;

    private String parentParamId;

    private String parentParamName;

    private String startDate;

    private String endDate;

    private String ishide;

    private String ishideName;
}
