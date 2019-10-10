package com.ai.cloudframe.provider.cmc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

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
@TableName("tf_asiainfo_api_param")
public class ApiParam implements Serializable ,Comparable<ApiParam>{

    private static final long serialVersionUID = 1L;

    public ApiParam(){ }

    public ApiParam(String paramName){
        this.paramName = paramName;
    }

    @TableField("API_ID")
    private String apiId;

    @TableField("PARAM_MODE")
    private String paramMode;

    @TableField("SEQ_ID")
    private String seqId;

//    @TableId(value = "PARAM_ID",type = IdType.UUID)
    @TableField("PARAM_ID")
    private String paramId;

    @TableField("PARAM_NAME")
    private String paramName;

    /**
     * 0:String,1:int,2:double,3:date,8:map,9:list
     */
    @TableField("PARAM_TYPE")
    private String paramType;

    @TableField("REGEX")
    private String regex;

    @TableField("IS_EMPTY")
    private String isEmpty;

    @TableField("PARAM_LENGTH")
    private String paramLength;

    @TableField("PARAM_INDEX")
    private String paramIndex;

    @TableField("HEAD_TYPE")
    private String headType;

    @TableField("COMPLETION_TYPE")
    private String completionType;

    @TableField("DEFAULT_VALUE")
    private String defaultValue;

    @TableField("XPATH")
    private String xpath;

    @TableField("NAME_SPACE")
    private String nameSpace;

    @TableField("PARAM_DETAIL")
    private String paramDetail;

    @TableField("PARENT_PARAM_ID")
    private String parentParamId;

    @TableField("START_DATE")
    private Date startDate;

    @TableField("END_DATE")
    private Date endDate;

    @TableField("IS_HIDE")
    private String isHide;

    @Override
    public int compareTo(ApiParam o) {
        if(this.paramName.hashCode() > o.paramName.hashCode()){
            return 1;
        }else if(this.paramName.hashCode() < o.paramName.hashCode()){
            return -1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o){
        if(o.getClass() != getClass()){
            return false;
        }
        ApiParam that = (ApiParam) o;
        if(!this.paramName.equals(that.paramName)){
            return false;
        }
        return true;
    }
}
