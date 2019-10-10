package com.ai.cloudframe.provider.api.sys.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

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
public class ApiDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String apiId;

    private String flag;

    private String apiType;

    private String apiTypeName;

    private String abilityLabel;

    private String abilityName;

    private String quoteType;

    private String requestType;

    private String requestTypeName;
    /**
     * 0：关闭
            1：开启
     */

    private String apiStatus;

    private String apiStatusName;

    private String accessAgree;

    private String accessAgreeName;

    private String accessUrl;

    private String apiAgree;

    private String apiAgreeName;

    private String apiUrl;

    private String dataFormat;

    private String dataFormatName;

    private String xmlHeader;

    private String createUserId;

    private String startDate;

    private String endDate;

    private String abilityDetail;

    private String accessDataFormat;

    private String accessDataFormatName;

    private String createTime;

    private String childApi;

    private List<ApiParamDto> params;

    private List<ApiParamDto> requestParams;

    private List<ApiParamDto> responseParams;

}
