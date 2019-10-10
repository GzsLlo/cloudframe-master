package com.ai.cloudframe.provider.cmc.controller;


import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.provider.api.sys.model.dto.ProductGoodDto;
import com.ai.cloudframe.provider.api.sys.service.GoodProductFeignServiceApi;
import com.ai.cloudframe.provider.cmc.entity.ProductGood;
import com.ai.cloudframe.provider.cmc.service.IGoodsService;
import com.ai.cloudframe.provider.cmc.service.IProductGoodService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import com.ai.cloudframe.common.base.support.BaseController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-17
 */
@Controller
public class ProductGoodController extends BaseController implements GoodProductFeignServiceApi {

  @Autowired
  IProductGoodService iProductGoodService;

  @Override
  public BaseResponse saveGoodsProduct(List<ProductGoodDto> productGoodDtoList) {
    boolean flag;
    List<ProductGood> productGoodList=new ArrayList<>();

    for (ProductGoodDto each:productGoodDtoList) {
      ProductGood productGood=new ProductGood();
      BeanUtils.copyProperties(each,productGood);
      productGoodList.add(productGood);
    }

    flag=iProductGoodService.saveBatch(productGoodList);
    return flag==true?BaseResponse.success():BaseResponse.fail();
  }
}
