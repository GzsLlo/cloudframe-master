package com.ai.cloudframe.provider.cmc.service;

import com.ai.cloudframe.provider.api.sys.model.vo.GoodsOrderVo;
import com.ai.cloudframe.provider.cmc.entity.Order;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-17
 */
public interface IOrderService extends IService<Order> {

  IPage<GoodsOrderVo> orderInfoqueryTable(GoodsOrderVo goodsOrderVo);

}
