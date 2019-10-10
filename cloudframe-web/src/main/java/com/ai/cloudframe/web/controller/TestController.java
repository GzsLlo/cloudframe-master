package com.ai.cloudframe.web.controller;

import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.common.base.support.BaseController;
import com.ai.cloudframe.common.config.properties.CloudFrameProperties;
import com.ai.cloudframe.provider.api.sys.model.dto.UserDto;
import com.ai.cloudframe.provider.api.sys.service.UserFeignServiceApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * tangsz.
 */
@RestController
@Api(description = "测试接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = "*")
public class TestController extends BaseController {


  @Value("${cloudframe.test.key:111}")
  private  String  key;

  @RequestMapping(value = "/test.do", method = RequestMethod.GET)
  @ApiOperation("测试")
  @RequiresPermissions("sys:test:view")
  public BaseResponse test(){
    Map map = new HashMap<>();
    Subject currentUser = SecurityUtils.getSubject();
    if (currentUser.isAuthenticated()) {
      logger.info("subject.isAuthenticated,userName:{}, login success");
    }

    map.put("license", cloudFrameProperties.getSwagger().getLicense());
    map.put("key", key);
    BaseResponse<Map> response = BaseResponse.create(map);
    return response;
  }
}
