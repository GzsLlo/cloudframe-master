/**
 * Copyright 2019 asiainfo Inc.
 **/

package com.ai.cloudframe.web.controller;

import com.ai.cloudframe.common.base.constant.GlobalConstant;
import com.ai.cloudframe.common.base.enums.StatusEnum;
import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.common.base.support.BaseController;
import com.ai.cloudframe.common.base.util.XmlUtil;
import com.ai.cloudframe.provider.api.sys.model.dto.ApiDto;
import com.ai.cloudframe.provider.api.sys.model.dto.ApiListDto;
import com.ai.cloudframe.provider.api.sys.model.dto.ApiParamDto;
import com.ai.cloudframe.provider.api.sys.model.dto.ApiStatisticsDto;
import com.ai.cloudframe.provider.api.sys.model.dto.CallApiDto;
import com.ai.cloudframe.provider.api.sys.model.dto.ParamDto;
import com.ai.cloudframe.provider.api.sys.model.vo.PageVo;
import com.ai.cloudframe.provider.api.sys.service.ApiServiceApi;
import com.ai.cloudframe.provider.api.sys.service.ApiStatisticsServiceApi;
import com.ai.cloudframe.provider.api.sys.service.CallApiServiceApi;
import com.ai.cloudframe.provider.api.sys.service.ParamServiceApi;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author: shenbin
 * @CreateDate: [2019/7/17 19:27]
 * @Version: [v1.0]
 */
@RestController
@Api(description = "接口调用接口")
@CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = "*")
public class APIController extends BaseController {
  private static Logger logger = LoggerFactory.getLogger(APIController.class);

  @Autowired
  private CallApiServiceApi callApiServiceApi;

  @Autowired
  private ApiStatisticsServiceApi apiStatisticsServiceApi;

  @Autowired
  private ApiServiceApi apiServiceApi;

  @Autowired
  private ParamServiceApi paramServiceApi;

  @RequestMapping(value = "/apiCall/*", method = {RequestMethod.POST,RequestMethod.GET},produces = "application/x-www-form-urlencoded; charset=UTF-8")
  @ApiOperation("调用API")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "vueJson", value = "提交报文", required = true, dataType = "form")
  })
  public String callApi(@RequestBody String in, @RequestHeader HttpHeaders headers, HttpServletRequest request) {
    String url = request.getRequestURI();
    logger.info("api url:{}",url);
    String label = url.substring(url.indexOf("/apiCall/")+9);
    logger.info("api label:{}",label);
    logger.info("input:{}",in);
    logger.info("requestMethod:{}",request.getMethod());
    CallApiDto dto = new CallApiDto();
    dto.setUrl(label);
    dto.setInputString(in);
    dto.setRequestMethod(request.getMethod());
    BaseResponse<Map> response = callApiServiceApi.callApi(dto);
    logger.info("request charset:{}",request.getCharacterEncoding());
    logger.info("deal result code:{},message:{},data:{}",response.getCode(),response.getMessage(),response.getData());
//    httpServletResponse.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
//    httpServletResponse.setCharacterEncoding("UTF-8");
    String result;
    Map<String,Object> responseContext = (Map<String, Object>) response.getData().get("context");
    if(responseContext != null && "200000".equals(responseContext.get("userId"))){
      ApiStatisticsDto apiStatisticsDto = new ApiStatisticsDto();
      apiStatisticsDto.setApiId((String) responseContext.get("apiId"));
      apiStatisticsDto.setCallEndTime(new Timestamp(new Date().getTime()));
      apiStatisticsDto.setCallStartTime(new Timestamp((Long) responseContext.get("startTime")));
      apiStatisticsDto.setChannelId( (String)responseContext.get("channelId"));
      apiStatisticsDto.setToken((String)responseContext.get("token"));
//      apiStatisticsDto.setProductId( (String) responseContext.get("productId"));
//      apiStatisticsDto.setGoodId((String) responseContext.get("goodId"));
      apiStatisticsDto.setUserId((String) responseContext.get("userId"));
      apiStatisticsDto.setUseCount(1);
      apiStatisticsDto.setResultCode(response.getCode());
      apiStatisticsDto.setResultMessage(response.getMessage());
      apiStatisticsServiceApi.insertStatistics(apiStatisticsDto);
    }
    if(StatusEnum.SUCCESS.getCode().equals(response.getCode())){
      result = (String)  response.getData().get("responseStr");
    }else {
      String dataFormat = response.getData().get("dataFormat")==null?GlobalConstant.API_DATA_FORMAT.JSON: (String) response.getData().get("dataFormat");

      Map<String,Object> resultMap = new HashMap<>();
      resultMap.put("resultCode","4000");
      resultMap.put("message", response.getMessage());
      if(GlobalConstant.API_DATA_FORMAT.JSON.equals(dataFormat)){
        result = JSON.toJSONString(resultMap);
      }else {
        try {
          result = XmlUtil.map2xml(resultMap,"root").asXML();
        } catch (Exception e) {
          e.printStackTrace();
          result = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
              "<root>\n" +
              "    <resultCode>4000</resultCode>\n" +
              "    <message>parse error result to xml error!</message>\n" +
              "</root>";
        }
      }
    }

    logger.info("api A end result:{}.",result);

    return result;
  }

  @RequestMapping(value = "/ability/abilityDetail", method = {RequestMethod.POST})
  @ApiOperation("能力详情")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "vueJson", value = "提交报文", required = true, paramType = "form")
  })
  public BaseResponse abilityDetail(@RequestParam String ApiId) {
    logger.info("input json:{}",ApiId);
    ApiDto apiDto = new ApiDto();
    apiDto.setApiId(ApiId);
    BaseResponse<ApiDto> response = apiServiceApi.queryApiDetail(apiDto);
    ApiDto result = response.getData();
    result.setApiAgreeName(StringUtils.isEmpty(result.getApiAgree())?"":paramServiceApi.codeToName("api_agree",result.getApiAgree()).getData());
    result.setRequestTypeName(StringUtils.isEmpty(result.getRequestType())?"":paramServiceApi.codeToName("request_type",result.getRequestType()).getData());
    result.setApiTypeName(StringUtils.isEmpty(result.getApiType())?"":paramServiceApi.codeToName("api_type",result.getApiType()).getData());
    result.setApiStatusName(StringUtils.isEmpty(result.getApiStatus())?"":paramServiceApi.codeToName("api_status",result.getApiStatus()).getData());
    result.setAccessAgreeName(StringUtils.isEmpty(result.getAccessAgree())?"":paramServiceApi.codeToName("api_agree",result.getAccessAgree()).getData());
    result.setAccessDataFormatName(StringUtils.isEmpty(result.getAccessDataFormat())?"":paramServiceApi.codeToName("api_data_format",result.getAccessDataFormat()).getData());
    result.setDataFormatName(StringUtils.isEmpty(result.getDataFormat())?"":paramServiceApi.codeToName("api_data_format",result.getDataFormat()).getData());

    if(result.getParams()!= null){
      result.getParams().forEach(resultDto ->{
        resultDto.setParamModeName(StringUtils.isEmpty(resultDto.getParamMode())?"":paramServiceApi.codeToName("api_param_mode",resultDto.getParamMode()).getData());
        resultDto.setParamTypeName(StringUtils.isEmpty(resultDto.getParamType())?"":paramServiceApi.codeToName("api_param_type",resultDto.getParamType()).getData());
        resultDto.setIsEmptyName(StringUtils.isEmpty(resultDto.getIsEmpty())?"":paramServiceApi.codeToName("yes_or_no",resultDto.getIsEmpty()).getData());
        resultDto.setIshideName(StringUtils.isEmpty(resultDto.getIshide())?"":paramServiceApi.codeToName("yes_or_no",resultDto.getIshide()).getData());
        if("-1".equals(resultDto.getParentParamId()) || StringUtils.isEmpty(resultDto.getParentParamId())){
          resultDto.setParentParamName("");
        }else {
          Optional<ApiParamDto> optionalParamDto =  result.getParams().stream().filter(row -> resultDto.getParentParamId().equals(row.getParamId())).findAny();
          if(optionalParamDto.isPresent()){
            resultDto.setParentParamName(optionalParamDto.get().getParamName());
          }else {
            resultDto.setParentParamName("");
          }
        }
      });
      result.setRequestParams( result.getParams().stream().filter(row -> GlobalConstant.API_RARAM_MODE.REQUEST.equals(row.getParamMode())).collect(Collectors.toList()));
      result.setResponseParams( result.getParams().stream().filter(row -> GlobalConstant.API_RARAM_MODE.RESPONSE.equals(row.getParamMode())).collect(Collectors.toList()));


    }

    return response;
  }

  @RequestMapping(
      value = "/ability/abilityList",
      method = RequestMethod.POST
//      , produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
//      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
  )
  @ApiOperation("能力列表")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "vueJson", value = "提交报文", required = true, paramType = "form")
  })
  public BaseResponse abilityList(@RequestBody String input) {
    logger.info("input json:{}",input);
    ApiListDto inputMap = JSON.parseObject(input,ApiListDto.class);
    BaseResponse<PageVo<ApiDto>> response = apiServiceApi.queryApiList(inputMap);

    PageVo<ApiDto> apiDtos = response.getData();
    apiDtos.getData().forEach(apiDto ->{
      apiDto.setApiStatusName(StringUtils.isEmpty(apiDto.getApiStatus())?"":paramServiceApi.codeToName("api_status",apiDto.getApiStatus()).getData());
      apiDto.setApiTypeName(StringUtils.isEmpty(apiDto.getApiType())?"":paramServiceApi.codeToName("api_type",apiDto.getApiType()).getData());
      apiDto.setRequestTypeName(StringUtils.isEmpty(apiDto.getRequestType())?"":paramServiceApi.codeToName("request_type",apiDto.getRequestType()).getData());
      apiDto.setApiAgreeName(StringUtils.isEmpty(apiDto.getApiAgree())?"":paramServiceApi.codeToName("api_agree",apiDto.getApiAgree()).getData());
      apiDto.setAccessDataFormatName(StringUtils.isEmpty(apiDto.getAccessAgree())?"":paramServiceApi.codeToName("api_agree",apiDto.getAccessAgree()).getData());
      apiDto.setAccessDataFormatName(StringUtils.isEmpty(apiDto.getAccessDataFormat())?"":paramServiceApi.codeToName("api_data_format",apiDto.getAccessDataFormat()).getData());
      apiDto.setDataFormatName(StringUtils.isEmpty(apiDto.getDataFormat())?"":paramServiceApi.codeToName("api_data_format",apiDto.getDataFormat()).getData());
    });

    return response;
  }
}
