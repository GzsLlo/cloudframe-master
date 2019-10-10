package com.ai.cloudframe.provider.api.sys.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
 * @since 2019-06-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel
public class ParamDto implements Serializable {

    @ApiModelProperty(value = "DictCode")
    private String dictCode;

    @ApiModelProperty(value = "DictTypeCode")
    private String dictTypeCode;

    @ApiModelProperty(value = "DictName")
    private String dictName;

}
