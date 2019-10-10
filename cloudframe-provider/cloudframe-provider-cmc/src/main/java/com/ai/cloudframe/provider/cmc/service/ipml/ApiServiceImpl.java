package com.ai.cloudframe.provider.cmc.service.ipml;

import com.ai.cloudframe.provider.api.sys.model.dto.ApiAppDto;
import com.ai.cloudframe.provider.cmc.entity.Api;
import com.ai.cloudframe.provider.cmc.entity.Product;
import com.ai.cloudframe.provider.cmc.mapper.ApiMapper;
import com.ai.cloudframe.provider.cmc.mapper.ProductMapper;
import com.ai.cloudframe.provider.cmc.service.IApiService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-18
 */
@Service
public class ApiServiceImpl extends ServiceImpl<ApiMapper, Api> implements IApiService {

    @Autowired(required = false)
    private ApiMapper apiMapper = null;


    @Override
    public IPage<ApiAppDto> apiQuery(Map map) {
        Long pageNum = ((Integer) map.get("page")).longValue();
        Long pageSize = ((Integer) map.get("pageSize")).longValue();
        Page<Product> page = new Page<>(pageNum, pageSize);
        return apiMapper.apiQuery(page, map);
    }

    @Override
    public IPage<ApiAppDto> apiAppQuery(Map map) {
        Long pageNum = ((Integer) map.get("page")).longValue();
        Long pageSize = ((Integer) map.get("pageSize")).longValue();
        Page<Product> page = new Page<>(pageNum, pageSize);
        return apiMapper.apiAppQuery(page, map);
    }

    @Override
    public int updateApi(Api api) {
        return apiMapper.updateApiById(api);
    }

}
