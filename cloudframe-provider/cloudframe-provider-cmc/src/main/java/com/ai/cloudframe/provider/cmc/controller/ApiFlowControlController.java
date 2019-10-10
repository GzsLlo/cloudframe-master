package com.ai.cloudframe.provider.cmc.controller;


import com.ai.cloudframe.common.base.constant.GlobalConstant;
import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.common.base.util.DateUtil;
import com.ai.cloudframe.provider.api.sys.model.dto.ApiDto;
import com.ai.cloudframe.provider.api.sys.model.dto.ApiFlowControlDto;
import com.ai.cloudframe.provider.api.sys.model.vo.PageVo;
import com.ai.cloudframe.provider.api.sys.service.ApiFlowControlServiceApi;
import com.ai.cloudframe.provider.cmc.entity.ApiFlowControl;
import com.ai.cloudframe.provider.cmc.entity.Channel;
import com.ai.cloudframe.provider.cmc.service.IApiFlowControlService;
import com.ai.cloudframe.provider.cmc.service.IChannelService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-18
 */
@RestController
@Api(description = "流控相关接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiFlowControlController extends BaseController implements ApiFlowControlServiceApi {

  @Autowired
  IApiFlowControlService iApiFlowControlService;

  @Autowired
  IChannelService iChannelService;

  @Override
  public BaseResponse<PageVo<ApiFlowControlDto>> queryApiFlowControlList(ApiFlowControlDto dto) {
    QueryWrapper<ApiFlowControl> queryWrapper = new QueryWrapper<>();
    Timestamp now = new Timestamp(DateUtil.nowAbsMilliSeconds());
    queryWrapper .le("START_TIME",now)
        .gt("END_TIME",now);
    if(StringUtils.isNotEmpty(dto.getAbilityName())){
      queryWrapper.like("ABILITY_NAME",dto.getAbilityName());
    }
    if(StringUtils.isNotEmpty(dto.getControlId())){
      queryWrapper.eq("CONTROL_ID",dto.getControlId());
    }
    if(StringUtils.isNotEmpty(dto.getFlowControlType())){
      queryWrapper.like("FLOW_CONTROL_TYPE",dto.getFlowControlType());
    }

    IPage<ApiFlowControl> page = new Page<>();
    page.setCurrent(dto.getIndex());
    page.setSize(dto.getPageSize());
    IPage<ApiFlowControl> result = iApiFlowControlService.page(page,queryWrapper);
    PageVo<ApiFlowControlDto> vo = new PageVo();

    vo.setCurrent(result.getCurrent());
    vo.setPages(result.getPages());
    vo.setSize(result.getSize());
    vo.setTotal(result.getTotal());
    List<ApiFlowControlDto> resultList = result.getRecords().stream().map(apiFlowControl -> {
      ApiFlowControlDto apiFlowControlDto = new ApiFlowControlDto();
      apiFlowControlDto.setAbilityName(apiFlowControl.getAbilityName());
      apiFlowControlDto.setApiId(apiFlowControl.getApiId());
      apiFlowControlDto.setChannelId(apiFlowControl.getChannelId());
      apiFlowControlDto.setComment(apiFlowControl.getComment());
      apiFlowControlDto.setControlId(apiFlowControl.getControlId());
      apiFlowControlDto.setEndTime(DateUtil.getCurrentDateTime(apiFlowControl.getEndTime(),DateUtil.SEG_YYYYMMDD_HHMMSS));
      apiFlowControlDto.setFlowControlType(apiFlowControl.getFlowControlType());//code2name
      apiFlowControlDto.setLimitCount(apiFlowControl.getLimitCount());
      apiFlowControlDto.setLimitUnitTime(apiFlowControl.getLimitUnitTime());//code2name
      apiFlowControlDto.setStartTime(DateUtil.getCurrentDateTime(apiFlowControl.getStartTime(),DateUtil.SEG_YYYYMMDD_HHMMSS));
      apiFlowControlDto.setTimeInterval(apiFlowControl.getTimeInterval());
      QueryWrapper<Channel> channelQueryWrapper = new QueryWrapper<>();
      if(StringUtils.isNotEmpty(apiFlowControlDto.getChannelId())){
        channelQueryWrapper.eq("CHANNEL_ID",apiFlowControlDto.getChannelId());
        Channel channel = iChannelService.getOne(channelQueryWrapper,false);
        apiFlowControlDto.setChannelName(channel==null?"":channel.getChannelName());
      }else {
        apiFlowControlDto.setChannelName("");
      }

      return apiFlowControlDto;
    }).collect(Collectors.toList());
    vo.setData(resultList);

    return BaseResponse.success(vo);
  }

  @Override
  @Transactional
  public BaseResponse<Map> saveApiFlowControl(ApiFlowControlDto dto) {
    ApiFlowControl apiFlowControl = new ApiFlowControl();
    apiFlowControl.setAbilityName(dto.getAbilityName());
    apiFlowControl.setApiId(dto.getApiId());
    apiFlowControl.setChannelId(dto.getChannelId());
    apiFlowControl.setComment(dto.getComment());
//    apiFlowControl.setControlId(dto.getControlId());
    apiFlowControl.setStartTime(new Timestamp(DateUtil.nowAbsMilliSeconds()));
    apiFlowControl.setFlowControlType(dto.getFlowControlType());
    apiFlowControl.setLimitCount(dto.getLimitCount());
    apiFlowControl.setLimitUnitTime(dto.getLimitUnitTime());
    apiFlowControl.setEndTime(new Timestamp(DateUtil.string2Date(GlobalConstant.END_DATE,DateUtil.SEG_YYYYMMDD).getTime()));
    apiFlowControl.setTimeInterval(dto.getTimeInterval());
    iApiFlowControlService.save(apiFlowControl);
    return BaseResponse.success();
  }

  @Override
  public BaseResponse<Map> modifyApiFlowControl(ApiFlowControlDto dto) {
    ApiFlowControl apiFlowControl = new ApiFlowControl();
    if(StringUtils.isEmpty(dto.getControlId())){
      return BaseResponse.fail(null,"required control id");
    }
    apiFlowControl.setControlId(dto.getControlId());
    if(GlobalConstant.MODIFY_TAG.UPDATE.equals(dto.getFlag())){

      if(StringUtils.isNotEmpty(dto.getFlowControlType())){
        apiFlowControl.setFlowControlType(dto.getFlowControlType());
      }
      if(StringUtils.isNotEmpty(dto.getAbilityName())){
        apiFlowControl.setAbilityName(dto.getAbilityName());
      }
      if(StringUtils.isNotEmpty(dto.getChannelId())){
        apiFlowControl.setChannelId(dto.getChannelId());
      }
      if(StringUtils.isNotEmpty(dto.getLimitUnitTime())){
        apiFlowControl.setLimitUnitTime(dto.getLimitUnitTime());
      }
      if(StringUtils.isNotEmpty(dto.getApiId())){
        apiFlowControl.setApiId(dto.getApiId());
      }
      if(StringUtils.isNotEmpty(dto.getComment())){
        apiFlowControl.setComment(dto.getComment());
      }
      if(StringUtils.isNotEmpty(dto.getEndTime())){
        apiFlowControl.setEndTime(new Timestamp(DateUtil.string2Date(dto.getEndTime(),DateUtil.SEG_YYYYMMDD_HHMMSS).getTime()));
      }
      if(dto.getLimitCount() != 0){
        apiFlowControl.setLimitCount(dto.getLimitCount());
      }
      if(dto.getTimeInterval() != 0){
        apiFlowControl.setTimeInterval(dto.getTimeInterval());
      }
    }else if(GlobalConstant.MODIFY_TAG.DELETE.equals(dto.getFlag())){
      apiFlowControl.setEndTime(new Timestamp(DateUtil.nowAbsMilliSeconds()));
    }else {
      return BaseResponse.fail(null,"flag wrong");
    }

    iApiFlowControlService.updateApiFlowById(apiFlowControl);
    return BaseResponse.success();
  }

}
