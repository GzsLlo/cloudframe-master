package com.ai.cloudframe.provider.cmc.controller;


import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.provider.api.sys.model.dto.GoodProductSettleDto;
import com.ai.cloudframe.provider.api.sys.model.dto.ProductDto;
import com.ai.cloudframe.provider.api.sys.model.vo.ProdIntroProdVo;
import com.ai.cloudframe.provider.api.sys.model.vo.ProductMutexVo;
import com.ai.cloudframe.provider.api.sys.service.ProductServiceApi;
import com.ai.cloudframe.provider.cmc.entity.Product;
import com.ai.cloudframe.provider.cmc.entity.ProductGood;
import com.ai.cloudframe.provider.cmc.entity.ProductMutex;
import com.ai.cloudframe.provider.cmc.service.IProductService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import com.ai.cloudframe.common.base.support.BaseController;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-16
 */
@RestController
public class ProductController extends BaseController  implements ProductServiceApi {


    @Autowired
    IProductService iProductService;


    @Override
    public BaseResponse<Map> selectProduct(@RequestBody Map map) {
        IPage<Product> productList = iProductService.selectProduct(map);
        logger.debug("getElderMoveIntoInfo elderMoveIntoVoList : {}", productList);
        Map resultMap = new HashMap();
        resultMap.put("data", productList.getRecords());
        resultMap.put("total", productList.getTotal());
        resultMap.put("page", (null != map.get("page")) ? Long.valueOf(map.get("page").toString()) : null);
        resultMap.put("size", productList.getSize());
        return BaseResponse.success(resultMap);
    }

    @Override
    public BaseResponse selectOneProduct(@RequestBody Map map) {
        List<Product> productList = iProductService.selectOneProduct(map);
        return BaseResponse.success(productList);
    }

    @Override
    public BaseResponse newProduct(@RequestBody ProductDto productDto) {
        logger.debug("input param : {}", productDto);
        return iProductService.newProduct(productDto)==true?BaseResponse.success():BaseResponse.fail();
    }


    @Override
    public BaseResponse productMutexCheck(String productIds) {
       ProductMutexVo productMutexVo =iProductService.productMutexCheck(productIds);
        return BaseResponse.success(productMutexVo);
    }

    @Override
    public BaseResponse<Map> prodUniIntroProdQueryTable(ProdIntroProdVo prodIntroProdVo) {
        if(prodIntroProdVo.getProductIdsSearch() == null) {
            IPage<ProdIntroProdVo> prodIntroProdVoIPage = iProductService.prodUniIntroProdQuery(prodIntroProdVo);
            logger.debug("prodIntroProdVoIPage: {}", prodIntroProdVoIPage);
            Map resultMap = new HashMap();
            resultMap.put("records", prodIntroProdVoIPage.getRecords());
            resultMap.put("total", prodIntroProdVoIPage.getTotal());
            resultMap.put("page", (null != prodIntroProdVo.getPage()) ? Long.valueOf(prodIntroProdVo.getPage()) : null);
            resultMap.put("size", prodIntroProdVoIPage.getSize());
            return BaseResponse.success(resultMap);
        }else{
            String prodIds=prodIntroProdVo.getProductIdsSearch();
            String[] prodIdsArr=prodIds.split(";");

            List<ProdIntroProdVo> resultsAll=new ArrayList<>();

            for (String prodId:prodIdsArr) {
                prodIntroProdVo.setProductId(prodId);
                IPage<ProdIntroProdVo> prodIntroProdVoIPage = iProductService.prodUniIntroProdQuery(prodIntroProdVo);
                List<ProdIntroProdVo> prodIntroProdVos = prodIntroProdVoIPage.getRecords();
                resultsAll.addAll(prodIntroProdVos);

            }

            Map resultMap = new HashMap();
            resultMap.put("records", resultsAll);
            return BaseResponse.success(resultMap);
        }
    }

    @Override
    public BaseResponse<Map> selectGoodProductSettle(@RequestBody Map map) {
        IPage<GoodProductSettleDto> productList = iProductService.selectGoodProductSettle(map);
        logger.debug("getElderMoveIntoInfo elderMoveIntoVoList : {}", productList);
        Map resultMap = new HashMap();
        resultMap.put("data", productList.getRecords());
        resultMap.put("total", productList.getTotal());
        resultMap.put("page", (null != map.get("page")) ? Long.valueOf(map.get("page").toString()) : null);
        resultMap.put("size", productList.getSize());

        return BaseResponse.success(resultMap);
    }
}
