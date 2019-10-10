package com.ai.cloudframe.provider.api.sys.service;

import com.ai.cloudframe.common.base.response.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@FeignClient(value = "${cloudframe.microservice.sys}")
@Component
public interface PrimaryKeyFeginServiceApi {

    @PostMapping(value = "/sys/primaryKey")
    @ResponseBody
    BaseResponse<String> createPrimaryKey();

}
