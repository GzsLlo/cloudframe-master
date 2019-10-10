package com.ai.cloudframe.provider.cmc.mapper;

import com.ai.cloudframe.provider.api.sys.model.vo.GoodsOrderVo;
import com.ai.cloudframe.provider.cmc.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-17
 */
public interface OrderMapper extends BaseMapper<Order> {


  IPage<GoodsOrderVo> orderInfoqueryTable(@Param("page") Page page, @Param("goodsVo")GoodsOrderVo goodsVo);

}
