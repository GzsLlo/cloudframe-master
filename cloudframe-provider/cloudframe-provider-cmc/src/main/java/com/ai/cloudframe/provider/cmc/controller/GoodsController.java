package com.ai.cloudframe.provider.cmc.controller;


import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.provider.api.sys.model.dto.GoodsDto;
import com.ai.cloudframe.provider.api.sys.model.vo.GoodsProductVo;
import com.ai.cloudframe.provider.api.sys.model.vo.GoodsVo;
import com.ai.cloudframe.provider.api.sys.service.GoodsFeignServiceApi;
import com.ai.cloudframe.provider.cmc.entity.Goods;
import com.ai.cloudframe.provider.cmc.service.IGoodsService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import com.ai.cloudframe.common.base.support.BaseController;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-17
 */
@RestController
@Api(description = "商品相关接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class GoodsController extends BaseController implements GoodsFeignServiceApi {

  @Autowired
  IGoodsService iGoodsService;

  @Override
  public BaseResponse saveGoods(List<GoodsDto> goodsDtoList) {
    boolean flag;
//    UUID uuid = UUID.randomUUID();
//    String uuidStr = uuid.toString();

    List<Goods> goodsList=new ArrayList<>();

    for (GoodsDto each:goodsDtoList) {
      Goods goods=new Goods();
      BeanUtils.copyProperties(each,goods);
//      if(each.getGoodId()==null){
//        goods.setGoodId(uuidStr);
//      }
      goodsList.add(goods);
    }
    flag=iGoodsService.saveBatch(goodsList);
    return flag==true?BaseResponse.success():BaseResponse.fail();
  }

  @Override
  public BaseResponse goodsStatusUpdate(GoodsDto goodsDto) {
    boolean flag=iGoodsService.goodsStatusUpdate(goodsDto);
    return flag==true?BaseResponse.success():BaseResponse.fail();
  }

  @Override
  public BaseResponse goodsUnderUpdate(GoodsDto goodsDto) {
    boolean flag=iGoodsService.goodsUnderUpdate(goodsDto);
    return flag==true?BaseResponse.success():BaseResponse.fail();
  }

  @Override
  public BaseResponse goodsReleaseUpdate(GoodsDto goodsDto) {
    boolean flag=iGoodsService.goodsReleaseUpdate(goodsDto);
    return flag==true?BaseResponse.success():BaseResponse.fail();
  }

  @Override
  public BaseResponse goodInfoqueryTable(GoodsVo goodsVo) {
    IPage<GoodsVo> goodsVoIPage = iGoodsService.goodInfoqueryTable(goodsVo);
    logger.debug("goodsVoIPage: {}", goodsVoIPage);
    Map resultMap = new HashMap();
    resultMap.put("records", goodsVoIPage.getRecords());
    resultMap.put("total", goodsVoIPage.getTotal());
    resultMap.put("page", (null != goodsVo.getPage()) ? Long.valueOf(goodsVo.getPage()) : null);
    resultMap.put("size", goodsVoIPage.getSize());
    return BaseResponse.success(resultMap);
  }

  @Override
  public BaseResponse<List<GoodsProductVo>> goodProdInfoquery(String goodId) {
    List<GoodsProductVo> goodsProductVos=iGoodsService.goodProductQuery(goodId);
    return BaseResponse.success(goodsProductVos);
  }
}
