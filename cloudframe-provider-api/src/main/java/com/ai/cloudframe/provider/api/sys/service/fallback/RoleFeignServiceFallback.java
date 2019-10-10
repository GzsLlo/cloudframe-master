/*
 * Copyright (c) 2019.  AI.
 */

package com.ai.cloudframe.provider.api.sys.service.fallback;


import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.provider.api.sys.model.dto.RoleDto;
import com.ai.cloudframe.provider.api.sys.service.RoleFeignServiceApi;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tangsz
 */
@Component
public class RoleFeignServiceFallback implements RoleFeignServiceApi {

	@Override
	public BaseResponse<List<RoleDto>> getUserRoles(@PathVariable("username") String username) {
		return BaseResponse.success();
	}
}
