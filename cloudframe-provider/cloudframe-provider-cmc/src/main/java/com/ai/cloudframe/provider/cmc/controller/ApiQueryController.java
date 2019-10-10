/**
 * Copyright 2019 asiainfo Inc.
 **/

package com.ai.cloudframe.provider.cmc.controller;

import com.ai.cloudframe.common.base.constant.GlobalConstant;
import com.ai.cloudframe.common.base.enums.StatusEnum;
import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.common.base.support.BaseController;
import com.ai.cloudframe.common.base.util.DateUtil;
import com.ai.cloudframe.common.base.util.UUIDUtil;
import com.ai.cloudframe.provider.api.sys.model.dto.AbilityRegisterDto;
import com.ai.cloudframe.provider.api.sys.model.dto.ApiAppDto;
import com.ai.cloudframe.provider.api.sys.model.dto.ApiDto;
import com.ai.cloudframe.provider.api.sys.model.dto.ApiListDto;
import com.ai.cloudframe.provider.api.sys.model.dto.ApiParamDto;
import com.ai.cloudframe.provider.api.sys.model.vo.PageVo;
import com.ai.cloudframe.provider.api.sys.service.ApiServiceApi;
import com.ai.cloudframe.provider.cmc.entity.ApiChild;
import com.ai.cloudframe.provider.cmc.entity.ApiParam;
import com.ai.cloudframe.provider.cmc.service.IApiChildService;
import com.ai.cloudframe.provider.cmc.service.IApiParamService;
import com.ai.cloudframe.provider.cmc.service.IApiService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: shenbin
 * @CreateDate: [2019/7/17 20:18]
 * @Version: [v1.0]
 */
@RestController
@Api(description = "接口相关接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiQueryController extends BaseController implements ApiServiceApi {

  private static Logger logger = LoggerFactory.getLogger(ApiQueryController.class);

  @Autowired
  IApiService iApiService;

  @Autowired
  IApiParamService iApiParamService;

  @Autowired
  IApiChildService iApiChildService;

  @Override
  public BaseResponse<Map> apiQuery(@RequestBody Map map) {
    IPage<ApiAppDto> productList = iApiService.apiQuery(map);
    logger.debug("getElderMoveIntoInfo elderMoveIntoVoList : {}", productList);
    Map resultMap = new HashMap();
    resultMap.put("data", productList.getRecords());
    resultMap.put("total", productList.getTotal());
    resultMap.put("page", (null != map.get("page")) ? Long.valueOf(map.get("page").toString()) : null);
    resultMap.put("size", productList.getSize());
    return BaseResponse.success(resultMap);
  }

  @Override
  public BaseResponse<Map> apiAppQuery(Map map) {
    IPage<ApiAppDto> productList = iApiService.apiAppQuery(map);
    logger.debug("getElderMoveIntoInfo elderMoveIntoVoList : {}", productList);
    Map resultMap = new HashMap();
    resultMap.put("data", productList.getRecords());
    resultMap.put("total", productList.getTotal());
    resultMap.put("page", (null != map.get("page")) ? Long.valueOf(map.get("page").toString()) : null);
    resultMap.put("size", productList.getSize());
    return BaseResponse.success(resultMap);
  }

  @Override
  public BaseResponse<PageVo<ApiDto>> queryApiList(ApiListDto dto) {
    QueryWrapper<com.ai.cloudframe.provider.cmc.entity.Api> queryWrapper = new QueryWrapper<>();
    Timestamp now = new Timestamp(DateUtil.nowAbsMilliSeconds());
    queryWrapper .le("START_DATE",now)
        .gt("END_DATE",now)
        .orderByDesc("CREATE_TIME");
    if(StringUtils.isNotEmpty(dto.getAbilityLabel())){
      queryWrapper.like("ABILITY_LABEL",dto.getAbilityLabel());
    }
    if(StringUtils.isNotEmpty(dto.getAbilityName())){
      queryWrapper.like("API_NAME",dto.getAbilityName());
    }
    if(StringUtils.isNotEmpty(dto.getApiId())){
      queryWrapper.eq("API_ID",dto.getApiId());
    }
    if(StringUtils.isNotEmpty(dto.getApiStatus())){
      queryWrapper.like("API_STATUS",dto.getApiStatus());
    }
    if(StringUtils.isNotEmpty(dto.getApiUrl())){
      queryWrapper.like("API_URL",dto.getApiUrl());
    }
    IPage<com.ai.cloudframe.provider.cmc.entity.Api> page = new Page<>();
    page.setCurrent(dto.getIndex());
    page.setSize(dto.getPageSize());
    IPage<com.ai.cloudframe.provider.cmc.entity.Api> result = iApiService.page(page,queryWrapper);
    PageVo<ApiDto> vo = new PageVo();
    List<ApiDto> resultList = new ArrayList<>();
    vo.setCurrent(result.getCurrent());
    vo.setPages(result.getPages());
    vo.setSize(result.getSize());
    vo.setTotal(result.getTotal());
    result.getRecords().forEach(api -> {
      ApiDto apiDto = new ApiDto();
      apiDto.setAbilityDetail(api.getApiDetail());
      apiDto.setAbilityLabel(api.getAbilityLabel());
      apiDto.setAbilityName(api.getApiName());
      apiDto.setAccessAgree(api.getAccessAgree());
      apiDto.setAccessDataFormat(api.getAccessDataFormat());
      apiDto.setAccessUrl(api.getAccessUrl());
      apiDto.setApiStatus(api.getApiStatus());
      apiDto.setApiAgree(api.getApiAgree());
      apiDto.setApiId(api.getApiId());
      apiDto.setApiType(api.getApiType());
      apiDto.setApiUrl(api.getApiUrl());
      apiDto.setCreateUserId(api.getCreateUserId());
      apiDto.setDataFormat(api.getDataFormat());
      apiDto.setQuoteType(api.getQuoteStatus());
      apiDto.setRequestType(api.getRequestType());
      apiDto.setStartDate(DateUtil.getCurrentDateTime(api.getStartDate(),DateUtil.SEG_YYYYMMDD_HHMMSS));
      apiDto.setEndDate(DateUtil.getCurrentDateTime(api.getEndDate(),DateUtil.SEG_YYYYMMDD_HHMMSS));
      apiDto.setXmlHeader(api.getXmlHeader());
      apiDto.setCreateTime(DateUtil.getCurrentDateTime(api.getCreateTime(),DateUtil.SEG_YYYYMMDD_HHMMSS));
      resultList.add(apiDto);
    });
    vo.setData(resultList);
    return BaseResponse.success(vo);
  }

  @Override
  public BaseResponse<ApiDto> queryApiDetail(ApiDto dto) {
    QueryWrapper<com.ai.cloudframe.provider.cmc.entity.Api> apiQueryWrapper = new QueryWrapper<>();
    Timestamp now = new Timestamp(DateUtil.nowAbsMilliSeconds());
    apiQueryWrapper
        .eq("API_ID",dto.getApiId())
        .le("START_DATE",now)
        .gt("END_DATE",now)
        .orderByDesc("CREATE_TIME");
    com.ai.cloudframe.provider.cmc.entity.Api api = iApiService.getOne(apiQueryWrapper);
    if(api == null){
      return BaseResponse.fail(new ApiDto(),"该API_ID不存在对应记录");
    }
    QueryWrapper<ApiParam> apiParamQueryWrapper = new QueryWrapper<>();
    apiParamQueryWrapper
        .eq("API_ID",dto.getApiId())
        .le("START_DATE",now)
        .gt("END_DATE",now);
    List<ApiParam> params = iApiParamService.list(apiParamQueryWrapper);
    ApiDto result = new ApiDto();
    result.setApiId(api.getApiId());
    result.setApiAgree(api.getApiAgree());
    result.setRequestType(api.getRequestType());
    result.setApiType(api.getApiType());
    result.setApiStatus(api.getApiStatus());
    result.setEndDate(DateUtil.getCurrentDateTime(api.getEndDate(),DateUtil.SEG_YYYYMMDD_HHMMSS) );
    result.setXmlHeader(api.getXmlHeader());
    result.setStartDate(DateUtil.getCurrentDateTime(api.getStartDate(),DateUtil.SEG_YYYYMMDD_HHMMSS) );
    result.setQuoteType(api.getQuoteStatus());
    result.setDataFormat(api.getDataFormat());
    result.setCreateUserId(api.getCreateUserId());
    result.setApiUrl(api.getApiUrl());
    result.setAccessUrl(api.getAccessUrl());
    result.setAccessDataFormat(api.getAccessDataFormat());
    result.setAccessAgree(api.getAccessAgree());
    result.setAbilityLabel(api.getAbilityLabel());
    result.setAbilityDetail(api.getApiDetail());
    result.setAbilityName(api.getApiName());
    result.setCreateTime(DateUtil.getCurrentDateTime(api.getCreateTime(),DateUtil.SEG_YYYYMMDD_HHMMSS) );
    result.setParams(params.stream().map(apiParam -> {
      ApiParamDto apiParamDto = new ApiParamDto();
      apiParamDto.setApiId(apiParam.getApiId());
      apiParamDto.setCompletionType(apiParam.getCompletionType());
      apiParamDto.setDefaultValue(apiParam.getDefaultValue());
      apiParamDto.setEndDate(DateUtil.getCurrentDateTime(apiParam.getEndDate(),DateUtil.SEG_YYYYMMDD_HHMMSS) );
      apiParamDto.setHeadType(apiParam.getHeadType());
      apiParamDto.setIsEmpty(apiParam.getIsEmpty());
      apiParamDto.setIshide(apiParam.getIsHide());
      apiParamDto.setNameSpace(apiParam.getNameSpace());
      apiParamDto.setParamDetail(apiParam.getParamDetail());
      apiParamDto.setParamId(apiParam.getParamId());
      apiParamDto.setParamIndex(apiParam.getParamIndex());
      apiParamDto.setParamLength(apiParam.getParamLength());
      apiParamDto.setParamMode(apiParam.getParamMode());
      apiParamDto.setParamName(apiParam.getParamName());
      apiParamDto.setParamType(apiParam.getParamType());
      apiParamDto.setParentParamId(apiParam.getParentParamId());
      apiParamDto.setRegex(apiParam.getRegex());
      apiParamDto.setSeqId(apiParam.getSeqId());
      apiParamDto.setStartDate(DateUtil.getCurrentDateTime(apiParam.getStartDate(),DateUtil.SEG_YYYYMMDD_HHMMSS));
      apiParamDto.setXpath(apiParam.getXpath());
      return apiParamDto;
    }).collect(Collectors.toList()));

    return BaseResponse.success(result);
  }

  @Override
  @Transactional
  public BaseResponse<Map> saveApi(AbilityRegisterDto dto) {
    com.ai.cloudframe.provider.cmc.entity.Api api = new com.ai.cloudframe.provider.cmc.entity.Api();
    String apiId = UUIDUtil.getUUID();
    Timestamp now = new Timestamp(DateUtil.nowAbsMilliSeconds());
    ApiDto apiDto = dto.getAbilityForm();
    api.setAbilityLabel(apiDto.getAbilityLabel());
    api.setAccessAgree(apiDto.getAccessAgree());
    api.setAccessUrl(apiDto.getAccessUrl());
    api.setApiAgree(apiDto.getApiAgree());
    api.setApiId(apiId);
    api.setApiName(apiDto.getAbilityName());
    api.setApiStatus(StringUtils.isEmpty(apiDto.getApiStatus())?"0":apiDto.getApiStatus());
    api.setApiUrl(apiDto.getApiUrl());
    api.setCreateUserId(apiDto.getCreateUserId());
    api.setDataFormat(apiDto.getDataFormat());
    api.setAccessDataFormat(apiDto.getAccessDataFormat());
    api.setApiDetail(apiDto.getAbilityDetail());
    api.setEndDate(DateUtil.string2Date(apiDto.getEndDate(),DateUtil.SEG_YYYYMMDD));
    api.setQuoteStatus("0");
    api.setRequestType(apiDto.getRequestType());
    api.setStartDate(DateUtil.string2Date(apiDto.getStartDate(),DateUtil.SEG_YYYYMMDD));
    api.setXmlHeader(apiDto.getXmlHeader());
    api.setApiType(apiDto.getApiType());
    api.setCreateTime(now);
    iApiService.save(api);

    String childApiIds = apiDto.getChildApi();
    if(GlobalConstant.API_TYPE.COMBINATION.equals(api.getApiType()) && StringUtils.isNotEmpty(childApiIds)){
      String[] childIds = childApiIds.split(",");
      List<ApiChild> apiChildList = new ArrayList<>();

      for (int i = 0; i < childIds.length; i++) {
        ApiChild apiChild = new ApiChild();
        apiChild.setApiId(api.getApiId());
        apiChild.setCallSeq(String.valueOf(i));
        apiChild.setChildApiId(childIds[i]);
        apiChild.setStartDate(now);
        apiChild.setEndDate(new Timestamp(DateUtil.string2Date(GlobalConstant.END_DATE,DateUtil.SEG_YYYYMMDD).getTime()));
        apiChildList.add(apiChild);
      }
      iApiChildService.saveBatch(apiChildList);
    }

    List<ApiParamDto> apiParamDtoList = dto.getParams();
    List<ApiParam> apiParamList = new ArrayList<>();
    apiParamDtoList.forEach(apiParamDto -> {
      ApiParam apiParam = new ApiParam();
      apiParam.setApiId(apiId);
      apiParam.setCompletionType(apiParamDto.getCompletionType());
      apiParam.setDefaultValue(apiParamDto.getDefaultValue());
      apiParam.setEndDate(new Timestamp(DateUtil.string2Date(GlobalConstant.END_DATE,DateUtil.SEG_YYYYMMDD).getTime()));
      apiParam.setHeadType(apiParamDto.getHeadType());
      apiParam.setIsEmpty(apiParamDto.getIsEmpty());
      apiParam.setNameSpace(apiParamDto.getNameSpace());
      apiParam.setParamDetail(apiParamDto.getParamDetail());
      apiParam.setParamId(apiParamDto.getParamId());
      apiParam.setParamIndex(apiParamDto.getParamIndex());
      apiParam.setParamLength(apiParamDto.getParamLength());
      apiParam.setParamMode(apiParamDto.getParamMode());
      apiParam.setParamName(apiParamDto.getParamName());
      apiParam.setParamType(apiParamDto.getParamType());
      apiParam.setParentParamId(StringUtils.isEmpty(apiParamDto.getParentParamId())?"-1": apiParamDto.getParentParamId());
      apiParam.setRegex(apiParamDto.getRegex());
      apiParam.setSeqId(apiParamDto.getSeqId());
      apiParam.setStartDate(now);
      apiParam.setXpath(apiParamDto.getXpath());
      apiParam.setIsHide(apiParamDto.getIshide());
      apiParamList.add(apiParam);
    });
    iApiParamService.saveBatch(apiParamList);

    return BaseResponse.success();
  }

  @Override
  @Transactional
  public BaseResponse<Map> apiModify(AbilityRegisterDto dto) {
    com.ai.cloudframe.provider.cmc.entity.Api api = new com.ai.cloudframe.provider.cmc.entity.Api();
    UpdateWrapper<com.ai.cloudframe.provider.cmc.entity.Api> apiUpdateWrapper = new UpdateWrapper<>();
    apiUpdateWrapper.eq("API_ID",dto.getAbilityForm().getApiId());
    Timestamp now = new Timestamp(DateUtil.nowAbsMilliSeconds());
    ApiDto apiDto = dto.getAbilityForm();
    if(apiDto != null && StringUtils.isNotEmpty(apiDto.getApiId())) {
      if(GlobalConstant.MODIFY_TAG.DELETE.equals(apiDto.getFlag())){
        //删除
        api.setApiId(apiDto.getApiId());
        api.setEndDate(now);
        iApiService.updateApi(api);
        ApiParam apiParam = new ApiParam();
        apiParam.setApiId(apiDto.getApiId());
        apiParam.setEndDate(now);
        iApiParamService.updateApiParam(apiParam);
        return BaseResponse.success();
      }else if(GlobalConstant.MODIFY_TAG.UPDATE.equals(apiDto.getFlag())){
        api.setAbilityLabel(apiDto.getAbilityLabel());
        api.setAccessAgree(apiDto.getAccessAgree());
        api.setAccessUrl(apiDto.getAccessUrl());
        api.setApiAgree(apiDto.getApiAgree());
        api.setApiId(apiDto.getApiId());
        api.setApiName(apiDto.getAbilityName());
        api.setApiStatus(apiDto.getApiStatus());
        api.setApiUrl(apiDto.getApiUrl());
//      api.setCreateUserId(apiDto.getCreateUserId());
        api.setDataFormat(apiDto.getDataFormat());
        api.setEndDate(new Date(DateUtil.string2Date(apiDto.getEndDate(),DateUtil.SEG_YYYYMMDD).getTime()) );
        api.setQuoteStatus(apiDto.getQuoteType());
        api.setRequestType(apiDto.getRequestType());
        api.setStartDate(new Date(DateUtil.string2Date(apiDto.getStartDate(),DateUtil.SEG_YYYYMMDD).getTime()));
        api.setXmlHeader(apiDto.getXmlHeader());
        api.setApiType(apiDto.getApiType());
//      api.setCreateTime(now);
        iApiService.updateApi(api);
      }

    }
    List<ApiParamDto> paramDtos = dto.getParams();
    List<ApiParam> apiParamList = new ArrayList<>();
    if(paramDtos != null && paramDtos.size()>0){
      paramDtos.forEach(apiParamDto -> {
        if(GlobalConstant.MODIFY_TAG.INSERT.equals(apiParamDto.getFlag())){
          ApiParam apiParam = new ApiParam();
          apiParam.setApiId(apiDto.getApiId());
          apiParam.setCompletionType(apiParamDto.getCompletionType());
          apiParam.setDefaultValue(apiParamDto.getDefaultValue());
          apiParam.setEndDate(new Timestamp(DateUtil.string2Date(GlobalConstant.END_DATE,DateUtil.SEG_YYYYMMDD).getTime()));
          apiParam.setHeadType(apiParamDto.getHeadType());
          apiParam.setIsEmpty(apiParamDto.getIsEmpty());
          apiParam.setNameSpace(apiParamDto.getNameSpace());
          apiParam.setParamDetail(apiParamDto.getParamDetail());
          apiParam.setParamId(apiParamDto.getParamId());
          apiParam.setParamIndex(apiParamDto.getParamIndex());
          apiParam.setParamLength(apiParamDto.getParamLength());
          apiParam.setParamMode(apiParamDto.getParamMode());
          apiParam.setParamName(apiParamDto.getParamName());
          apiParam.setParamType(apiParamDto.getParamType());
          apiParam.setParentParamId(StringUtils.isEmpty(apiParamDto.getParentParamId())?"-1": apiParamDto.getParentParamId());
          apiParam.setRegex(apiParamDto.getRegex());
          apiParam.setSeqId(apiParamDto.getSeqId());
          apiParam.setStartDate(now);
          apiParam.setXpath(apiParamDto.getXpath());
          apiParam.setIsHide(apiParamDto.getIshide());
          apiParamList.add(apiParam);
        }else if(GlobalConstant.MODIFY_TAG.DELETE.equals(apiParamDto.getFlag())){
          ApiParam apiParam = new ApiParam();
          apiParam.setParamId(apiParamDto.getParamId());
          apiParam.setEndDate(now);
          iApiParamService.updateApiParam(apiParam);
        }else if(GlobalConstant.MODIFY_TAG.UPDATE.equals(apiParamDto.getFlag())){
          ApiParam apiParam = new ApiParam();
//          apiParam.setApiId(apiDto.getApiId());
          apiParam.setCompletionType(apiParamDto.getCompletionType());
          apiParam.setDefaultValue(apiParamDto.getDefaultValue());
//          if(StringUtils.isNotEmpty(apiDto.getStartDate())){
//            apiParam.setStartDate(new Timestamp(DateUtil.string2Date(apiDto.getStartDate(),DateUtil.SEG_YYYYMMDD).getTime()));
//          }
//          if(StringUtils.isNotEmpty(apiDto.getEndDate())){
//            apiParam.setEndDate(new Timestamp(DateUtil.string2Date(apiDto.getEndDate(),DateUtil.SEG_YYYYMMDD).getTime()));
//          }
          apiParam.setHeadType(apiParamDto.getHeadType());
          apiParam.setIsEmpty(apiParamDto.getIsEmpty());
          apiParam.setNameSpace(apiParamDto.getNameSpace());
          apiParam.setParamDetail(apiParamDto.getParamDetail());
          apiParam.setParamId(apiParamDto.getParamId());
          apiParam.setParamIndex(apiParamDto.getParamIndex());
          apiParam.setParamLength(apiParamDto.getParamLength());
          apiParam.setParamMode(apiParamDto.getParamMode());
          apiParam.setParamName(apiParamDto.getParamName());
          apiParam.setParamType(apiParamDto.getParamType());
          apiParam.setParentParamId(StringUtils.isEmpty(apiParamDto.getParentParamId())?"-1": apiParamDto.getParentParamId());
          apiParam.setRegex(apiParamDto.getRegex());
          apiParam.setSeqId(apiParamDto.getSeqId());
          apiParam.setXpath(apiParamDto.getXpath());
          apiParam.setIsHide(apiParamDto.getIshide());
          iApiParamService.updateApiParam(apiParam);
        }
      });
    }

    if(apiParamList.size()>0){
      iApiParamService.saveBatch(apiParamList);
    }
    return BaseResponse.success();
  }

}
