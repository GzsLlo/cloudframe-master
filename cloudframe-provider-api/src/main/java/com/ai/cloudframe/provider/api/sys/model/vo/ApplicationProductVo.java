package com.ai.cloudframe.provider.api.sys.model.vo;

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
public class ApplicationProductVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String applicationId;

    private String productId;

    private String goodId;

}
