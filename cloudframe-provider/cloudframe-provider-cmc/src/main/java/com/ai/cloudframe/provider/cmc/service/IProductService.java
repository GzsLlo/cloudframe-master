package com.ai.cloudframe.provider.cmc.service;

import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.provider.api.sys.model.dto.GoodProductSettleDto;
import com.ai.cloudframe.provider.api.sys.model.dto.ProductDto;
import com.ai.cloudframe.provider.api.sys.model.vo.ProdIntroProdVo;
import com.ai.cloudframe.provider.api.sys.model.vo.ProductMutexVo;
import com.ai.cloudframe.provider.cmc.entity.Product;
import com.ai.cloudframe.provider.cmc.entity.ProductGood;
import com.ai.cloudframe.provider.cmc.entity.ProductMutex;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-16
 */
public interface IProductService extends IService<Product> {

    IPage<Product> selectProduct(@RequestBody Map map);

    List<Product> selectOneProduct(@RequestBody Map map);

    boolean newProduct(ProductDto productDto);

    ProductMutexVo productMutexCheck(String productIds);

    IPage<ProdIntroProdVo> prodUniIntroProdQuery(ProdIntroProdVo prodIntroProdVo);


    IPage<GoodProductSettleDto> selectGoodProductSettle(@RequestBody Map map);
}
