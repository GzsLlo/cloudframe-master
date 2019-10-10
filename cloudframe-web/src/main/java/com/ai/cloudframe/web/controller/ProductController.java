package com.ai.cloudframe.web.controller;

import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.common.base.support.BaseController;
import com.ai.cloudframe.common.base.util.Utility;
import com.ai.cloudframe.provider.api.sys.model.dto.GoodsDto;
import com.ai.cloudframe.provider.api.sys.model.dto.ProductDto;
import com.ai.cloudframe.provider.api.sys.model.vo.ProdIntroProdVo;
import com.ai.cloudframe.provider.api.sys.service.ParamServiceApi;
import com.ai.cloudframe.provider.api.sys.service.ProductServiceApi;

import com.ai.cloudframe.common.base.util.DateUtil;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

//import com.neusoft.encrypt.sm4.encryptor.SM4Encryptor;


/**
 * zhouzhou.
 */
@RestController
@Api(description = "原子产品相关接口")
@CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = "*")
public class ProductController extends BaseController {


  @Autowired
  ParamServiceApi paramServiceApi;

  @Autowired
  ProductServiceApi productServiceApi;

  @RequestMapping(value = "/selectProduct", method = RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
          consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  @ApiOperation("原子产品列表查询")
  @ApiImplicitParams({
    @ApiImplicitParam(name="vueJson",value="提交报文",required=true,paramType="form")
  })
  public BaseResponse selectProduct(@RequestParam Map<String, Object> vueJson, @RequestHeader HttpHeaders headers){
    logger.info("selectProduct.param:{}", vueJson);

    JSONObject map = Utility.convert2JsonAndCheckRequiredColumns(vueJson, "");

    logger.info("map {}",map);
    BaseResponse<Map> baseResponse = productServiceApi.selectProduct(map);
    logger.info("baseResponse:",baseResponse.getData());
    List<Map<String,Object>> dataList = (List<Map<String,Object>> ) baseResponse.getData().get("data");
    Map<String,Object> param = new HashMap<>();

    for(Map dataMap:dataList){

      if(!StringUtils.isEmpty(dataMap.get("introduceProductType"))) {
        BaseResponse<String> codeToName2 = paramServiceApi
                .codeToName("product_type", (String) dataMap.get("introduceProductType"));
        dataMap.put("introduceProductType", codeToName2.getData());
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


      dataMap.put("merchants", "某信科技");


    }

    Map resultMap = new HashMap();
    resultMap.put("dataList",dataList);
    resultMap.put("total",baseResponse.getData().get("total"));
    resultMap.put("page",baseResponse.getData().get("page"));
    resultMap.put("size",baseResponse.getData().get("size"));
    return baseResponse.success(resultMap);
  }



  @RequestMapping(value = "/selectOneProduct", method = RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
          consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  @ApiOperation("单个产品查询")
  @ApiImplicitParams({
          @ApiImplicitParam(name="vueJson",value="提交报文",required=true,paramType="form")
  })
  public BaseResponse selectOneProduct(@RequestParam Map<String, Object> vueJson, @RequestHeader HttpHeaders headers){
    logger.info("selectOneProduct.param:{}", vueJson);

    JSONObject map = Utility.convert2JsonAndCheckRequiredColumns(vueJson, "");

    logger.info("map {}",map);
    BaseResponse baseResponse = productServiceApi.selectOneProduct(map);
    logger.info("baseResponse:",baseResponse.getData());
    List<Map<String,Object>> dataList = (List<Map<String,Object>> ) baseResponse.getData();
    Map<String,Object> param = new HashMap<>();

    for(Map dataMap:dataList){

      if(!StringUtils.isEmpty(dataMap.get("productType"))) {
        BaseResponse<String> codeToName2 = paramServiceApi
                .codeToName("product_type", (String) dataMap.get("productType"));
        dataMap.put("productType", codeToName2.getData());
      }

      if(!StringUtils.isEmpty(dataMap.get("examineStatus"))) {
        BaseResponse<String> codeToName2 = paramServiceApi
                .codeToName("product_status", (String) dataMap.get("examineStatus"));
        dataMap.put("examineStatus", codeToName2.getData());
      }

      if(!StringUtils.isEmpty(dataMap.get("chargeType"))) {
        BaseResponse<String> codeToName2 = paramServiceApi
                .codeToName("charge_type", (String) dataMap.get("chargeType"));
        dataMap.put("chargeType", codeToName2.getData());
      }


    }



    Map resultMap = new HashMap();
    resultMap.put("dataList",dataList);
    return baseResponse.success(resultMap);
  }


  @RequestMapping(value = "/newProduct", method = RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
          consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  @ApiOperation("原子产品引入")
  @ApiImplicitParams({
          @ApiImplicitParam(name="vueJson",value="提交报文",required=true,paramType="form")
  })
  public BaseResponse newProduct(@RequestParam Map<String, Object> vueJson, @RequestHeader HttpHeaders headers){
    logger.info("newProduct.param:{}", vueJson);
    ProductDto productDto = new ProductDto();

    JSONObject info = Utility.convert2JsonAndCheckRequiredColumns(vueJson, "");
    List productId2List = (List)info.get("productId2List");
    productDto.setProductId2(productId2List);

    String productName = info.getString("productName");
    productDto.setProductName(productName);

    String introduceProductId = info.getString("introduceProductId");
    productDto.setIntroduceProductId(introduceProductId);

    String introduceProductName = info.getString("introduceProductName");
    productDto.setIntroduceProductName(introduceProductName);

    String introduceProductType = info.getString("introduceProductType");
    productDto.setIntroduceProductType(introduceProductType);


    String chargeType = info.getString("chargeType");
    productDto.setChargeType(chargeType);



    String productPrice = info.getString("productPrice");
    if(productPrice!= null || productPrice!= ""){
      productPrice = productPrice.substring(0,productPrice.indexOf("元"));
    }
    productDto.setProductPrice(productPrice);


    Date date2 = new Date();
    Instant instant2 = date2.toInstant();//An instantaneous point on the time-line.(时间线上的一个瞬时点。)
    ZoneId zoneId2 = ZoneId.systemDefault();//A time-zone ID, such as {@code Europe/Paris}.(时区)
    LocalDateTime localDateTime2 = instant2.atZone(zoneId2).toLocalDateTime();
    productDto.setStartDate(localDateTime2);


    String endDate = "2099-01-01 00:00:00";
    Date date3 = DateUtil.string2Date(endDate,DateUtil.SEG_YYYYMMDD_HHMMSS);
    Instant instant3 = date3.toInstant();//An instantaneous point on the time-line.(时间线上的一个瞬时点。)
    ZoneId zoneId3 = ZoneId.systemDefault();//A time-zone ID, such as {@code Europe/Paris}.(时区)
    LocalDateTime localDateTime3 = instant3.atZone(zoneId3).toLocalDateTime();
    productDto.setEndDate(localDateTime3);



    logger.info("productDto {}",productDto);
    BaseResponse baseResponse = productServiceApi.newProduct(productDto);

    logger.info("baseResponse:",baseResponse.getMessage());
    return baseResponse;
  }





  @RequestMapping(value = "/productMutexCheck", method = RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  @ApiOperation("产品互斥状态校验")
  @ApiImplicitParams({
      @ApiImplicitParam(name="vueJson",value="提交报文",required=true,paramType="form")
  })
  public BaseResponse productMutexCheck(@RequestParam Map<String, Object> vueJson, @RequestHeader HttpHeaders headers) {
    logger.info("vueJson{}", vueJson);
    String productIds=vueJson.get("productIds").toString();
//    String productIds="3;3a1b326d-0ade-4d1c-b34a-4c9cdbe747d0;2";

    BaseResponse baseResponse = productServiceApi.productMutexCheck(productIds);
    logger.info("baseResponseProductMutex:", baseResponse.getData());

    return baseResponse;
  }

  @RequestMapping(value = "/queryTable/prodUniIntroProdList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation("原子产品信息列表查询")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "vueJson", value = "提交报文", required = true, dataType = "form")
  })
  public BaseResponse goodInfoList(@RequestBody ProdIntroProdVo prodIntroProdVo, @RequestHeader HttpHeaders headers) {
    logger.info("prodIntroProdVo{}", prodIntroProdVo);

    BaseResponse<Map> baseResponse = productServiceApi.prodUniIntroProdQueryTable(prodIntroProdVo);
    logger.info("baseResponse:", baseResponse.getData());

    for (Map each:(List<Map>)baseResponse.getData().get("records")) {

      if(!StringUtils.isEmpty(each.get("introduceProductType"))) {
        BaseResponse<String> codeToName2 = paramServiceApi
            .codeToName("product_type",each.get("introduceProductType").toString());
        each.put("introduceProductTypeName", codeToName2.getData());
      }

      if(!StringUtils.isEmpty(each.get("chargeType"))) {
        BaseResponse<String> codeToName2 = paramServiceApi
            .codeToName("charge_type",each.get("chargeType").toString());
        each.put("chargeTypeName", codeToName2.getData());
      }
//
//      if(!StringUtils.isEmpty(each.get("isExperienceProduct"))) {
//        BaseResponse<String> codeToName2 = paramServiceApi
//            .codeToName("if_status",each.get("isExperienceProduct").toString());
//        each.put("isExperienceProductName", codeToName2.getData());
//      }

    }

    return baseResponse;
  }


  @RequestMapping(value = "/selectGoodProductSettle", method = RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  @ApiOperation("结算列表查询")
  @ApiImplicitParams({
      @ApiImplicitParam(name="vueJson",value="提交报文",required=true,paramType="form")
  })
  public BaseResponse selectGoodProductSettle(@RequestParam Map<String, Object> vueJson, @RequestHeader HttpHeaders headers){
    logger.info("selectIntroduceProduct.param:{}", vueJson);

    JSONObject map = Utility.convert2JsonAndCheckRequiredColumns(vueJson, "");

    logger.info("map {}",map);
    BaseResponse<Map> baseResponse = productServiceApi.selectGoodProductSettle(map);
    logger.info("baseResponse:",baseResponse.getData());
    List<Map<String,Object>> dataList = (List<Map<String,Object>> ) baseResponse.getData().get("data");
    Map<String,Object> param = new HashMap<>();

    for(Map dataMap:dataList){

      if (!StringUtils.isEmpty(dataMap.get("chargeType"))) {

        if("0".equals(dataMap.get("chargeType").toString())){
          dataMap.put("productPrice", dataMap.get("productPrice").toString() + "元");
          dataMap.put("goodProductPrice", dataMap.get("goodProductPrice").toString() + "元");
        }else if("1".equals(dataMap.get("chargeType").toString())){
          dataMap.put("productPrice", dataMap.get("productPrice").toString() +  "元/月");
          dataMap.put("goodProductPrice", dataMap.get("goodProductPrice").toString() + "元/月");
        }else if("2".equals(dataMap.get("chargeType").toString())){
          dataMap.put("productPrice", dataMap.get("productPrice").toString() +  "元/次");
          dataMap.put("goodProductPrice", dataMap.get("goodProductPrice").toString() + "元/次");
        }
        BaseResponse<String> codeToName2 = paramServiceApi
            .codeToName("charge_type", (String) dataMap.get("chargeType"));
        dataMap.put("chargeType", codeToName2.getData());


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

}
