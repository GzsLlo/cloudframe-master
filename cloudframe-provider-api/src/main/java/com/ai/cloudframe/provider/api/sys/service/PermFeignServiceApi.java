/*
 * Copyright (c) 2019.  AI.
 */

package com.ai.cloudframe.provider.api.sys.service;


import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.provider.api.sys.model.dto.PermissionDto;
import com.ai.cloudframe.provider.api.sys.model.dto.MenuPermissionDto;
import com.ai.cloudframe.provider.api.sys.model.dto.PermissionListDto;
import com.ai.cloudframe.provider.api.sys.service.fallback.PermFeignServiceFallback;
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

@FeignClient(value = "${cloudframe.microservice.sys}")
@Component
public interface PermFeignServiceApi {

	/**
	 *
	 * @param username
	 *
	 * @return
	 */
	@PostMapping(value = "/sys/perms/{username}")
	@ResponseBody
	BaseResponse<List<PermissionDto>> getUserPerms(@PathVariable("username") String username);


	@PostMapping(value = "/sys/menuPermission/{username}")
	@ResponseBody
	BaseResponse<List<MenuPermissionDto>> getUserPermission(@PathVariable("username") String username);

	@PostMapping(value = "/sys/permissionList/{username}")
	@ResponseBody
	BaseResponse<List<PermissionListDto>> getPermissionList(@PathVariable("username") String username);
}
