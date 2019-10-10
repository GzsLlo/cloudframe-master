/**
 * Copyright 2019 asiainfo Inc.
 **/

package com.ai.cloudframe.provider.api.sys.service.fallback;

import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.provider.api.sys.model.dto.CallApiDto;
import com.ai.cloudframe.provider.api.sys.service.CallApiServiceApi;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: shenbin
 * @CreateDate: [2019/7/17 20:08]
 * @Version: [v1.0]
 */
@Component
public class CallApiServiceFallback implements CallApiServiceApi {

  @Override
  public BaseResponse<Map> callApi(CallApiDto param) {
    return null;
  }

  @Override
  public BaseResponse callApiByChanId(CallApiDto param) {
    return null;
  }
}
