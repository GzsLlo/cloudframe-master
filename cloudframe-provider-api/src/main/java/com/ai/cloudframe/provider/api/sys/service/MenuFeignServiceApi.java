/*
 * Copyright (c) 2019.  AI.
 */

package com.ai.cloudframe.provider.api.sys.service;


import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.provider.api.sys.model.dto.UserDto;
import com.ai.cloudframe.provider.api.sys.service.fallback.MenuFeignServiceFallback;
import com.ai.cloudframe.provider.api.sys.service.fallback.UserFeignServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author tangsz
 */

@FeignClient(value = "${cloudframe.microservice.sys}", fallback = MenuFeignServiceFallback.class)
@Component
public interface MenuFeignServiceApi {

	/**
	 *
	 * @param userName
	 *
	 * @return
	 */
	@PostMapping(value = "/sys/menus")
	@ResponseBody
	BaseResponse<UserDto> getUserMenus(@PathVariable("userName") String userName);
}
