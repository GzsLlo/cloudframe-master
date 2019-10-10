package com.ai.cloudframe.provider.cmc.controller;


import com.ai.cloudframe.common.base.constant.GlobalConstant;
import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.common.base.util.DateUtil;
import com.ai.cloudframe.provider.api.sys.model.dto.ChannelAuthorizeDto;
import com.ai.cloudframe.provider.api.sys.model.dto.ChannelDto;
import com.ai.cloudframe.provider.api.sys.model.vo.ChannelVo;
import com.ai.cloudframe.provider.api.sys.model.vo.PageVo;
import com.ai.cloudframe.provider.api.sys.service.ChannelServiceApi;
import com.ai.cloudframe.provider.cmc.entity.Channel;
import com.ai.cloudframe.provider.cmc.service.IChannelService;
import com.baomidou.mybatisplus.annotation.TableField;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-25
 */
@RestController
@Api(description = "渠道相关接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ChannelController extends BaseController implements ChannelServiceApi {

  @Autowired
  IChannelService iChannelService;

  @Override
  @Transactional
  public BaseResponse<Map> saveChannel(ChannelDto dto) {
    Channel channel = new Channel();
    channel.setChannelId(dto.getChannelId());
    channel.setChannelName(dto.getChannelName());
    channel.setContactPhone(dto.getContactPhone());
    channel.setContacts(dto.getContacts());
    channel.setEndDate(new Timestamp(DateUtil.string2Date(GlobalConstant.END_DATE,DateUtil.SEG_YYYYMMDD).getTime()));
    channel.setStartDate(new Timestamp(DateUtil.nowAbsMilliSeconds()));

    iChannelService.save(channel);
    return BaseResponse.success(new HashMap());
  }

  @Override
  public BaseResponse<PageVo<ChannelDto>> channelList(ChannelDto dto) {
    QueryWrapper<Channel> queryWrapper = new QueryWrapper<>();
    Timestamp now = new Timestamp(DateUtil.nowAbsMilliSeconds());
    queryWrapper.le("START_DATE",now)
        .gt("END_DATE",now);
    IPage<Channel> page = new Page<>();
    page.setSize(dto.getPageSize());
    page.setCurrent(dto.getIndex());
    if(StringUtils.isNotEmpty(dto.getChannelId())){
      queryWrapper.eq("CHANNEL_ID",dto.getChannelId());
    }
    if(StringUtils.isNotEmpty(dto.getChannelName())){
      queryWrapper.like("CHANNEL_NAME",dto.getChannelName());
    }
    if(StringUtils.isNotEmpty(dto.getContactPhone())){
      queryWrapper.like("CONTACT_PHONE",dto.getContactPhone());
    }
    if(StringUtils.isNotEmpty(dto.getContacts())){
      queryWrapper.like("CONTACTS",dto.getContacts());
    }
    IPage<Channel> pageRes = iChannelService.page(page,queryWrapper);
    PageVo<ChannelDto> vo = new PageVo();
    List<ChannelDto> resultList = pageRes.getRecords().stream().map(
        row -> {
          ChannelDto channelDto = new ChannelDto();
          channelDto.setChannelId(row.getChannelId());
          channelDto.setChannelName(row.getChannelName());
          channelDto.setContactPhone(row.getContactPhone());
          channelDto.setContacts(row.getContacts());
          channelDto.setStartDate(DateUtil.getCurrentDateTime(row.getStartDate(),DateUtil.SEG_YYYYMMDD_HHMMSS));
          channelDto.setEndDate(DateUtil.getCurrentDateTime(row.getEndDate(),DateUtil.SEG_YYYYMMDD_HHMMSS));
          channelDto.setSeqId(row.getSeqId());
          return channelDto;
        }).collect(Collectors.toList());
    vo.setData(resultList);
    vo.setTotal(pageRes.getTotal());
    vo.setSize(pageRes.getSize());
    vo.setPages(pageRes.getPages());
    vo.setCurrent(pageRes.getCurrent());
    return BaseResponse.success(vo);
  }

  @Override
  public BaseResponse channelInfoList(ChannelDto dto) {
    IPage<ChannelDto> channelDtoIPage = iChannelService.channelInfoqueryTable(dto);
    logger.debug("channelDtoIPage: {}", dto);
    Map resultMap = new HashMap();
    resultMap.put("records", channelDtoIPage.getRecords());
    resultMap.put("total", channelDtoIPage.getTotal());
    resultMap.put("page", (null != dto.getPage()) ? Long.valueOf(dto.getPage()) : null);
    resultMap.put("size", channelDtoIPage.getSize());
    return BaseResponse.success(resultMap);
  }

  @Override
  public BaseResponse getTableByChannelId(Map selectParams) {
    Map resultMap = new HashMap();
    IPage<ChannelVo> billlist=iChannelService.getTableByChannelId(selectParams);
    resultMap.put("records",billlist.getRecords());
    resultMap.put("total",billlist.getTotal());
    resultMap.put("page",Long.valueOf(selectParams.get("page").toString()));
    resultMap.put("size",billlist.getSize());
    logger.debug("billlist:{}",billlist);
    return BaseResponse.success(resultMap);
  }

  @Override
  public BaseResponse channelByChanId(ChannelDto param) {
    QueryWrapper<Channel> apiTokenQueryWrapper = new QueryWrapper<>();
    apiTokenQueryWrapper.eq("CHANNEL_ID",param.getChannelId());

    List<Channel> apiTokens = iChannelService.list(apiTokenQueryWrapper);
    String channelName="";
    if(apiTokens.size()>0){
      channelName=apiTokens.get(0).getChannelName();
    }
    return  BaseResponse.success(channelName);
  }

}
