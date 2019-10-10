/**
 * Copyright 2019 asiainfo Inc.
 **/

package com.ai.cloudframe.provider.api.sys.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: shenbin
 * @CreateDate: [2019/7/25 9:45]
 * @Version: [v1.0]
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ApiListDto implements Serializable {

  private String abilityLabel;

  private String abilityName;

  private String apiId;

  private String apiStatus;

  private String apiType;

  private String apiUrl;

  private long pageSize;

  private long index;

}
