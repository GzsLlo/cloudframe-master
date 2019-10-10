package com.ai.cloudframe.provider.cmc.mapper;

import com.ai.cloudframe.provider.api.sys.model.dto.OrderDto;
import com.ai.cloudframe.provider.cmc.entity.ProductGood;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-17
 */
public interface ProductGoodMapper extends BaseMapper<ProductGood> {
  List<OrderDto> getInfoByGoodId (String goodId);

}
