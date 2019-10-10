package com.ai.cloudframe.provider.cmc.service.ipml;

import com.ai.cloudframe.provider.api.sys.model.vo.GoodsOrderVo;
import com.ai.cloudframe.provider.cmc.entity.Order;
import com.ai.cloudframe.provider.cmc.mapper.OrderMapper;
import com.ai.cloudframe.provider.cmc.service.IOrderService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-17
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

  @Override
  public IPage<GoodsOrderVo> orderInfoqueryTable(GoodsOrderVo goodsOrderVo) {

    Long pageNum = (null != goodsOrderVo.getPage()) ? Long.valueOf(goodsOrderVo.getPage()) : null;
    Long pageSize = (null != goodsOrderVo.getPageSize())
        ? Long.valueOf(goodsOrderVo.getPageSize()) : null;

    Page<GoodsOrderVo> page = new Page<>(pageNum, pageSize);

    return this.baseMapper.orderInfoqueryTable(page, goodsOrderVo);

  }
}
