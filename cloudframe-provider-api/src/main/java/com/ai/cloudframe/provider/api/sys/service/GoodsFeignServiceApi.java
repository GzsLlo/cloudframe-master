/*
 * Copyright (c) 2019.  AI.
 */

package com.ai.cloudframe.provider.api.sys.service;


import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.provider.api.sys.model.dto.GoodsDto;
import com.ai.cloudframe.provider.api.sys.model.vo.GoodsProductVo;
import com.ai.cloudframe.provider.api.sys.model.vo.GoodsVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 *
 * @author tangsz
 */

@FeignClient(value = "${cloudframe.microservice.cmc}")
@Component
public interface GoodsFeignServiceApi {

	@PostMapping(value = "/goodsSave",consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
  BaseResponse saveGoods(@RequestBody List<GoodsDto> goodsDtoList);

	@PostMapping(value = "/goodsStatusUpdate",consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	BaseResponse goodsStatusUpdate(@RequestBody GoodsDto goodsDto);

	@PostMapping(value = "/goodsUnderUpdate",consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	BaseResponse goodsUnderUpdate(@RequestBody GoodsDto goodsDto);

	@PostMapping(value = "/goodsReleaseUpdate",consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	BaseResponse goodsReleaseUpdate(@RequestBody GoodsDto goodsDto);


	@PostMapping(value = "/goodInfoqueryTable",consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	BaseResponse goodInfoqueryTable(@RequestBody GoodsVo goodsVo);

	@PostMapping(value = "/goodProdInfoquery/{goodId}",consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	BaseResponse<List<GoodsProductVo>> goodProdInfoquery(@PathVariable("goodId") String goodId);

}
