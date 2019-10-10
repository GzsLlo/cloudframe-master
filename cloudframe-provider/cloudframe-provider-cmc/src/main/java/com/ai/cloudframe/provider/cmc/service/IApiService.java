package com.ai.cloudframe.provider.cmc.service;

import com.ai.cloudframe.provider.api.sys.model.dto.ApiAppDto;
import com.ai.cloudframe.provider.cmc.entity.Api;
import com.ai.cloudframe.provider.cmc.entity.Product;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-18
 */
public interface IApiService extends IService<Api> {


    IPage<ApiAppDto> apiQuery(@RequestBody Map map);

    IPage<ApiAppDto> apiAppQuery(@RequestBody Map map);

    int updateApi(@RequestBody  com.ai.cloudframe.provider.cmc.entity.Api api);
}
