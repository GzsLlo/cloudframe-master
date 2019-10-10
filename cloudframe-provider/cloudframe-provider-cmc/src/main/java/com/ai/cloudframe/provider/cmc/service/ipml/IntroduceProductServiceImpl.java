package com.ai.cloudframe.provider.cmc.service.ipml;

import com.ai.cloudframe.provider.api.sys.model.dto.IntroduceProductDto;
import com.ai.cloudframe.provider.api.sys.model.dto.ProductDto;
import com.ai.cloudframe.provider.api.sys.model.vo.ProductMutexVo;
import com.ai.cloudframe.provider.cmc.entity.IntroduceProduct;
import com.ai.cloudframe.provider.cmc.entity.Product;
import com.ai.cloudframe.provider.cmc.entity.ProductMutex;
import com.ai.cloudframe.provider.cmc.mapper.*;
import com.ai.cloudframe.provider.cmc.service.IIntroduceProductService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-22
 */
@Service
public class IntroduceProductServiceImpl extends ServiceImpl<IntroduceProductMapper, IntroduceProduct> implements IIntroduceProductService {


    @Autowired(required = false)
    private IntroduceProductMapper introduceProductMapper = null;


    @Autowired(required = false)
    private ApiMapper apiMapper = null;

    @Autowired(required = false)
    private ApplicationMapper applicationMapper = null;

    @Override
    public IPage<IntroduceProduct> selectIntroduceProduct(Map map) {
        Long pageNum = ((Integer) map.get("page")).longValue();
        Long pageSize = ((Integer) map.get("pageSize")).longValue();
        Page<Product> page = new Page<>(pageNum, pageSize);
        return introduceProductMapper.selectIntroduceProduct(page, map);
    }


    @Override
    @Transactional(rollbackFor=Exception.class)
    public boolean newIntroduceProduct(IntroduceProductDto introduceProductDto) throws Exception{

        IntroduceProduct introduceProduct = new IntroduceProduct();
        BeanUtils.copyProperties(introduceProductDto, introduceProduct);
        UUID uuid = UUID.randomUUID();
        String uuidStr = uuid.toString();
        introduceProduct.setIntroduceProductId(uuidStr);

        int flag = introduceProductMapper.insert(introduceProduct);
        if (flag < 1) {
            return false;
        }

        Map<String, Object>  map = new HashMap<>();
        map.put("autoId", introduceProduct.getAtomId());
        map.put("status", "1");//0：未引入 1：已引入

        int flag1 = apiMapper.updateByApiId(map);
        int flag2 = applicationMapper.updateByAppId(map);

        if ((flag1 == 1 && flag2 == 1)||(flag1 == 0 && flag2 == 0) ) {
            throw new Exception("");
        }

        return true;
    }

    @Override
    public boolean introduceProductsStatusUpdate(IntroduceProductDto introduceProductDto) {
        int i = this.baseMapper.introduceProductsStatusUpdate(introduceProductDto.getIntroduceProductId(),introduceProductDto.getIntroduceStatus(), introduceProductDto.getExamineDate(),introduceProductDto.getExamineDetail(),introduceProductDto.getProductPrice(),introduceProductDto.getExamineUserId(),introduceProductDto.getReleaseDate());
        return i == 1;
    }


}
