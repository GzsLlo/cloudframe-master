package com.ai.cloudframe.provider.cmc.controller;


import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.provider.api.sys.model.dto.IntroduceProductDto;
import com.ai.cloudframe.provider.api.sys.model.dto.ProductDto;
import com.ai.cloudframe.provider.api.sys.model.vo.ProductMutexVo;
import com.ai.cloudframe.provider.api.sys.service.IntroduceProductServiceApi;
import com.ai.cloudframe.provider.cmc.entity.IntroduceProduct;
import com.ai.cloudframe.provider.cmc.entity.Product;
import com.ai.cloudframe.provider.cmc.service.IIntroduceProductService;
import com.ai.cloudframe.provider.cmc.service.IProductService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import com.ai.cloudframe.common.base.support.BaseController;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-22
 */
@RestController
public class IntroduceProductController extends BaseController implements IntroduceProductServiceApi {



    @Autowired
    IIntroduceProductService iIntroduceProductService;


    @Override
    public BaseResponse<Map> selectIntroduceProduct(@RequestBody Map map) {
        IPage<IntroduceProduct> productList = iIntroduceProductService.selectIntroduceProduct(map);
        logger.debug("getElderMoveIntoInfo elderMoveIntoVoList : {}", productList);
        Map resultMap = new HashMap();
        resultMap.put("data", productList.getRecords());
        resultMap.put("total", productList.getTotal());
        resultMap.put("page", (null != map.get("page")) ? Long.valueOf(map.get("page").toString()) : null);
        resultMap.put("size", productList.getSize());
        return BaseResponse.success(resultMap);
    }

    @Override
    public BaseResponse newIntroduceProduct(@RequestBody IntroduceProductDto introduceProductDto) {
        logger.debug("input param : {}", introduceProductDto);
        BaseResponse result = null;
        try {
            result =  iIntroduceProductService.newIntroduceProduct(introduceProductDto) == true ? BaseResponse.success() : BaseResponse.fail();
        } catch (Exception e) {
            result = BaseResponse.fail();
        }
        return result;
    }

    @Override
    public BaseResponse introduceProductsStatusUpdate(IntroduceProductDto introduceProductDto) {
        boolean flag=iIntroduceProductService.introduceProductsStatusUpdate(introduceProductDto);
        return flag==true?BaseResponse.success():BaseResponse.fail();
    }

}
