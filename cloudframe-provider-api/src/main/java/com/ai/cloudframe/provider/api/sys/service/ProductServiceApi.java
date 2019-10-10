/*
 * Copyright (c) 2019.  AI.
 */

package com.ai.cloudframe.provider.api.sys.service;


import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.provider.api.sys.model.dto.ProductDto;
import com.ai.cloudframe.provider.api.sys.model.dto.UserDto;
import com.ai.cloudframe.provider.api.sys.model.vo.ProdIntroProdVo;
import com.ai.cloudframe.provider.api.sys.service.fallback.UserFeignServiceFallback;
import com.alibaba.fastjson.JSONObject;
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
public interface ProductServiceApi {

	/**
	 *
	 * @param map
	 * @return
	 */
	@PostMapping(value = "/queryTable/selectProduct")
	@ResponseBody
	BaseResponse<Map> selectProduct(@RequestBody Map map);

	/**
	 *
	 * @param map
	 * @return
	 */
	@PostMapping(value = "/queryTable/selectOneProduct")
	@ResponseBody
    BaseResponse selectOneProduct(@RequestBody Map  map);


	@PostMapping(value = "/newProduct",consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	BaseResponse newProduct(ProductDto productDto);



	@PostMapping(value = "/productMutexCheck/{productIds}")
	@ResponseBody
	BaseResponse productMutexCheck(@PathVariable("productIds") String productIds);

	@PostMapping(value = "/prodUniIntroProdQueryTable",consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	BaseResponse<Map> prodUniIntroProdQueryTable(@RequestBody ProdIntroProdVo prodIntroProdVo);


	@PostMapping(value = "/queryTable/selectGoodProductSettle")
	@ResponseBody
  BaseResponse<Map> selectGoodProductSettle(@RequestBody Map  map);

}
