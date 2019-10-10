/*
 * Copyright (c) 2019.  AI.
 */

package com.ai.cloudframe.provider.api.sys.service;


import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.provider.api.sys.model.dto.IntroduceProductDto;
import com.ai.cloudframe.provider.api.sys.model.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 *
 * @author tangsz
 */

@FeignClient(value = "${cloudframe.microservice.cmc}")
@Component
public interface IntroduceProductServiceApi {

	/**
	 *
	 * @param map
	 * @return
	 */
	@PostMapping(value = "/queryTable/selectIntroduceProduct")
	@ResponseBody
	BaseResponse<Map> selectIntroduceProduct(@RequestBody Map map);


	@PostMapping(value = "/newIntroduceProduct",consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	BaseResponse newIntroduceProduct(IntroduceProductDto introduceProductDto);

	@PostMapping(value = "/introduceProductsStatusUpdate",consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	BaseResponse introduceProductsStatusUpdate(@RequestBody IntroduceProductDto introduceProductDto);

}
