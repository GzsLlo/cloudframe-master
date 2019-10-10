package com.ai.cloudframe.web.controller;

import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.common.base.support.BaseController;
import com.ai.cloudframe.common.base.util.Utility;
import com.ai.cloudframe.provider.api.sys.model.dto.ChargeOffDto;
import com.ai.cloudframe.provider.api.sys.model.dto.OrderDto;
import com.ai.cloudframe.provider.api.sys.service.BillDetailFeignServiceApi;
import com.ai.cloudframe.provider.api.sys.service.OrderFeignServiceApi;
import com.ai.cloudframe.provider.api.sys.service.ParamServiceApi;
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

import java.util.List;
import java.util.Map;

@RestController
@Api(description = "出账接口")
@CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = "*")
public class ChargeOffController extends BaseController {

  @Autowired
  OrderFeignServiceApi orderFeignServiceApi;

  @Autowired
  BillDetailFeignServiceApi billDetailFeignServiceApi;


  @Autowired
  ParamServiceApi paramServiceApi;

  @RequestMapping(value = "/chargeOff", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation("出账")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "vueJson", value = "提交报文", required = true, dataType = "form")
  })
  public BaseResponse goodsSave(@RequestBody Map<String, Object> vueJson, @RequestHeader HttpHeaders headers) {
    logger.info("cycle_code{}", vueJson);
    String cycleCode="";
    if(vueJson.containsKey("cycleCode")){
      cycleCode=vueJson.get("cycleCode").toString();
    }
    BaseResponse baseResponse = billDetailFeignServiceApi.chargeOff(cycleCode);
    logger.info("baseResponse:", baseResponse.getData());
    return baseResponse;
  }



  @RequestMapping(value = "/updateSettleScale", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation("出账")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "vueJson", value = "提交报文", required = true, dataType = "form")
  })
  public BaseResponse updateSettleScale(@RequestBody Map<String, Object> vueJson, @RequestHeader HttpHeaders headers) {
    logger.info("cycle_code{}", vueJson);
    BaseResponse baseResponse = billDetailFeignServiceApi.updateSettleScale(vueJson);
    logger.info("baseResponse:", baseResponse.getData());
    return baseResponse;
  }





/*
客户账单列表查询
 */
  @RequestMapping(value = "/queryTable/getbCustBillTable", method = RequestMethod.POST ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  @ApiOperation("客户账单列表")
  @ApiImplicitParams({
      @ApiImplicitParam(name="vueJson",value="提交报文",required=true,paramType="form")
  })
  public BaseResponse getbCustBillTable(@RequestParam Map<String, Object> vueJson,@RequestHeader HttpHeaders headers){
    //传参：账期和客户
    logger.info("login.param:{}", vueJson);
    BaseResponse<Map> baseResponse = billDetailFeignServiceApi.getCustBillTable(vueJson);
    logger.info("baseResponse:",baseResponse);
    Map resultMap = baseResponse.getData();
    BaseResponse response = BaseResponse.success(resultMap);
    return response;
  }


  /*
 合作伙伴收入一次性列表查询
 */
  @RequestMapping(value = "/queryTable/getMyBillTableOnce", method = RequestMethod.POST ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  @ApiOperation("合作伙伴收入一次性列表")
  @ApiImplicitParams({
      @ApiImplicitParam(name="vueJson",value="提交报文",required=true,paramType="form")
  })
  public BaseResponse getMyBillTableOnce(@RequestParam Map<String, Object> vueJson,@RequestHeader HttpHeaders headers){
    //传参：账期和客户
    logger.info("login.param:{}", vueJson);
    BaseResponse<Map> baseResponse = billDetailFeignServiceApi.getMyBillTableOnce(vueJson);
    logger.info("baseResponse:",baseResponse);
    for (Map each:(List<Map>)baseResponse.getData().get("records")) {
      if (!StringUtils.isEmpty(each.get("chargeType"))) {
        if("0".equals(each.get("chargeType"))){
          each.put("productPrice", each.get("productPrice")+"元");
          each.put("goodProductPrice", each.get("goodProductPrice")+"元");
        }else if("1".equals(each.get("chargeType"))){
          each.put("productPrice", each.get("productPrice")+"元/月");
          each.put("goodProductPrice", each.get("goodProductPrice")+"元/月");
        }else{
          each.put("productPrice", each.get("productPrice")+"元/次");
          each.put("goodProductPrice", each.get("goodProductPrice")+"元/次");
        }
      }
      if(!StringUtils.isEmpty(each.get("settleScale"))){
        Double scale = Double.parseDouble(each.get("settleScale").toString())*100;
        scale = (double) Math.round(scale * 100) / 100;
        each.put("settleScaleName", String.valueOf(scale)+"%");
      }
      if(!StringUtils.isEmpty(each.get("settleMoney"))){
        each.put("settleMoney",each.get("settleMoney")+"元");
      }
    }
    Map resultMap = baseResponse.getData();
    BaseResponse response = BaseResponse.success(resultMap);
    return response;
  }


 /*
 合作伙伴收入月收列表查询
 */
  @RequestMapping(value = "/queryTable/getMyBillTableMonth", method = RequestMethod.POST ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  @ApiOperation("合作伙伴收入月收列表")
  @ApiImplicitParams({
      @ApiImplicitParam(name="vueJson",value="提交报文",required=true,paramType="form")
  })
  public BaseResponse getMyBillTableMonth(@RequestParam Map<String, Object> vueJson,@RequestHeader HttpHeaders headers){
    //传参：账期和客户
    logger.info("login.param:{}", vueJson);
    BaseResponse<Map> baseResponse = billDetailFeignServiceApi.getMyBillTableOnce(vueJson);
    logger.info("baseResponse:",baseResponse);
    for (Map each:(List<Map>)baseResponse.getData().get("records")) {
      if (!StringUtils.isEmpty(each.get("chargeType"))) {
        if("0".equals(each.get("chargeType"))){
          each.put("productPrice", each.get("productPrice")+"元");
          each.put("goodProductPrice", each.get("goodProductPrice")+"元");
        }else if("1".equals(each.get("chargeType"))){
          each.put("productPrice", each.get("productPrice")+"元/月");
          each.put("goodProductPrice", each.get("goodProductPrice")+"元/月");
        }else{
          each.put("productPrice", each.get("productPrice")+"元/次");
          each.put("goodProductPrice", each.get("goodProductPrice")+"元/次");
        }
      }
      if(!StringUtils.isEmpty(each.get("settleScale"))){
        Double scale = Double.parseDouble(each.get("settleScale").toString())*100;
        scale = (double) Math.round(scale * 100) / 100;
        each.put("settleScaleName", String.valueOf(scale)+"%");
      }
      if(!StringUtils.isEmpty(each.get("settleMoney"))){
        each.put("settleMoney",each.get("settleMoney")+"元");
      }
    }
    Map resultMap = baseResponse.getData();
    BaseResponse response = BaseResponse.success(resultMap);
    return response;
  }



  /*
合作伙伴收入使用量列表查询
*/
  @RequestMapping(value = "/queryTable/getMyBillTableByUse", method = RequestMethod.POST ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  @ApiOperation("合作伙伴收入使用量列表")
  @ApiImplicitParams({
      @ApiImplicitParam(name="vueJson",value="提交报文",required=true,paramType="form")
  })
  public BaseResponse getMyBillTableByUse(@RequestParam Map<String, Object> vueJson,@RequestHeader HttpHeaders headers){
    //传参：账期和客户
    logger.info("login.param:{}", vueJson);
    BaseResponse<Map> baseResponse = billDetailFeignServiceApi.getMyBillTableOnce(vueJson);
    logger.info("baseResponse:",baseResponse);
    for (Map each:(List<Map>)baseResponse.getData().get("records")) {
      if (!StringUtils.isEmpty(each.get("chargeType"))) {
        if("0".equals(each.get("chargeType"))){
          each.put("productPrice", each.get("productPrice")+"元");
          each.put("goodProductPrice", each.get("goodProductPrice")+"元");
        }else if("1".equals(each.get("chargeType"))){
          each.put("productPrice", each.get("productPrice")+"元/月");
          each.put("goodProductPrice", each.get("goodProductPrice")+"元/月");
        }else{
          each.put("productPrice", each.get("productPrice")+"元/次");
          each.put("goodProductPrice", each.get("goodProductPrice")+"元/次");
        }
      }
      if(!StringUtils.isEmpty(each.get("settleScale"))){
        Double scale = Double.parseDouble(each.get("settleScale").toString())*100;
        scale = (double) Math.round(scale * 100) / 100;
        each.put("settleScaleName", String.valueOf(scale)+"%");
      }
      if(!StringUtils.isEmpty(each.get("settleMoney"))){
        each.put("settleMoney",each.get("settleMoney")+"元");
      }
    }
    Map resultMap = baseResponse.getData();
    BaseResponse response = BaseResponse.success(resultMap);
    return response;
  }

  /*
合作伙伴收入使用量列表查询
*/
  @RequestMapping(value = "/getSelect2Good", method = RequestMethod.POST ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  @ApiOperation("下拉框搜索商品")
  @ApiImplicitParams({
      @ApiImplicitParam(name="vueJson",value="提交报文",required=true,paramType="form")
  })
  public BaseResponse getSelect2Good(@RequestParam Map<String, Object> vueJson,@RequestHeader HttpHeaders headers){
    //传参：账期和客户
    logger.info("login.param:{}", vueJson);
    BaseResponse<Map> baseResponse = billDetailFeignServiceApi.getSelect2Good(vueJson);
    logger.info("baseResponse:",baseResponse);
    return baseResponse;
  }


  /*
  合作伙伴一次性账单列表查询
   */
  @RequestMapping(value = "/queryTable/getPartnerBillTableOnce", method = RequestMethod.POST ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  @ApiOperation("合作伙伴一次性账单列表")
  @ApiImplicitParams({
      @ApiImplicitParam(name="vueJson",value="提交报文",required=true,paramType="form")
  })
  public BaseResponse getPartnerBillTableOnce(@RequestParam Map<String, Object> vueJson,@RequestHeader HttpHeaders headers){
    //传参：账期和商品名称产品名称
    logger.info("login.param:{}", vueJson);
    BaseResponse<Map> baseResponse = billDetailFeignServiceApi.getPartnerBillTableOnce(vueJson);
    logger.info("baseResponse:",baseResponse);
    for (Map each:(List<Map>)baseResponse.getData().get("records")) {
      if (!StringUtils.isEmpty(each.get("chargeType"))) {
        if("0".equals(each.get("chargeType"))){
          each.put("productPrice", each.get("productPrice")+"元");
          each.put("goodProductPrice", each.get("goodProductPrice")+"元");
        }else if("1".equals(each.get("chargeType"))){
          each.put("productPrice", each.get("productPrice")+"元/月");
          each.put("goodProductPrice", each.get("goodProductPrice")+"元/月");
        }else{
          each.put("productPrice", each.get("productPrice")+"元/次");
          each.put("goodProductPrice", each.get("goodProductPrice")+"元/次");
        }
      }
      if(!StringUtils.isEmpty(each.get("settleScale"))){
        Double scale = Double.parseDouble(each.get("settleScale").toString())*100;
        scale = (double) Math.round(scale * 100) / 100;
        each.put("settleScaleName", String.valueOf(scale)+"%");
      }
      if(!StringUtils.isEmpty(each.get("settleMoney"))){
        each.put("settleMoney",each.get("settleMoney")+"元");
      }
    }



    Map resultMap = baseResponse.getData();
    BaseResponse response = BaseResponse.success(resultMap);
    return response;
  }

  /*
   合作伙伴按月收费账单列表查询
    */
  @RequestMapping(value = "/queryTable/getPartnerBillTableMonth", method = RequestMethod.POST ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  @ApiOperation("合作伙伴按月收费账单列表")
  @ApiImplicitParams({
      @ApiImplicitParam(name="vueJson",value="提交报文",required=true,paramType="form")
  })
  public BaseResponse getPartnerBillTableMonth(@RequestParam Map<String, Object> vueJson,@RequestHeader HttpHeaders headers){
    //传参：账期和商品名称产品名称
    logger.info("login.param:{}", vueJson);
    JSONObject info = Utility.convert2JsonAndCheckRequiredColumns(vueJson, "");
    Map selectParams = (Map)info;
    BaseResponse<Map> baseResponse = billDetailFeignServiceApi.getPartnerBillTableOnce(selectParams);
    logger.info("baseResponse:",baseResponse);
    for (Map each:(List<Map>)baseResponse.getData().get("records")) {
      if (!StringUtils.isEmpty(each.get("chargeType"))) {
        if("0".equals(each.get("chargeType"))){
          each.put("productPrice", each.get("productPrice")+"元");
          each.put("goodProductPrice", each.get("goodProductPrice")+"元");
        }else if("1".equals(each.get("chargeType"))){
          each.put("productPrice", each.get("productPrice")+"元/月");
          each.put("goodProductPrice", each.get("goodProductPrice")+"元/月");
        }else{
          each.put("productPrice", each.get("productPrice")+"元/次");
          each.put("goodProductPrice", each.get("goodProductPrice")+"元/次");
        }
      }
      if(!StringUtils.isEmpty(each.get("settleScale"))){
        Double scale = Double.parseDouble(each.get("settleScale").toString())*100;
        scale = (double) Math.round(scale * 100) / 100;
        each.put("settleScaleName", String.valueOf(scale)+"%");
      }
      if(!StringUtils.isEmpty(each.get("settleMoney"))){
        each.put("settleMoney",each.get("settleMoney")+"元");
      }
    }
    Map resultMap = baseResponse.getData();
    BaseResponse response = BaseResponse.success(resultMap);
    return response;
  }

  /*
   合作伙伴按使用次数收费账单列表查询
    */
  @RequestMapping(value = "/queryTable/getPartnerBillTableByUse", method = RequestMethod.POST ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  @ApiOperation("合作伙伴按使用次数收费账单列表")
  @ApiImplicitParams({
      @ApiImplicitParam(name="vueJson",value="提交报文",required=true,paramType="form")
  })
  public BaseResponse getPartnerBillTableByUse(@RequestParam Map<String, Object> vueJson,@RequestHeader HttpHeaders headers){
    //传参：账期和商品名称产品名称
    logger.info("login.param:{}", vueJson);
    BaseResponse<Map> baseResponse = billDetailFeignServiceApi.getPartnerBillTableByUse(vueJson);
    for (Map each:(List<Map>)baseResponse.getData().get("records")) {
      if (!StringUtils.isEmpty(each.get("chargeType"))) {
        if("0".equals(each.get("chargeType"))){
          each.put("goodProductPrice", each.get("goodProductPrice")+"元");
        }else if("1".equals(each.get("chargeType"))){
          each.put("goodProductPrice", each.get("goodProductPrice")+"元/月");
        }else{
          each.put("goodProductPrice", each.get("goodProductPrice")+"元/次");
        }
      }
    }
    logger.info("baseResponse:",baseResponse);
    Map resultMap = baseResponse.getData();
    BaseResponse response = BaseResponse.success(resultMap);
    return response;
  }


  /*
   结算配置列表查询
    */
  @RequestMapping(value = "/queryTable/getSettleRuleTable", method = RequestMethod.POST ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  @ApiOperation("结算配置列表")
  @ApiImplicitParams({
      @ApiImplicitParam(name="vueJson",value="提交报文",required=true,paramType="form")
  })
  public BaseResponse getSettleRuleTable(@RequestParam Map<String, Object> vueJson,@RequestHeader HttpHeaders headers){
    //传参：账期和商品名称产品名称
    logger.info("login.param:{}", vueJson);
    BaseResponse<Map> baseResponse = billDetailFeignServiceApi.getSettleRuleTable(vueJson);
    logger.info("baseResponse:",baseResponse);

    for (Map each:(List<Map>)baseResponse.getData().get("records")) {
      if (!StringUtils.isEmpty(each.get("goodType"))) {
        BaseResponse<String> codeToName2 = paramServiceApi
            .codeToName("good_type", each.get("goodType").toString());
        each.put("goodTypeName", codeToName2.getData());
      }
        if (!StringUtils.isEmpty(each.get("chargeType"))) {
          if("0".equals(each.get("chargeType"))){
            each.put("productPrice", each.get("productPrice")+"元");
            each.put("goodProductPrice", each.get("goodProductPrice")+"元");
          }else if("1".equals(each.get("chargeType"))){
            each.put("productPrice", each.get("productPrice")+"元/月");
            each.put("goodProductPrice", each.get("goodProductPrice")+"元/月");
          }else{
            each.put("productPrice", each.get("productPrice")+"元/次");
            each.put("goodProductPrice", each.get("goodProductPrice")+"元/次");
          }
          BaseResponse<String> codeToName2 = paramServiceApi
              .codeToName("charge_type", each.get("chargeType").toString());
          each.put("chargeTypeName", codeToName2.getData());
        }
        if(!StringUtils.isEmpty(each.get("settlerCycle"))){
          BaseResponse<String> codeToName2 = paramServiceApi
              .codeToName("settlerCycle", each.get("settlerCycle").toString());
          each.put("settlerCycleName", codeToName2.getData());
        }
      if(!StringUtils.isEmpty(each.get("settleScale"))){
      Double scale = Double.parseDouble(each.get("settleScale").toString())*100;
        scale = (double) Math.round(scale * 100) / 100;
        each.put("settleScaleName", String.valueOf(scale)+"%");
      }

    }
    Map resultMap = baseResponse.getData();
    BaseResponse response = BaseResponse.success(resultMap);
    return response;
  }




}
