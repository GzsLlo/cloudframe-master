/*
 * Copyright (c) 2019.  AI.
 */

package com.ai.cloudframe.provider.api.sys.service.fallback;


import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.provider.api.sys.model.dto.MenuPermissionDto;
import com.ai.cloudframe.provider.api.sys.model.dto.PermissionDto;
import com.ai.cloudframe.provider.api.sys.model.dto.PermissionListDto;
import com.ai.cloudframe.provider.api.sys.service.PermFeignServiceApi;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 *
 * @author tangsz
 */
@Component
public class PermFeignServiceFallback implements PermFeignServiceApi {

	@Override
	public BaseResponse<List<PermissionDto>> getUserPerms(@PathVariable("username") String username) {
		return BaseResponse.success();
	}

	@Override
	public BaseResponse<List<MenuPermissionDto>> getUserPermission(String username) {
		return BaseResponse.success();
	}

	@Override
	public BaseResponse<List<PermissionListDto>> getPermissionList(String username) {
		return BaseResponse.success();
	}
}
