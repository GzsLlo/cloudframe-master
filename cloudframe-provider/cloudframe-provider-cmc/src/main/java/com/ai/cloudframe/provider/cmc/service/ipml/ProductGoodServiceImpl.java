package com.ai.cloudframe.provider.cmc.service.ipml;

import com.ai.cloudframe.provider.api.sys.model.dto.OrderDto;
import com.ai.cloudframe.provider.cmc.entity.ProductGood;
import com.ai.cloudframe.provider.cmc.mapper.ProductGoodMapper;
import com.ai.cloudframe.provider.cmc.service.IProductGoodService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-17
 */
@Service
public class ProductGoodServiceImpl extends ServiceImpl<ProductGoodMapper, ProductGood> implements IProductGoodService {

  @Override
  public List<OrderDto> getInfoByGoodId(String goodId) {
    return this.baseMapper.getInfoByGoodId(goodId);
  }
}
