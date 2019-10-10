package com.ai.cloudframe.provider.cmc.mapper;

import com.ai.cloudframe.provider.api.sys.model.dto.GoodProductSettleDto;
import com.ai.cloudframe.provider.api.sys.model.vo.ProdIntroProdVo;
import com.ai.cloudframe.provider.cmc.entity.Product;
import com.ai.cloudframe.provider.cmc.entity.ProductGood;
import com.ai.cloudframe.provider.cmc.entity.ProductMutex;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-16
 */
public interface ProductMapper extends BaseMapper<Product> {

    IPage<Product> selectProduct(@Param("page") Page page, @Param("map") Map map);

    List<Product> selectOneProduct(@Param("map") Map map);


    @Select("SELECT PRODUCT_ID_1, PRODUCT_ID_2 FROM tf_asiainfo_product_mutex  WHERE PRODUCT_ID_1 = #{prodId}")
    List<ProductMutex> selectByProdIds(String prodId);

    IPage<ProdIntroProdVo> prodUniIntroQueryTable(@Param("page") Page page, @Param("prodIntroProdVo")ProdIntroProdVo prodIntroProdVo);


   IPage<GoodProductSettleDto> selectGoodProductSettle(@Param("page") Page page, @Param("map") Map map);

    List<GoodProductSettleDto> selectGoodProductSettleEffect(@Param("map") Map map);
}