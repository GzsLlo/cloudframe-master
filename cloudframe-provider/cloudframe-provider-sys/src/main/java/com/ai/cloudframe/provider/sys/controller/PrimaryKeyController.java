package com.ai.cloudframe.provider.sys.controller;


import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.common.base.support.BaseController;
import com.ai.cloudframe.provider.api.sys.service.PrimaryKeyFeginServiceApi;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

/**
 * <p>
 * 生成36位主键uuid
 * </p>
 *
 * @author Automated procedures
 * @since 2019-05-31
 */
@Controller
public class PrimaryKeyController extends BaseController implements PrimaryKeyFeginServiceApi {

  @Override
  public BaseResponse<String> createPrimaryKey() {
    UUID uuid = UUID.randomUUID();
    String uuidStr = uuid.toString();
    logger.debug("createPrimaryKey:{}", uuidStr);
    return BaseResponse.success(uuidStr);
  }
}
