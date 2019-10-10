/*
 * Copyright (c) 2019.  AI.
 */

package com.ai.cloudframe.provider.api.sys.service.fallback;


import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.provider.api.sys.model.dto.UserDto;
import com.ai.cloudframe.provider.api.sys.service.MenuFeignServiceApi;
import com.ai.cloudframe.provider.api.sys.service.PermFeignServiceApi;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author tangsz
 */
@Component
public class MenuFeignServiceFallback implements MenuFeignServiceApi {

	@Override
	public BaseResponse<UserDto> getUserMenus(@PathVariable("userName") String userName) {
		return null;
	}
}
