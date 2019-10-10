/*
 * Copyright (c) 2019.  AI.
 */

package com.ai.cloudframe.provider.api.sys.service;


import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.provider.api.sys.model.dto.GoodsDto;
import com.ai.cloudframe.provider.api.sys.model.dto.ProductGoodDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *
 * @author tangsz
 */

@FeignClient(value = "${cloudframe.microservice.cmc}")
@Component
public interface GoodProductFeignServiceApi {

	@PostMapping(value = "/goodsProductSave",consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
  BaseResponse saveGoodsProduct(@RequestBody List<ProductGoodDto> productGoodDtoList);

}
