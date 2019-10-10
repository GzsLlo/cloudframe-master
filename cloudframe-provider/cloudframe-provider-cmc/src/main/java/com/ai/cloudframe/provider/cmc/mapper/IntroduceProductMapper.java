package com.ai.cloudframe.provider.cmc.mapper;

import com.ai.cloudframe.provider.cmc.entity.IntroduceProduct;
import com.ai.cloudframe.provider.cmc.entity.Product;
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
 * @since 2019-07-22
 */
public interface IntroduceProductMapper extends BaseMapper<IntroduceProduct> {



    IPage<IntroduceProduct> selectIntroduceProduct(@Param("page") Page page, @Param("map") Map map);


    int introduceProductsStatusUpdate(@Param("introduceProductId") String introduceProductId, @Param("introduceStatus") String introduceStatus, @Param("examineDate") LocalDateTime examineDate, @Param("examineDetail") String examineDetail, @Param("productPrice") String productPrice, @Param("examineUserId") String examineUserId,@Param("releaseDate") LocalDateTime releaseDate);

}
