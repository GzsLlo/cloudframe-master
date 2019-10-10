package com.ai.cloudframe.provider.cmc.service;

import com.ai.cloudframe.provider.api.sys.model.dto.IntroduceProductDto;
import com.ai.cloudframe.provider.api.sys.model.dto.ProductDto;
import com.ai.cloudframe.provider.api.sys.model.vo.ProductMutexVo;
import com.ai.cloudframe.provider.cmc.entity.IntroduceProduct;
import com.ai.cloudframe.provider.cmc.entity.Product;
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
 * @since 2019-07-22
 */
public interface IIntroduceProductService extends IService<IntroduceProduct> {


    IPage<IntroduceProduct> selectIntroduceProduct(@RequestBody Map map);

    boolean newIntroduceProduct(IntroduceProductDto introduceProductDto) throws Exception;

    boolean introduceProductsStatusUpdate(IntroduceProductDto introduceProductDto);

}
