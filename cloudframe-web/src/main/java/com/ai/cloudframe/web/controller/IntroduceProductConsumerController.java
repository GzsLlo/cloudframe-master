package com.ai.cloudframe.web.controller;

import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.common.base.support.BaseController;
import com.ai.cloudframe.common.base.util.DateUtil;
import com.ai.cloudframe.common.base.util.Utility;
import com.ai.cloudframe.provider.api.sys.model.dto.IntroduceProductDto;
import com.ai.cloudframe.provider.api.sys.model.dto.ProductDto;
import com.ai.cloudframe.provider.api.sys.service.IntroduceProductServiceApi;
import com.ai.cloudframe.provider.api.sys.service.ParamServiceApi;
import com.ai.cloudframe.provider.api.sys.service.ProductServiceApi;


import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

//import com.neusoft.encrypt.sm4.encryptor.SM4Encryptor;


/**
 * zhouzhou.
 */
@RestController
@Api(description = "引入产品相关接口")
@CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = "*")
public class IntroduceProductConsumerController extends BaseController {


  @Autowired
  ParamServiceApi paramServiceApi;

  @Autowired
  IntroduceProductServiceApi introduceProductServiceApi;

  @RequestMapping(value = "/selectIntroduceProduct", method = RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
          consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  @ApiOperation("引入列表查询")
  @ApiImplicitParams({
    @ApiImplicitParam(name="vueJson",value="提交报文",required=true,paramType="form")
  })
  public BaseResponse selectIntroduceProduct(@RequestParam Map<String, Object> vueJson, @RequestHeader HttpHeaders headers){
    logger.info("selectIntroduceProduct.param:{}", vueJson);

    JSONObject map = Utility.convert2JsonAndCheckRequiredColumns(vueJson, "");

    logger.info("map {}",map);
    BaseResponse<Map> baseResponse = introduceProductServiceApi.selectIntroduceProduct(map);
    logger.info("baseResponse:",baseResponse.getData());
    List<Map<String,Object>> dataList = (List<Map<String,Object>> ) baseResponse.getData().get("data");
    Map<String,Object> param = new HashMap<>();

    for(Map dataMap:dataList){

      if(!StringUtils.isEmpty(dataMap.get("introduceProductType"))) {
        BaseResponse<String> codeToName2 = paramServiceApi
                .codeToName("product_type", (String) dataMap.get("introduceProductType"));
        dataMap.put("introduceProductType", codeToName2.getData());
      }

      if(!StringUtils.isEmpty(dataMap.get("introduceStatus"))) {
        BaseResponse<String> codeToName2 = paramServiceApi
                .codeToName("product_status", (String) dataMap.get("introduceStatus"));
        dataMap.put("introduceStatus", codeToName2.getData());
      }

      if(!StringUtils.isEmpty(dataMap.get("testStatus"))) {
        BaseResponse<String> codeToName2 = paramServiceApi
            .codeToName("testStatus", (String) dataMap.get("testStatus"));
        dataMap.put("testStatus", codeToName2.getData());
      }

      if (!StringUtils.isEmpty(dataMap.get("chargeType"))) {

        if("0".equals(dataMap.get("chargeType").toString())){
          dataMap.put("productPrice", dataMap.get("productPrice").toString() + "元");
        }else if("1".equals(dataMap.get("chargeType").toString())){
          dataMap.put("productPrice", dataMap.get("productPrice").toString() + "元/月");
        }else if("2".equals(dataMap.get("chargeType").toString())){
          dataMap.put("productPrice", dataMap.get("productPrice").toString() + "元/次");
        }

        BaseResponse<String> codeToName2 = paramServiceApi
            .codeToName("charge_type", (String) dataMap.get("chargeType"));
        dataMap.put("chargeType", codeToName2.getData());
      }

      if (!StringUtils.isEmpty(dataMap.get("releaseDate"))) {
        ArrayList l = (ArrayList) dataMap.get("releaseDate");
        String dateString = l.get(0)+"-"+l.get(1)+"-"+l.get(2)+" "+l.get(3)+":"+l.get(4)+":";
        if(l.size()==6){
          dateString +=l.get(5);
        }
        dataMap.put("releaseDate",dateString);
      }

      dataMap.put("merchants", "某信科技");


    }

    Map resultMap = new HashMap();
    resultMap.put("dataList",dataList);
    resultMap.put("total",baseResponse.getData().get("total"));
    resultMap.put("page",baseResponse.getData().get("page"));
    resultMap.put("size",baseResponse.getData().get("size"));
    return baseResponse.success(resultMap);
  }


  @RequestMapping(value = "/newIntroduceProduct", method = RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
          consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  @ApiOperation("产品引入")
  @ApiImplicitParams({
          @ApiImplicitParam(name="vueJson",value="提交报文",required=true,paramType="form")
  })
  public BaseResponse newIntroduceProduct(@RequestParam Map<String, Object> vueJson, @RequestHeader HttpHeaders headers){
    logger.info("newProduct.param:{}", vueJson);
    IntroduceProductDto introduceProductDto = new IntroduceProductDto();

    JSONObject info = Utility.convert2JsonAndCheckRequiredColumns(vueJson, "");

    String introduceProductName = info.getString("introduceProductName");
    introduceProductDto.setIntroduceProductName(introduceProductName);

    String introduceProductType = info.getString("introduceProductType");
    introduceProductDto.setIntroduceProductType(introduceProductType);


    String atomUrl = info.getString("atomUrl");
    introduceProductDto.setAtomUrl(atomUrl);


    String atomId = info.getString("atomId");
    introduceProductDto.setAtomId(atomId);

    String chargeType = info.getString("chargeType");
    introduceProductDto.setChargeType(chargeType);

    String productPrice = info.getString("productPrice");
    introduceProductDto.setProductPrice(productPrice);


    String testStatus = info.getString("testStatus");
    introduceProductDto.setTestStatus(testStatus);


    String introduceProductDatail = info.getString("introduceProductDatail");
    introduceProductDto.setIntroduceProductDatail(introduceProductDatail);

    String versionNumber = info.getString("versionNumber");
    introduceProductDto.setVersionNumber(versionNumber);


    String createUserId = info.getString("createUserId");
    introduceProductDto.setCreateUserId(createUserId);

    introduceProductDto.setProductId("未创建原子产品");

    introduceProductDto.setIntroduceStatus("0");//0：未提交1：待审核2：待发布3：已发布4：已下架

    Date date2 = new Date();
    Instant instant2 = date2.toInstant();//An instantaneous point on the time-line.(时间线上的一个瞬时点。)
    ZoneId zoneId2 = ZoneId.systemDefault();//A time-zone ID, such as {@code Europe/Paris}.(时区)
    LocalDateTime localDateTime2 = instant2.atZone(zoneId2).toLocalDateTime();
    introduceProductDto.setStartDate(localDateTime2);


    String endDate = "2099-01-01 00:00:00";
    Date date3 = DateUtil.string2Date(endDate, DateUtil.SEG_YYYYMMDD_HHMMSS);
    Instant instant3 = date3.toInstant();//An instantaneous point on the time-line.(时间线上的一个瞬时点。)
    ZoneId zoneId3 = ZoneId.systemDefault();//A time-zone ID, such as {@code Europe/Paris}.(时区)
    LocalDateTime localDateTime3 = instant3.atZone(zoneId3).toLocalDateTime();
    introduceProductDto.setEndDate(localDateTime3);



    logger.info("productDto {}",introduceProductDto);
    BaseResponse baseResponse = introduceProductServiceApi.newIntroduceProduct(introduceProductDto);

    logger.info("baseResponse:",baseResponse.getMessage());
    return baseResponse;
  }



  @RequestMapping(value = "/introduceProductsStatusUpdate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
          consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  @ApiOperation("产品状态修改")
  @ApiImplicitParams({
          @ApiImplicitParam(name = "vueJson", value = "提交报文", required = true, paramType="form")
  })
  public BaseResponse introduceProductsStatusUpdate(@RequestParam  Map<String, Object> vueJson, @RequestHeader HttpHeaders headers) {
    logger.info("productDto{}", vueJson);
    IntroduceProductDto introduceProductDto = new IntroduceProductDto();
    JSONObject info = Utility.convert2JsonAndCheckRequiredColumns(vueJson, "");

    String introduceProductId = info.getString("introduceProductId");
    introduceProductDto.setIntroduceProductId(introduceProductId);

    String introduceStatus = info.getString("introduceStatus");
    introduceProductDto.setIntroduceStatus(introduceStatus);

    String productPrice = info.getString("productPrice");
    introduceProductDto.setProductPrice(productPrice);


    String examineUserId = info.getString("examineUserId");
    introduceProductDto.setExamineUserId(examineUserId);

    String examineDetail = info.getString("examineDetail");
    introduceProductDto.setExamineDetail(examineDetail);


    String registerTime = info.getString("examineDate");
    LocalDateTime localDateTime2 = null;
    if(!"".equals(registerTime)){
      Date date2 = DateUtil.string2Date(registerTime,DateUtil.SEG_YYYYMMDD_HHMMSS);
      if(date2 == null){
        date2 = DateUtil.string2Date(registerTime,DateUtil.SEG_YYYYMMDD);
      }
      Instant instant2 = date2.toInstant();//An instantaneous point on the time-line.(时间线上的一个瞬时点。)
      ZoneId zoneId2 = ZoneId.systemDefault();//A time-zone ID, such as {@code Europe/Paris}.(时区)
      localDateTime2 = instant2.atZone(zoneId2).toLocalDateTime();
    }
    introduceProductDto.setExamineDate(localDateTime2);


    String registerTime2 = info.getString("releaseDate");
    LocalDateTime localDateTime3 = null;
    if(!"".equals(registerTime2)){
      Date date3 = DateUtil.string2Date(registerTime2,DateUtil.SEG_YYYYMMDD_HHMMSS);
      if(date3 == null){
        date3 = DateUtil.string2Date(registerTime2,DateUtil.SEG_YYYYMMDD);
      }
      Instant instant3 = date3.toInstant();//An instantaneous point on the time-line.(时间线上的一个瞬时点。)
      ZoneId zoneId3 = ZoneId.systemDefault();//A time-zone ID, such as {@code Europe/Paris}.(时区)
      localDateTime3 = instant3.atZone(zoneId3).toLocalDateTime();
    }
    introduceProductDto.setReleaseDate(localDateTime3);


    BaseResponse baseResponse = introduceProductServiceApi.introduceProductsStatusUpdate(introduceProductDto);
    logger.info("baseResponse:", baseResponse.getData());

    return baseResponse;
  }

}
