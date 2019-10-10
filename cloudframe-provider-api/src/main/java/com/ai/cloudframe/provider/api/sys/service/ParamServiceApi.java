/*
 * Copyright (c) 2019.  AI.
 */

package com.ai.cloudframe.provider.api.sys.service;


import com.ai.cloudframe.common.base.response.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 *
 * @author tangsz
 */

@FeignClient(value = "${cloudframe.microservice.param}")
@Component
public interface ParamServiceApi {


	@PostMapping(value = "/param/codeToName/{dictTypeCode}/{dictCode}/")
	@ResponseBody
	BaseResponse<String> codeToName(@PathVariable("dictTypeCode") String dictTypeCode,@PathVariable("dictCode") String dictCode);
}
