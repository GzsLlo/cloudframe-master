package com.ai.cloudframe.provider.param.service;

import com.ai.cloudframe.provider.api.sys.model.dto.ParamDto;
import com.ai.cloudframe.provider.param.entity.Param;
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
public interface IParamService extends IService<Param> {

    List<ParamDto> codeToName(String dictTypeCode, String dictCode);
}
