/*
 * Copyright (c) 2019.  AI.
 */

package com.ai.cloudframe.provider.api.sys.service.fallback;


import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.provider.api.sys.model.dto.UserDto;
import com.ai.cloudframe.provider.api.sys.service.UserFeignServiceApi;
import org.springframework.stereotype.Component;

/**
 *
 * @author tangsz
 */
@Component
public class UserFeignServiceFallback implements UserFeignServiceApi {

	@Override
	public BaseResponse<UserDto> getLoginUser(String username, String password) {
		return BaseResponse.success();
	}

	@Override
	public BaseResponse<UserDto> getUserNameById(String usernameId) {
		return  BaseResponse.success();
	}

	;
}
