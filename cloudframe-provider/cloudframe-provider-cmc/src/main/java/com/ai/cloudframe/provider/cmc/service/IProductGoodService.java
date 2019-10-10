package com.ai.cloudframe.provider.cmc.service;

import com.ai.cloudframe.provider.api.sys.model.dto.OrderDto;
import com.ai.cloudframe.provider.cmc.entity.ProductGood;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-17
 */
public interface IProductGoodService extends IService<ProductGood> {
  List<OrderDto> getInfoByGoodId (String goodId);

}
