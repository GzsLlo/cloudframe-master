package com.ai.cloudframe.provider.cmc.mapper;

import com.ai.cloudframe.provider.api.sys.model.dto.GoodsDto;
import com.ai.cloudframe.provider.api.sys.model.vo.GoodsProductVo;
import com.ai.cloudframe.provider.api.sys.model.vo.GoodsVo;
import com.ai.cloudframe.provider.cmc.entity.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-17
 */
public interface GoodsMapper extends BaseMapper<Goods> {

//  int goodsStatusUpdate(@Param("goodId") String goodId,@Param("examineStatus") String examineStatus);

  int goodsStatusUpdate(@Param("goodsDto")GoodsDto goodsDto);

  int goodsUnderUpdate(@Param("goodsDto")GoodsDto goodsDto);

  int goodsReleaseUpdate(@Param("goodsDto")GoodsDto goodsDto);

  IPage<GoodsVo> goodInfoqueryTable(@Param("page") Page page, @Param("goodsVo")GoodsVo goodsVo);

  List<GoodsProductVo> goodProdInfoquery(@Param("goodId") String goodId);

}
