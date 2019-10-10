/**
 * Copyright 2019 asiainfo Inc.
 **/

package com.ai.cloudframe.provider.api.sys.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: shenbin
 * @CreateDate: [2019/7/25 17:38]
 * @Version: [v1.0]
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ChannelAuthorizeDto implements Serializable {

  private String apiId;

  private String token;

  private String channelId;

  private String channelName;

}
