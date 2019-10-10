package com.ai.cloudframe.provider.param.service.ipml;

import com.ai.cloudframe.provider.api.sys.model.dto.ParamDto;
import com.ai.cloudframe.provider.param.entity.Param;
import com.ai.cloudframe.provider.param.mapper.ParamMapper;
import com.ai.cloudframe.provider.param.service.IParamService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class ParamServiceImpl extends ServiceImpl<ParamMapper, Param> implements IParamService {

    @Override
    public List<ParamDto> codeToName(String dictTypeCode, String dictCode) {
        return this.baseMapper.codeToName(dictTypeCode,dictCode);
    }
}
