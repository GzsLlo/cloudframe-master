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
 * @CreateDate: [2019/7/18 10:14]
 * @Version: [v1.0]
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CallApiDto implements Serializable {
  private static final long serialVersionUID = 1L;

  private String url;

  private String inputString;

  private String channelId;

  private String requestMethod;

}
