package com.ai.cloudframe.provider.cmc.controller;


import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.provider.api.sys.model.dto.ApiDto;
import com.ai.cloudframe.provider.api.sys.model.dto.ApiStatisticsDto;
import com.ai.cloudframe.provider.api.sys.model.dto.ApplicationDto;
import com.ai.cloudframe.provider.api.sys.model.vo.ApplicationProductVo;
import com.ai.cloudframe.provider.api.sys.service.ApiStatisticsServiceApi;
import com.ai.cloudframe.provider.cmc.entity.ApiStatistics;
import com.ai.cloudframe.provider.cmc.entity.Application;
import com.ai.cloudframe.provider.cmc.service.IApiStatisticsService;
import com.ai.cloudframe.provider.cmc.service.IApplicationService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import com.ai.cloudframe.common.base.support.BaseController;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-17
 */
@RestController
@Api(description = "能力调用相关接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiStatisticsController extends BaseController implements ApiStatisticsServiceApi {

  @Autowired
  IApiStatisticsService iApiStatisticsService;

  @Autowired
  IApplicationService iApplicationService;
  @Override
  @Transactional
  public BaseResponse<Map> insertStatistics(ApiStatisticsDto dto) {
    logger.info("---------   insertStatistics start  --------");
    Timestamp now = new Timestamp(new Date().getTime());
    QueryWrapper<Application> queryWrapper = new QueryWrapper<>();
    queryWrapper.le("START_DATE",now)
        .gt("END_DATE",now)
        .eq("ACTIVE_FLAG","1");
    Application application = iApplicationService.getOne(queryWrapper,false);
    if(application != null && StringUtils.isNotEmpty(application.getApiId())){
      ApiDto apiDto = new ApiDto();
      apiDto.setApiId(application.getApiId());
      ApplicationProductVo vo = iApplicationService.getApplicationProductRelation(apiDto);
      if(vo != null && StringUtils.isNotEmpty(vo.getProductId()) && StringUtils.isNotEmpty(vo.getGoodId())){
        ApiStatistics apiStatistics = new ApiStatistics();
        apiStatistics.setApiId(dto.getApiId());
        apiStatistics.setCallEndTime(dto.getCallEndTime());
        apiStatistics.setCallStartTime(dto.getCallStartTime());
        apiStatistics.setChannelId(dto.getChannelId());
        apiStatistics.setResultCode(dto.getResultCode());
        apiStatistics.setResultMessage(dto.getResultMessage());
        apiStatistics.setToken(dto.getToken());
        apiStatistics.setUseCount(dto.getUseCount());
        apiStatistics.setUserId(dto.getUserId());
        apiStatistics.setApplicationId(application.getApplicationId());
        apiStatistics.setIsChargeoff("0");
        apiStatistics.setGoodId(vo.getGoodId());
        apiStatistics.setProductId(vo.getProductId());
        iApiStatisticsService.save(apiStatistics);
      }else{
//        return BaseResponse.fail(null,"there's no product,goods info!");
      }

    }else {
      logger.warn("there's no effective application!");
//      return BaseResponse.fail(null,"there's no effective application!");
    }
    logger.info("---------   insertStatistics end  --------");
    return BaseResponse.success();
  }


}
