/*
 * Copyright (c) 2019.  AI.
 */

package com.ai.cloudframe.provider.api.sys.service;


import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.provider.api.sys.model.dto.UserDto;
import com.ai.cloudframe.provider.api.sys.service.fallback.UserFeignServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author tangsz
 */

@FeignClient(value = "${cloudframe.microservice.sys}", fallback = UserFeignServiceFallback.class)
@Component
public interface UserFeignServiceApi {

	/**
	 *
	 * @param username
	 * @param password
	 * @return
	 */
	@PostMapping(value = "/sys/user/{username}/{password}")
	@ResponseBody
	BaseResponse<UserDto> getLoginUser(@PathVariable("username") String username, @PathVariable("password") String password);


	@PostMapping(value = "/sys/userIdToName/{usernameId}")
	@ResponseBody
	BaseResponse<UserDto> getUserNameById(@PathVariable("usernameId") String usernameId);
}
