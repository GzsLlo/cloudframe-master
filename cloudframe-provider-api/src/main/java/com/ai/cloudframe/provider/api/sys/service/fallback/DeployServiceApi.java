/*
 * Copyright (c) 2019.  AI.
 */

package com.ai.cloudframe.provider.api.sys.service.fallback;


import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.provider.api.sys.model.vo.DeployVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


/**
 * 主机信息.
 *
 * @author tangsz
 */

@FeignClient(value = "${cloudframe.microservice.cmc}")
@Component
public interface DeployServiceApi {

  /**
   * 主机信息查询.
   *
   * @param params
   * @return
   */
  @PostMapping(value = "/deploy/getDeployInfo")
  @ResponseBody
  BaseResponse<Map> getDeployInfo(@RequestBody Map params);

  /**
   * 编辑主机信息.
   *
   * @param deployVo
   * @return
   */
  @PostMapping(value = "/application/updateDeployInfo")
  @ResponseBody
  BaseResponse<Map> updateDeployInfo(@RequestBody DeployVo deployVo);
}
