package com.ai.cloudframe.provider.cmc.controller;


import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.provider.api.sys.model.vo.DeployVo;
import com.ai.cloudframe.provider.api.sys.service.fallback.DeployServiceApi;
import com.ai.cloudframe.provider.cmc.service.IDeployService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import com.ai.cloudframe.common.base.support.BaseController;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-24
 */
@RestController
@Api(description = "主机信息相关服务", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DeployController extends BaseController implements DeployServiceApi {

  @Autowired
  private IDeployService iDeployService;

  /**
   * 主机信息查询.
   *
   * @param params
   * @return
   */
  @Override
  public BaseResponse<Map> getDeployInfo(Map params) {
    logger.info("DeployController.getDeployInfo params : {}", params);

    IPage<DeployVo> deployVoList = iDeployService.getDeployInfo(params);

    logger.info("DeployController.getDeployInfo deployVoList : {}", deployVoList);

    Map resultMap = new HashMap();
    resultMap.put("records", deployVoList.getRecords());
    resultMap.put("total", deployVoList.getTotal());
    resultMap.put("page", (null != params.get("page")) ? Long.valueOf(params.get("page").toString()) : null);
    resultMap.put("size", deployVoList.getSize());
    logger.debug("DeployController.getDeployInfo resultMap : {}", resultMap);

    return BaseResponse.success(resultMap);
  }

  /**
   * 编辑主机信息.
   *
   * @param deployVo
   * @return
   */
  @Override
  public BaseResponse<Map> updateDeployInfo(DeployVo deployVo) {
    logger.debug("DeployController.updateDeployInfo deployVo : {}", deployVo);

    boolean flag = iDeployService.updateDeployInfo(deployVo);

    logger.debug("DeployController.updateDeployInfo flag : {}", flag);
    return flag == true ? BaseResponse.success() : BaseResponse.fail();
  }
}
