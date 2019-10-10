/*
 * Copyright (c) 2019.  AI.
 */

package com.ai.cloudframe.provider.api.sys.service;


import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.provider.api.sys.model.dto.RoleDto;
import com.ai.cloudframe.provider.api.sys.service.fallback.RoleFeignServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *
 * @author tangsz
 */

@FeignClient(value = "${cloudframe.microservice.sys}", fallback = RoleFeignServiceFallback.class)
@Component
public interface RoleFeignServiceApi {

	/**
	 *
	 * @param username
	 *
	 * @return
	 */
	@PostMapping(value = "/sys/roles/{username}")
	@ResponseBody
	BaseResponse<List<RoleDto>> getUserRoles(@PathVariable("username") String username);
}
