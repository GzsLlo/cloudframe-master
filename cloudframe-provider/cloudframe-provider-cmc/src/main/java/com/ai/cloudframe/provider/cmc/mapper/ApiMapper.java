package com.ai.cloudframe.provider.cmc.mapper;

import com.ai.cloudframe.provider.api.sys.model.dto.ApiAppDto;
import com.ai.cloudframe.provider.cmc.entity.Api;
import com.ai.cloudframe.provider.cmc.entity.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-18
 */
public interface ApiMapper extends BaseMapper<Api> {

    IPage<ApiAppDto> apiQuery(@Param("page") Page page, @Param("map") Map map);

    IPage<ApiAppDto> apiAppQuery(@Param("page") Page page, @Param("map") Map map);

    int updateByApiId(@Param("map") Map map);

    int updateApiById(@Param("api") com.ai.cloudframe.provider.cmc.entity.Api api);

}
