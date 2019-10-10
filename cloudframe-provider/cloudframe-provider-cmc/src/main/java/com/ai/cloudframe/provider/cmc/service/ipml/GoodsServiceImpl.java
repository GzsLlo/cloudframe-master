package com.ai.cloudframe.provider.cmc.service.ipml;

import com.ai.cloudframe.provider.api.sys.model.dto.GoodsDto;
import com.ai.cloudframe.provider.api.sys.model.vo.GoodsProductVo;
import com.ai.cloudframe.provider.api.sys.model.vo.GoodsVo;
import com.ai.cloudframe.provider.cmc.entity.Goods;
import com.ai.cloudframe.provider.cmc.mapper.GoodsMapper;
import com.ai.cloudframe.provider.cmc.service.IGoodsService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

  @Override
  public boolean goodsStatusUpdate(GoodsDto goodsDto) {
    int i = this.baseMapper.goodsStatusUpdate(goodsDto);
    return i == 1;
  }

  @Override
  public boolean goodsUnderUpdate(GoodsDto goodsDto) {
    int i = this.baseMapper.goodsUnderUpdate(goodsDto);
    return i == 1;
  }

  @Override
  public boolean goodsReleaseUpdate(GoodsDto goodsDto) {
    int i = this.baseMapper.goodsReleaseUpdate(goodsDto);
    return i == 1;
  }

  @Override
  public IPage<GoodsVo> goodInfoqueryTable(GoodsVo goodsVo) {

    Long pageNum = (null != goodsVo.getPage()) ? Long.valueOf( goodsVo.getPage()) : null;
    Long pageSize = (null !=  goodsVo.getPageSize())
        ? Long.valueOf(goodsVo.getPageSize()) : null;

    Page<GoodsVo> page = new Page<>(pageNum, pageSize);

    return this.baseMapper.goodInfoqueryTable(page,goodsVo);
  }

  @Override
  public List<GoodsProductVo> goodProductQuery(String goodId) {
    return this.baseMapper.goodProdInfoquery(goodId);
  }
}
