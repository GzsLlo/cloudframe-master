package com.ai.cloudframe.provider.param.controller;


import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.provider.api.sys.model.dto.ParamDto;
import com.ai.cloudframe.provider.api.sys.service.ParamServiceApi;
import com.ai.cloudframe.provider.param.service.IParamService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import com.ai.cloudframe.common.base.support.BaseController;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-17
 */
//@Controller
//@RequestMapping("//param")
@RestController
@Api(description = "参数相关接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ParamController extends BaseController implements ParamServiceApi {

    @Autowired
    IParamService paramService;

    @Override
    public BaseResponse<String> codeToName(String dictTypeCode, String dictCode) {
        List<ParamDto> paramDtoList = paramService.codeToName(dictTypeCode,dictCode);
        logger.info("paramDtoList:",paramDtoList);
        if(paramDtoList.size()>0){
            return BaseResponse.success(paramDtoList.get(0).getDictName());
        }else {
            return BaseResponse.success();
        }
    }
}
