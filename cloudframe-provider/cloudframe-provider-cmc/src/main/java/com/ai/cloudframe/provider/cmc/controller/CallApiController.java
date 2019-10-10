/**
 * Copyright 2019 asiainfo Inc.
 **/

package com.ai.cloudframe.provider.cmc.controller;

import com.ai.cloudframe.common.base.constant.GlobalConstant;
import com.ai.cloudframe.common.base.enums.StatusEnum;
import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.common.base.support.BaseController;
import com.ai.cloudframe.common.base.util.CollectionUtil;
import com.ai.cloudframe.common.base.util.DateUtil;
import com.ai.cloudframe.common.base.util.InterfaceUtil;
import com.ai.cloudframe.common.base.util.StringUtil;
import com.ai.cloudframe.common.base.util.XmlUtil;
import com.ai.cloudframe.provider.api.sys.model.dto.CallApiDto;
import com.ai.cloudframe.provider.api.sys.service.CallApiServiceApi;
import com.ai.cloudframe.provider.cmc.entity.ApiChild;
import com.ai.cloudframe.provider.cmc.entity.ApiFlowControl;
import com.ai.cloudframe.provider.cmc.entity.ApiParam;
import com.ai.cloudframe.provider.cmc.entity.ApiToken;
import com.ai.cloudframe.provider.cmc.entity.Channel;
import com.ai.cloudframe.provider.cmc.service.IApiChildService;
import com.ai.cloudframe.provider.cmc.service.IApiFlowControlService;
import com.ai.cloudframe.provider.cmc.service.IApiParamService;
import com.ai.cloudframe.provider.cmc.service.IApiService;
import com.ai.cloudframe.provider.cmc.service.IApiTokenService;
import com.ai.cloudframe.provider.cmc.service.IChannelService;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

/**
 * @Author: shenbin
 * @CreateDate: [2019/7/17 20:18]
 * @Version: [v1.0]
 */
@RestController
@Api(description = "接口相关接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CallApiController extends BaseController implements CallApiServiceApi {
  private static Map<String,List<Long>> callRecordsMap = new HashMap<>();

  private static Logger logger = LoggerFactory.getLogger(CallApiController.class);
  @Autowired
  IApiService iApiService;

  @Autowired
  IApiTokenService iApiTokenService;

  @Autowired
  IApiParamService iApiParamService;

  @Autowired
  IApiChildService iApiChildService;

  @Autowired
  IApiFlowControlService iApiFlowControlService;

  @Autowired
  IChannelService iChannelService;

  @Override
  public BaseResponse<Map> callApi(CallApiDto param) {
    Timestamp now = new Timestamp(new Date().getTime());
    QueryWrapper<com.ai.cloudframe.provider.cmc.entity.Api> apiQueryWrapper = new QueryWrapper<>();
    apiQueryWrapper.eq("ABILITY_LABEL",param.getUrl())
        .le("START_DATE",now)
        .gt("END_DATE",now);
    List<com.ai.cloudframe.provider.cmc.entity.Api> apis = iApiService.list(apiQueryWrapper);
    Map result = new HashMap();
    if(apis == null|| apis.size()==0){
      logger.error("There's no Api corresponding to this url! url:{}",param.getUrl());
      return BaseResponse.fail(result,"There's no Api corresponding to this url");
    }

    if( apis.size()>1){
      logger.error("The url corresponding to multiple Api! url:{}",param.getUrl());
      return BaseResponse.fail(result,"The url corresponding to multiple Api");
    }

    com.ai.cloudframe.provider.cmc.entity.Api api = apis.get(0);
    //todo 如果为原子接口直接调用。可能不需要
    result.put("dataFormat",api.getDataFormat());
    if(!GlobalConstant.API_STATUS.ACTIVE.equals(api.getApiStatus())){
      logger.error("api is not active ! url:{}",param.getUrl());
      return BaseResponse.fail(result,"Api is not active ");
    }

  //渠道token 校验
    //tokenInfo 包括token，channelId，GOOD_ID，APPLICATION_ID，USER_ID，PRODUCT_ID
    Map tokenInfo = getToken(param.getInputString(),api.getDataFormat());
    if(tokenInfo == null ){
      logger.error("can not get token info!");
      return BaseResponse.fail(result,"Parse token info error!");
    }

    if(!tokenInfo.containsKey("channelId")){
      logger.error("can not get channelId!");
      return BaseResponse.fail(result,"Can not get channelId!");
    }

    if(!tokenInfo.containsKey("token")){
      logger.error("can not get token info!");
      return BaseResponse.fail(result,"Can not get token !");
    }

    if(!tokenInfo.containsKey("userId")){
      logger.error("can not get openId info!");
      return BaseResponse.fail(result,"Can not get openId !");
    }




    QueryWrapper<Channel> channelQueryWrapper = new QueryWrapper<>();
    channelQueryWrapper
        .eq("CHANNEL_ID",tokenInfo.get("channelId"))
        .le("START_DATE",now)
        .gt("END_DATE",now);

    List<Channel> channels = iChannelService.list(channelQueryWrapper);
    if(channels==null ||channels.size()==0){
      logger.error("!");
      return BaseResponse.fail(result,"Channel not registered yet !");
    }

    QueryWrapper<ApiToken> apiTokenQueryWrapper = new QueryWrapper<>();
    apiTokenQueryWrapper.eq("API_ID",api.getApiId())
//        .eq("API_STATUS","1")
        .eq("CHANNEL_ID",tokenInfo.get("channelId"))
        .eq("TOKEN",tokenInfo.get("token"))
        .le("START_DATE",now)
        .gt("END_DATE",now);

    List<ApiToken> apiTokens = iApiTokenService.list(apiTokenQueryWrapper);
    if(apiTokens==null ||apiTokens.size()==0){
      logger.error("token validation fails!");
      return BaseResponse.fail(result,"Token validate failed!");
    }

    //流控
    if(!judgeFlow(api.getApiId(),tokenInfo.get("channelId").toString())){
      return BaseResponse.fail(result,"Too many Api calls!");
    }

    tokenInfo.put("startTime",DateUtil.nowAbsMilliSeconds());
    tokenInfo.put("apiId",api.getApiId());
    result.put("context",tokenInfo);

    BaseResponse<Map> paramsResponse = getParams(api,GlobalConstant.API_RARAM_MODE.REQUEST);
    if(!StatusEnum.SUCCESS.getCode().equals(paramsResponse.getCode())){
      logger.error("get api request params error.apiID:{}",api.getApiId());
      return  BaseResponse.fail(result,paramsResponse.getMessage());
    }
    Map<ApiParam,TreeMap> apiParams = paramsResponse.getData();
    BaseResponse<Map> parseResponse = null;

    if(GlobalConstant.API_DATA_FORMAT.JSON.equals(api.getDataFormat())){
      Map<String,Object> jsonInput = new HashMap<>();
      Map jsonMap = JSON.parseObject(param.getInputString(),Map.class);
      for(Map.Entry<ApiParam,TreeMap> entry:apiParams.entrySet()) {
        parseResponse = getRequiredParams(entry.getKey(),entry.getValue(),jsonMap,jsonInput);
        if(!StatusEnum.SUCCESS.getCode().equals(parseResponse.getCode())){
          logger.error("json parse error.json context:{}",api.getApiId());
          return  BaseResponse.fail(result,parseResponse.getMessage());
        }
      }
    }else if(GlobalConstant.API_DATA_FORMAT.XML.equals(api.getDataFormat())){
      parseResponse = parseXML2Map(param.getInputString(),apiParams);
      if(!StatusEnum.SUCCESS.getCode().equals(parseResponse.getCode())){
        logger.error("json parse error.json context:{}",api.getApiId());
        return  BaseResponse.fail(result,parseResponse.getMessage());
      }
    }

    //解析后入参
    Map<String,Object> inputMap = (Map<String, Object>) parseResponse.getData();

    BaseResponse<String> callResponse = callApis(inputMap,api);
    if(!StatusEnum.SUCCESS.getCode().equals(callResponse.getCode())){
      logger.error("call child api failed.apiId:{}",api.getApiId());
      return  BaseResponse.fail(result,callResponse.getMessage());
    }
    result.put("responseStr",callResponse.getData());


    return BaseResponse.success(result);
  }

  private BaseResponse<String> callApis(Map<String,Object> inputMap,com.ai.cloudframe.provider.cmc.entity.Api fatherApi){
    Map<String,Object> allParamMap = inputMap;
    Timestamp now = new Timestamp(new Date().getTime());
    QueryWrapper<ApiChild> apiChildQueryWrapper = new QueryWrapper<>();
    apiChildQueryWrapper.eq("API_ID",fatherApi.getApiId())
        .le("START_DATE",now)
        .gt("END_DATE",now)
        .orderByAsc("CALL_SEQ");
    List<ApiChild> apiChildren = iApiChildService.list(apiChildQueryWrapper);
    if(apiChildren == null || apiChildren.size()==0){
      logger.error("api has no child api!");
      return BaseResponse.fail("","Composite interface has no effective child!");
    }

    for (ApiChild apiChild:apiChildren) {
      QueryWrapper<com.ai.cloudframe.provider.cmc.entity.Api> apiQueryWrapper = new QueryWrapper<>();
      apiQueryWrapper.eq("API_ID",apiChild.getChildApiId());
//          .eq("API_STATUS","1");

      com.ai.cloudframe.provider.cmc.entity.Api childApi = iApiService.getOne(apiQueryWrapper);
      if(childApi == null){
        logger.error("childApi have none detail api records! apiId:{}",apiChild.getApiId());
        return BaseResponse.fail("","child api have none detail api records!");
      }

      if(!GlobalConstant.API_STATUS.ACTIVE.equals(childApi.getApiStatus())){
        logger.error("child api is not active ! apiId:{}",childApi.getApiId());
        return BaseResponse.fail("","Child api is not active.apiId :"+childApi.getApiId());
      }

      BaseResponse<Map> paramsResponse = getParams(childApi,GlobalConstant.API_RARAM_MODE.REQUEST);
      if(!StatusEnum.SUCCESS.getCode().equals(paramsResponse.getCode())){
        logger.error("get api request params error.apiID:{}",childApi.getApiId());
        return  BaseResponse.fail("",paramsResponse.getMessage());
      }
      Map<ApiParam,TreeMap> apiParams = paramsResponse.getData();
      //子接口需要入参
      Map<ApiParam,TreeMap> apiParamsWithoutRoot = apiParams;

      Map<String,Object> currParamMap = new HashMap<>();
      //xml 取根节点的子节点
      if(GlobalConstant.API_DATA_FORMAT.XML.equals(childApi.getDataFormat()) ){
        Optional<Map.Entry<ApiParam,TreeMap>> root = apiParams.entrySet().stream().findFirst();
        if(root.isPresent()){
          apiParamsWithoutRoot = root.get().getValue();
        }
      }
//从总入参中取出需要参数（所有入参会进行合并）
      for(Map.Entry<ApiParam,TreeMap> entry:apiParamsWithoutRoot.entrySet()) {
        BaseResponse<Map> parseResponse = getRequiredParams(entry.getKey(),entry.getValue(),allParamMap,currParamMap);
        if(!StatusEnum.SUCCESS.getCode().equals(parseResponse.getCode())){
          logger.error("json parse error.json context:{}",entry.getKey().getApiId());
          return  BaseResponse.fail("",parseResponse.getMessage());
        }
      }


      String requestString;
      try {
        requestString = parseMap2String(currParamMap,childApi.getDataFormat(),childApi.getXmlHeader(), apiParams);
      } catch (Exception e) {
        logger.error("Data parse string error.data Type:{},data:{}",childApi.getDataFormat(),currParamMap);
        e.printStackTrace();
        return BaseResponse.fail("","Data parse string error");
      }

      String resultString = null;
      try {
        resultString = InterfaceUtil.doPost(childApi.getAccessUrl(),requestString,childApi.getDataFormat(),childApi.getRequestType());
      } catch (Exception e) {
        logger.error("Api call error,ApiId:{},url:{},requestMessage:{}",childApi.getApiId(),childApi.getAccessUrl(),requestString);
        e.printStackTrace();
        return BaseResponse.fail("","Child Api call error");
      }
      BaseResponse<Map> paramsOutResponse = getParams(childApi,GlobalConstant.API_RARAM_MODE.RESPONSE);
      if(!StatusEnum.SUCCESS.getCode().equals(paramsOutResponse.getCode())){
        logger.error("get api request params error.apiID:{}",childApi.getApiId());
        return  BaseResponse.fail("",paramsOutResponse.getMessage());
      }
      Map<ApiParam,TreeMap> apiOutParams = paramsOutResponse.getData();
      BaseResponse<Map> apiOutParamResponse = parseString2Map(resultString,apiOutParams,childApi.getDataFormat());
      if(!StatusEnum.SUCCESS.getCode().equals(apiOutParamResponse.getCode())){
        logger.error("get api request params error.apiID:{}",childApi.getApiId());
        return  BaseResponse.fail("",apiOutParamResponse.getMessage());
      }
      Map<String,Object> apiOutMap = apiOutParamResponse.getData();

      allParamMap = CollectionUtil.mapConbine(apiOutMap,allParamMap,false);
      //list 不合并，以后获得的为准

    }//子api调用结束

    BaseResponse<Map> paramsOutResponse = getParams(fatherApi,GlobalConstant.API_RARAM_MODE.RESPONSE);
    if(!StatusEnum.SUCCESS.getCode().equals(paramsOutResponse.getCode())){
      logger.error("get api request params error.apiID:{}",fatherApi.getApiId());
      return  BaseResponse.fail("",paramsOutResponse.getMessage());
    }

    Map<ApiParam,TreeMap> apiOutParams = paramsOutResponse.getData();
    Map<String,Object> resultMap = new HashMap<>();

    Map<ApiParam,TreeMap> apiParamsWithoutRoot = apiOutParams;

    //xml 取根节点的所有子节点
    if(GlobalConstant.API_DATA_FORMAT.XML.equals(fatherApi.getDataFormat()) ){
      Optional<Map.Entry<ApiParam,TreeMap>> root = apiOutParams.entrySet().stream().findFirst();
      if(root.isPresent()){
        apiParamsWithoutRoot = root.get().getValue();
      }
    }

    for(Map.Entry<ApiParam,TreeMap> entry:apiParamsWithoutRoot.entrySet()) {
      paramsOutResponse = getRequiredParams(entry.getKey(),entry.getValue(),allParamMap,resultMap);
      if(!StatusEnum.SUCCESS.getCode().equals(paramsOutResponse.getCode())){
        logger.error("json parse error.json context:{}",fatherApi.getApiId());
        return  BaseResponse.fail("",paramsOutResponse.getMessage());
      }
    }
    resultMap.put("resultCode","0000");
    //    Map<String,Object> resultMap = parseResponse.getData();
    String responseStr = "";
    try {
      responseStr = parseMap2String(resultMap,fatherApi.getDataFormat(),fatherApi.getXmlHeader(), apiOutParams);
    } catch (Exception e) {
      logger.error("parse response data error.apiId:{}",fatherApi.getApiId());
      e.printStackTrace();
      return BaseResponse.fail("","parse map to String error");
    }
    return BaseResponse.success(responseStr);
  }

  private BaseResponse<Map> getParams(com.ai.cloudframe.provider.cmc.entity.Api api,String paramMode){
    Timestamp now = new Timestamp(new Date().getTime());
    QueryWrapper<ApiParam> apiParamQueryWrapper = new QueryWrapper<>();
    apiParamQueryWrapper.eq("API_ID",api.getApiId())
        .eq("PARAM_MODE",paramMode)
        .le("START_DATE",now)
        .gt("END_DATE",now)
        .orderByAsc("SEQ_ID");
    List<ApiParam> apiParamList = iApiParamService.list(apiParamQueryWrapper);
    Map<ApiParam,TreeMap> apiParams = new TreeMap<>();
    for (int i=0;i<apiParamList.size();i++){
      ApiParam apiParam = apiParamList.get(i);
      if(StringUtils.isEmpty(apiParam.getParentParamId())||"-1".equals(apiParam.getParentParamId())){
        if(apiParams.containsKey(apiParam)){
          logger.error("duplicate column！");
          return BaseResponse.fail(apiParams,"api params error! duplicate param name.");
        }

        switch (apiParam.getParamType()) {
          case GlobalConstant.API_PARAM_TYPE.MAP:
          case GlobalConstant.API_PARAM_TYPE.LIST:
            apiParams.put(apiParam,new TreeMap<ApiParam,TreeMap>());
            break;
          case GlobalConstant.API_PARAM_TYPE.STRING:
          case GlobalConstant.API_PARAM_TYPE.INT:
          case GlobalConstant.API_PARAM_TYPE.DOUBLE:
          case GlobalConstant.API_PARAM_TYPE.DATE:
            apiParams.put(apiParam,null);
            break;
        }
        apiParamList.remove(i);
        i--;
      }
    }
    apiParams.entrySet().forEach(row ->{
      if(GlobalConstant.API_PARAM_TYPE.MAP.equals(row.getKey().getParamType())||
          GlobalConstant.API_PARAM_TYPE.LIST.equals(row.getKey().getParamType())
          ){
        List<ApiParam> children = findChildren(apiParamList, row.getKey());
        children.forEach(child -> analysis(apiParamList,child,row.getValue()));
      }
    });



    return BaseResponse.success(apiParams);
  }

  @Override
  public BaseResponse callApiByChanId(CallApiDto param) {
    QueryWrapper<ApiToken> apiTokenQueryWrapper = new QueryWrapper<>();
    apiTokenQueryWrapper.eq("CHANNEL_ID",param.getChannelId());

    List<ApiToken> apiTokens = iApiTokenService.list(apiTokenQueryWrapper);
    String channelName="";
    if(apiTokens.size()>0){
      channelName=apiTokens.get(0).getChannelName();
    }
    return  BaseResponse.success(channelName);
  }

  private void analysis(List<ApiParam> apiParamList,ApiParam apiParam,Map<ApiParam,TreeMap> apiParams){
    switch (apiParam.getParamType()) {
      case GlobalConstant.API_PARAM_TYPE.MAP:
      case GlobalConstant.API_PARAM_TYPE.LIST:
        TreeMap childMap = new TreeMap<ApiParam,TreeMap>();
        apiParams.put(apiParam, childMap);
        apiParamList.remove(apiParam);
        List<ApiParam> children = findChildren(apiParamList,apiParam);
        children.forEach(child -> analysis(apiParamList,child,childMap));
        break;
      case GlobalConstant.API_PARAM_TYPE.STRING:
      case GlobalConstant.API_PARAM_TYPE.INT:
      case GlobalConstant.API_PARAM_TYPE.DOUBLE:
      case GlobalConstant.API_PARAM_TYPE.DATE:
        apiParams.put(apiParam,null);
        apiParamList.remove(apiParam);
        break;
    }

  }

  private List<ApiParam> findChildren(List<ApiParam> apiParamList,ApiParam apiParam){
    List<ApiParam> children = new ArrayList<>();
    for(ApiParam param: apiParamList){
      if(param.getParentParamId().equals(apiParam.getParamId())){
        children.add(param);
      }
    }
    return children;
  }

  private  Map<String,String> getToken(String input ,String dataType){
    Map<String,String> result = new HashMap<>();
    if(GlobalConstant.API_DATA_FORMAT.JSON.equals(dataType)){
      Map res = null;
      try {
        res = JSON.parseObject(input,Map.class);
        result.put("channelId", res.get("channelId").toString());
        result.put("token",  res.get("token").toString());
//        result.put("callTag", (String) res.get("callTag"));
//        result.put("applicationId", (String) res.get("applicationId"));
//        result.put("productId", (String) res.get("productId"));
//        result.put("goodId", (String) res.get("goodId"));
        result.put("userId", (String) res.get("openId"));
      } catch (Exception e) {
        logger.error("json parse error.");
        e.printStackTrace();
        return null;
      }

    }else if(GlobalConstant.API_DATA_FORMAT.XML.equals(dataType)){
      try {
        Document document = DocumentHelper.parseText(input);
        Element root = document.getRootElement();
        Iterator it = root.elementIterator();
        while (it.hasNext()) {
          Element pa = (Element) it.next();
          if(pa.isTextOnly()&& ("channelId".equals(pa.getName())||"token".equals(pa.getName())) ){
            result.put(pa.getName(), pa.getStringValue());
          }
          if("openId".equals(pa.getName())){
            result.put("userId", pa.getStringValue());
          }
        }
      }catch (DocumentException e){
        logger.error("xml parse error.");
        e.printStackTrace();
        return null;
      }
    }
    return result;
  }

  private BaseResponse<Map> parseXML2Map(String input,Map<ApiParam,TreeMap> apiParams){
    Map<String,Object> result = new HashMap<>();

    try {
      Document document = DocumentHelper.parseText(input);
      Element root = document.getRootElement();
      Iterator rootIt = root.elementIterator();
      ApiParam apiParam = new ApiParam(root.getName());
      Map<ApiParam,TreeMap> rootParams = apiParams.get(apiParam);
      if(rootParams == null){
        return BaseResponse.fail(result,"XML parse error. root name is wrong.");
      }
//      Map<String,Object> rootMap = new HashMap<>();
//      result.put(root.getName(),rootMap);
      while (rootIt.hasNext()){
        Element child = (Element) rootIt.next();
        BaseResponse<Map> response = parseXMLChild(child,rootParams,result);
        if (!StatusEnum.SUCCESS.getCode().equals(response.getCode())) {
          return response;
        }
      }

    } catch (DocumentException e) {
      logger.error("xml parse error.");
      e.printStackTrace();
      return BaseResponse.fail(result,"XML parse error");
    }
    return BaseResponse.success(result);
  }

  private BaseResponse<Map> parseXMLChild(Element element, Map<ApiParam,TreeMap> apiParams,Map<String,Object> result){
    String childName = element.getName();
    ApiParam filter =  new ApiParam(childName);
    Optional<Map.Entry<ApiParam,TreeMap>> entryOptional = apiParams.entrySet().stream().filter(entry-> entry.getKey().equals(filter) ).findAny();
    //该字段不存在于参数表
    if(!entryOptional.isPresent()){
      return BaseResponse.success();
    }
    Map.Entry<ApiParam, TreeMap> entry = entryOptional.get();
    ApiParam param = entry.getKey();
    TreeMap<ApiParam, TreeMap> children = entry.getValue();

    if (GlobalConstant.API_PARAM_TYPE.LIST.equals(param.getParamType())) {
      if(children.size()!=1){
        logger.error("The list must have only one node!params name:{}",param.getParamName());
        return BaseResponse.fail(result,"list node("+param.getParamName()+")must have only one node");
      }
      List list = new ArrayList();
      ApiParam childP = children.firstEntry().getKey();
      TreeMap<ApiParam,TreeMap> grandChildren = children.firstEntry().getValue();
      if(GlobalConstant.API_PARAM_TYPE.LIST.equals(childP.getParamType())){
        logger.error("The list can not have list node!params name:{}",childP.getParamName());
        return BaseResponse.fail(result,"list node("+param.getParamName()+") can not have list node");
      }else if(GlobalConstant.API_PARAM_TYPE.MAP.equals(childP.getParamType())){
        Iterator<Element> childrenEleIt = element.elementIterator();
        while (childrenEleIt.hasNext()){
          //list下的Map节点的一个entry
          Element childEle = childrenEleIt.next();
          //只取list下配置的子节点
          if(!param.getParamName().equals(childEle.getName())){
            continue;
          }
          Map<String,Object> childMap = new HashMap<>();
          BaseResponse<Map> response = parseXMLChild(childEle,grandChildren,childMap);
          if (!StatusEnum.SUCCESS.getCode().equals(response.getCode())) {
            return response;
          }
          list.add(childMap);
        }
        result.put(param.getParamName(),list);
      }else {
        Iterator<Element> childrenEleIt = element.elementIterator();
        while (childrenEleIt.hasNext()){
          Element childEle = childrenEleIt.next();
          //只取list下配置的子节点
          if(!param.getParamName().equals(childEle.getName())){
            continue;
          }
          Object value = parseValue(childEle.getStringValue(),childP.getParamType());
          list.add(value);
        }
        result.put(param.getParamName(),list);
      }

    }else if(GlobalConstant.API_PARAM_TYPE.MAP.equals(param.getParamType())){
      Map<String,Object> map = new HashMap<>();
      result.put(param.getParamName(),map);
      Iterator childrenEleIt = element.elementIterator();
      while (childrenEleIt.hasNext()){
        Element childEle = (Element) childrenEleIt.next();
        BaseResponse<Map> response = parseXMLChild(childEle,children,map);
        if (!StatusEnum.SUCCESS.getCode().equals(response.getCode())) {
          return response;
        }
      }

    }else {
      // 非集合
      Object value = parseValue(element.getStringValue(),param.getParamType());
      result.put(param.getParamName(),value);
    }

    return BaseResponse.success(result);
  }


  /**
   * 从入参Map中获取，参数表所配置的参数
   * @param apiParam 当前节点
   * @param children 当前节点的子节点
   * @param jsonMap 当前节点所处入参Map
   * @param result 当前节点所处出参map
   * @return
   */
  private BaseResponse<Map> getRequiredParams(ApiParam apiParam,TreeMap<ApiParam,TreeMap> children,Map<String,Object> jsonMap,Map<String,Object> result){
    if(GlobalConstant.API_PARAM_TYPE.MAP.equals(apiParam.getParamType())){
      if(jsonMap.get(apiParam.getParamName()) != null){
        Object value = jsonMap.get(apiParam.getParamName());
        if(value instanceof Map){
          Map map = new HashMap();
          result.put(apiParam.getParamName(), map);
          for (Map.Entry<ApiParam, TreeMap> entry : children.entrySet()) {
            ApiParam child = entry.getKey();
            TreeMap<ApiParam, TreeMap> grandChildren = entry.getValue();
            BaseResponse<Map> response = getRequiredParams(child, grandChildren, (Map<String, Object>) value, map);
            if (!StatusEnum.SUCCESS.getCode().equals(response.getCode())) {
              return response;
            }
          }
        }else {
          logger.error("map param:{} is not map!,value is :{} ,type is:{}"
              ,apiParam.getParamName()
              ,value
              ,value.getClass());
          return BaseResponse.fail(result,"map node("+apiParam.getParamName()+") it's value is not map");
        }
      }else {
        if(GlobalConstant.YES_OR_NO.NO.equals(apiParam.getIsEmpty())){
          logger.error("required param:{},can not be empty!",apiParam.getParamName());
          return BaseResponse.fail(result,"required param("+apiParam.getParamName()+") it's value is empty");
        }
      }
    }else if( GlobalConstant.API_PARAM_TYPE.LIST.equals(apiParam.getParamType())){
      if(jsonMap.get(apiParam.getParamName()) != null){
        Object value = jsonMap.get(apiParam.getParamName());
        if( value instanceof List){
          if(children.size()!=1){
            logger.error("The list must have only one node!params name:{}",apiParam.getParamName());
            return BaseResponse.fail(result,"list node("+apiParam.getParamName()+")must have only one node");
          }
          List list = new ArrayList();
          result.put(apiParam.getParamName(),list);
          ApiParam child = children.firstEntry().getKey();
          TreeMap<ApiParam,TreeMap> grandChildren = children.firstEntry().getValue();
          if(GlobalConstant.API_PARAM_TYPE.LIST.equals(child.getParamType())){
            logger.error("The list can not have list node!params name:{}",apiParam.getParamName());
            return BaseResponse.fail(result,"list node("+apiParam.getParamName()+")can not have list node");
          }else if(GlobalConstant.API_PARAM_TYPE.MAP.equals(child.getParamType())){
            //list 下为map
            for(Object o:(List)value){
              Map<String,Object> row = (Map) o;
              Map<String,Object> rowResult = new HashMap();
              BaseResponse<Map> response = getRequiredParams(child,grandChildren,row,rowResult);
              if(!StatusEnum.SUCCESS.getCode().equals(response.getCode())){
                return response;
              }
              list.add(rowResult);
            }
          }else {
            for(Object o:(List)value){
              Object v = parseValue(o,child.getParamType());
              if(v == null){
                logger.error("parse value error.param name:{},paramId:{}",child.getParamName(),child.getParamId());
                return BaseResponse.fail(result,"node("+apiParam.getParamName()+")parse value error，value("+o.toString()+")");
              }
              list.add(v);//list 赋值
            }
          }


        }else {
          logger.error("list param:{} is not list!,value is :{} ,type is:{}"
              ,apiParam.getParamName()
              ,value
              ,value.getClass());
          return BaseResponse.fail(result,"list node("+apiParam.getParamName()+") it's value is not list");
        }
      }else {
        if(GlobalConstant.YES_OR_NO.NO.equals(apiParam.getIsEmpty())){
          logger.error("required param:{},can not be empty!",apiParam.getParamName());
          return BaseResponse.fail(result,"required param("+apiParam.getParamName()+") can not be empty");
        }
      }
    }else{
      if(jsonMap.get(apiParam.getParamName()) != null){
        Object value = parseValue(jsonMap.get(apiParam.getParamName()),apiParam.getParamType());
        if(value == null){
          logger.error("param convert error.param name:{}",apiParam.getParamName());
        }
        result.put(apiParam.getParamName(),value);
      }else {
        if(GlobalConstant.YES_OR_NO.NO.equals(apiParam.getIsEmpty())){
          if(apiParam.getDefaultValue()==null){
            logger.error("required param:{},can not be empty!",apiParam.getParamName());
            return BaseResponse.fail(result,"required param ("+apiParam.getParamName()+") can not be empty");
          }else {
            Object value = parseValue(apiParam.getDefaultValue(),apiParam.getParamType());
            if(value == null){
              logger.error("required param:{},can not be empty!",apiParam.getParamName());
              return BaseResponse.fail(result,"required param("+apiParam.getParamName()+") can not be empty");
            }
          }
        }else {
          //字段为空
        }
      }
    }
    return BaseResponse.success(result);
  }

  private BaseResponse<Map> parseString2Map(String inputString,Map<ApiParam,TreeMap> apiParams,String dataType){
    Map<String,Object> map = new HashMap<>();
    BaseResponse<Map> response = BaseResponse.success(map);
    BaseResponse<Map>  responseI = null;
    if(GlobalConstant.API_DATA_FORMAT.JSON.equals(dataType)){
      Map jsonMap = JSON.parseObject(inputString,Map.class);
      for(Map.Entry<ApiParam,TreeMap> entry:apiParams.entrySet()) {
        responseI = getRequiredParams(entry.getKey(),entry.getValue(),jsonMap,map);
        if(!StatusEnum.SUCCESS.getCode().equals(responseI.getCode())){
          logger.error("json parse error.json context:{}",inputString);
          return  responseI;
        }
      }
    }else if(GlobalConstant.API_DATA_FORMAT.XML.equals(dataType)){
      response = parseXML2Map(inputString,apiParams);
    }else {
      response = BaseResponse.fail(map,"api data type config is error");
    }

    return response;
  }

  private String parseMap2String(Map<String,Object> data,String dataType,String header,Map<ApiParam,TreeMap> apiParams) throws Exception{

    if(GlobalConstant.API_DATA_FORMAT.JSON.equals(dataType)){
      return JSON.toJSONString(data);
    }else if(GlobalConstant.API_DATA_FORMAT.XML.equals(dataType)){
      Map<String,Object> root = new HashMap<>();
      Optional<Map.Entry<ApiParam,TreeMap>> opt = apiParams.entrySet().stream().findFirst();
      if(opt.isPresent()){
        Map.Entry<ApiParam,TreeMap> rootParam = opt.get();
//        root.put(rootParam.getKey().getParamName(),data);
        String xml = XmlUtil.map2xml(data,rootParam.getKey().getParamName()).getRootElement().asXML();
        if(header == null){
          return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+xml;
        }
        return header+"\n"+xml;
      }

    }
    return "";
  }


  private Object parseValue(Object value,String type){
    switch (type) {
      case GlobalConstant.API_PARAM_TYPE.MAP:
        if(value instanceof Map){
          return value;
        }else {
          logger.error("value :{} is not map ,it's :{}",value,value.getClass());
          break;
        }
      case GlobalConstant.API_PARAM_TYPE.LIST:
        if(value instanceof List){
          return value;
        }else {
          logger.error("value :{} is not list ,it's :{}",value,value.getClass());
          break;
        }
      case GlobalConstant.API_PARAM_TYPE.STRING:
        if(value != null){
          return value.toString();
        }
        break;
      case GlobalConstant.API_PARAM_TYPE.INT:
        if(value != null){
          Integer res;
          try {
            res = Integer.parseInt(value.toString());
          } catch (NumberFormatException e) {
            logger.error("value:{} can not convert to int.",value);
            e.printStackTrace();
            return null;
          }
          return res;
        }
        break;
      case GlobalConstant.API_PARAM_TYPE.DOUBLE:
        if(value != null){
          Double res;
          try {
            res = Double.parseDouble(value.toString());
          } catch (NumberFormatException e) {
            logger.error("value:{} can not convert to double.",value);
            e.printStackTrace();
            return null;
          }
          return res;
        }
        break;
      case GlobalConstant.API_PARAM_TYPE.DATE:
        if(value != null){
          return DateUtil.string2Date(value.toString(),DateUtil.SEG_YYYYMMDD_HHMMSS);
        }
        break;
    }
    return null;
  }

  private boolean judgeFlow(String apiId,String channelId){
    Timestamp now =  new Timestamp(new Date().getTime());
    QueryWrapper<ApiFlowControl> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("API_ID",apiId)
        .le("START_TIME",now)
        .gt("END_TIME",now);
    List<ApiFlowControl> flowControls = iApiFlowControlService.list(queryWrapper);
    if(flowControls == null || flowControls.size()==0){
      //没有流控设置则通过
      return true;
    }

    Optional<ApiFlowControl> optionalApiFlowControl = flowControls.stream()
        .filter(row -> channelId.equals(row.getChannelId())&&GlobalConstant.FLOW_CONTROL_TYPE.CHANNEL.equals(row.getFlowControlType()) )
        .findAny();

    if(optionalApiFlowControl.isPresent()){
      // 有渠道级先走渠道级
      return flowControl(optionalApiFlowControl.get(),apiId,channelId);

    }else {
      //没有渠道级，走api级
      optionalApiFlowControl = flowControls.stream()
          .filter(row -> GlobalConstant.FLOW_CONTROL_TYPE.API.equals(row.getFlowControlType()) )
          .findAny();
      if (optionalApiFlowControl.isPresent()){
        return flowControl(optionalApiFlowControl.get(),apiId,"-1");
      }else {
        //没有流控设置则通过
        return true;
      }
    }


  }

  private boolean flowControl(ApiFlowControl apiFlowControl,String apiId,String channelId){
    long interval = calculateSeconds(apiFlowControl.getTimeInterval(),apiFlowControl.getLimitUnitTime());
    long now = DateUtil.nowAbsSeconds();
    long start = now-interval;
    List<Long> callRecords = callRecordsMap.get(apiId+"_"+channelId);
    if(callRecords == null){
      callRecords = new ArrayList<>();
      callRecordsMap.put(apiId+"_"+channelId,callRecords);
//      return true;
    }




    for (int i = 0; i < callRecords.size(); i++) {
      Long time = callRecords.get(i);
      if(time<start){
        callRecords.remove(i);
        i--;
      }
    }
    //总次数，拒绝也算
    callRecords.add(now);
    if(!"-1".equals(channelId)){
      //channel级别,需要给api级别添加一条记录
      List<Long> allCallRecords = callRecordsMap.get(apiId+"_-1");
      if(allCallRecords == null){
        allCallRecords = new ArrayList<>();
        allCallRecords.add(now);
        callRecordsMap.put(apiId+"_-1",allCallRecords);
      }else {
        allCallRecords.add(now);
      }
    }
    if(callRecords.size()> apiFlowControl.getLimitCount()){
      logger.info("api rejected by flow control!api call counts:{} ,apiId:{},channelId:{}",callRecords.size(),apiId,channelId);
      return false;
    }
    return true;
  }

  private long calculateSeconds(long value,String unit){
    switch (unit){
      case GlobalConstant.FLOW_CONTROL_TIME_UNIT.SECONDS:
        return value;
      case GlobalConstant.FLOW_CONTROL_TIME_UNIT.MIMUTE:
        return value*60;
      case GlobalConstant.FLOW_CONTROL_TIME_UNIT.HOUR:
        return value*60*60;
      case GlobalConstant.FLOW_CONTROL_TIME_UNIT.DAY:
        return value*60*60*24;
        default:
          return 60L;
    }
  }



}
