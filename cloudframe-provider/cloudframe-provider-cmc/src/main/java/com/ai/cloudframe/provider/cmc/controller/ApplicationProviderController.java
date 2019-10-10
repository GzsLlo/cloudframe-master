package com.ai.cloudframe.provider.cmc.controller;


import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.provider.api.sys.model.dto.ApiAppDto;
import com.ai.cloudframe.provider.api.sys.model.dto.ApiDto;
import com.ai.cloudframe.provider.api.sys.model.dto.ApplicationDto;
import com.ai.cloudframe.provider.api.sys.model.vo.AddApplicationVo;
import com.ai.cloudframe.provider.api.sys.model.vo.ApplicationProductVo;
import com.ai.cloudframe.provider.api.sys.service.ApplicationServiceApi;
import com.ai.cloudframe.provider.cmc.service.IApiService;
import com.ai.cloudframe.provider.cmc.service.IApplicationService;
import com.ai.cloudframe.provider.cmc.entity.Application;
import com.ai.cloudframe.provider.cmc.service.IAddApplicationService;
import com.ai.cloudframe.provider.cmc.service.IApplicationService;
import com.ai.cloudframe.provider.cmc.service.IRolePermissionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;

import com.ai.cloudframe.common.base.support.BaseController;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-19
 */
@RestController
@Api(description = "应用相关服务", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApplicationProviderController extends BaseController implements ApplicationServiceApi {

  private static Logger logger = LoggerFactory.getLogger(ApiQueryController.class);

  @Autowired
  IAddApplicationService iAddApplicationService;

  @Autowired
  IApplicationService iApplicationService;

  @Autowired
  IRolePermissionService iRolePermissionService;

  @Override
  public BaseResponse<Map> applicationQuery(@RequestBody Map map) {
    IPage<ApiAppDto> productList = iApplicationService.applicationQuery(map);
    logger.debug("getElderMoveIntoInfo elderMoveIntoVoList : {}", productList);
    Map resultMap = new HashMap();
    resultMap.put("data", productList.getRecords());
    resultMap.put("total", productList.getTotal());
    resultMap.put("page", (null != map.get("page")) ? Long.valueOf(map.get("page").toString()) : null);
    resultMap.put("size", productList.getSize());
    return BaseResponse.success(resultMap);
  }

  /**
   * 新增应用.
   *
   * @param addApplicationVos
   * @return
   */
  @Override
  public BaseResponse<Map> createApplication(List<AddApplicationVo> addApplicationVos) {
    logger.debug("ApplicationProviderController.createApplication addApplicationVos : {}", addApplicationVos);

    boolean flag = iAddApplicationService.addApplication(addApplicationVos);
    logger.debug("ApplicationProviderController.createApplication flag : {}", flag);

    return flag == true ? BaseResponse.success() : BaseResponse.fail();
  }

  /**
   * 查询应用.
   *
   * @param param
   * @return
   */
  @Override
  public BaseResponse<Map> getApplicationInfo(@RequestBody Map param) {
    logger.info("ApplicationProviderController.getApplicationInfo param : {}", param);

    IPage<Application> applicationList = iApplicationService.getApplicationInfo(param);
    logger.debug("ApplicationProviderController.getApplicationInfo applicationList : {}", applicationList);

    Map resultMap = new HashMap();
    resultMap.put("records", applicationList.getRecords());
    resultMap.put("total", applicationList.getTotal());
    resultMap.put("page", (null != param.get("page")) ? Long.valueOf(param.get("page").toString()) : null);
    resultMap.put("size", applicationList.getSize());
    logger.debug("ApplicationProviderController.getApplicationInfo resultMap : {}", resultMap);

    return BaseResponse.success(resultMap);
  }

  /**
   * 编辑应用.
   *
   * @param applicationVo
   * @return
   */
  @Override
  public BaseResponse<Map> updateApplication(AddApplicationVo applicationVo) {
    logger.debug("ApplicationProviderController.updateApplication applicationVo : {}", applicationVo);

    boolean flag = iApplicationService.updateApplication(applicationVo);

    logger.debug("ApplicationProviderController.updateApplication flag : {}", flag);
    return flag == true ? BaseResponse.success() : BaseResponse.fail();
  }

  /**
   * 编辑应用权限.
   *
   * @param applicationVo
   * @return
   */
  @Override
  public BaseResponse<Map> updateApplicationRolePermission(AddApplicationVo applicationVo) {
    logger.debug("ApplicationProviderController.updateApplicationRolePermission applicationVo : {}", applicationVo);

    boolean flag = iRolePermissionService.updateRolePermission(applicationVo.getRolePermissions());

    logger.debug("ApplicationProviderController.updateApplicationRolePermission flag : {}", flag);
    return flag == true ? BaseResponse.success() : BaseResponse.fail();
  }

  /**
   * 部署应用.
   *
   * @return
   */
  @Override
  public BaseResponse<Map> deployApplication() {
    logger.debug("ApplicationProviderController.deployApplication begin");

    boolean flag = iApplicationService.deployApplication();

    logger.debug("ApplicationProviderController.deployApplication flag : {}", flag);
    return flag == true ? BaseResponse.success() : BaseResponse.fail();
  }

  /**
   * 启动/停止应用.
   *
   * @return
   */
  @Override
  public BaseResponse<Map> startOrStopApplication(String tag) {
    logger.debug("ApplicationProviderController.startOrStopApplication tag : {}", tag);

    boolean flag = iApplicationService.startOrStopApplication(tag);

    logger.debug("ApplicationProviderController.startOrStopApplication flag : {}", flag);
    return flag == true ? BaseResponse.success() : BaseResponse.fail();
  }

  @Override
  public BaseResponse<ApplicationProductVo> applicationProductRelation(ApiDto dto) {
    ApplicationProductVo vo = iApplicationService.getApplicationProductRelation(dto);
    return BaseResponse.success(vo);
  }
}
