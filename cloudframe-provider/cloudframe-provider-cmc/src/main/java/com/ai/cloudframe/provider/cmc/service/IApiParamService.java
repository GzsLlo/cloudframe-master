package com.ai.cloudframe.provider.cmc.service;

import com.ai.cloudframe.provider.cmc.entity.ApiParam;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-18
 */
public interface IApiParamService extends IService<ApiParam> {

  int updateApiParam(@RequestBody ApiParam param);

}
