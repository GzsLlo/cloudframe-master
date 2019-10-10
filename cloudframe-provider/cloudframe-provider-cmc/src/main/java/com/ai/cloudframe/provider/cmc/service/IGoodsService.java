package com.ai.cloudframe.provider.cmc.service;

import com.ai.cloudframe.provider.api.sys.model.dto.GoodsDto;
import com.ai.cloudframe.provider.api.sys.model.vo.GoodsProductVo;
import com.ai.cloudframe.provider.api.sys.model.vo.GoodsVo;
import com.ai.cloudframe.provider.cmc.entity.Goods;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
public interface IGoodsService extends IService<Goods> {

      boolean goodsStatusUpdate(GoodsDto goodsDto);

      boolean goodsUnderUpdate(GoodsDto goodsDto);

      boolean goodsReleaseUpdate(GoodsDto goodsDto);

      IPage<GoodsVo> goodInfoqueryTable(GoodsVo goodsVo);

      List<GoodsProductVo> goodProductQuery(String goodId);

}
