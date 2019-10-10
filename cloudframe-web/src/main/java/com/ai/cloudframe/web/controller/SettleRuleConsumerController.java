package com.ai.cloudframe.web.controller;

import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.common.base.support.BaseController;
import com.ai.cloudframe.common.base.util.DateUtil;
import com.ai.cloudframe.common.base.util.Utility;
import com.ai.cloudframe.provider.api.sys.model.dto.GoodProductSettleDto;
import com.ai.cloudframe.provider.api.sys.model.dto.ProductDto;
import com.ai.cloudframe.provider.api.sys.model.vo.GoodsVo;
import com.ai.cloudframe.provider.api.sys.model.vo.ProdIntroProdVo;
import com.ai.cloudframe.provider.api.sys.service.ParamServiceApi;
import com.ai.cloudframe.provider.api.sys.service.ProductServiceApi;
import com.ai.cloudframe.provider.api.sys.service.SettleRuleServiceApi;
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
@Api(description = "匹配表接口")
@CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = "*")
public class SettleRuleConsumerController extends BaseController {


  @Autowired
  ParamServiceApi paramServiceApi;

  @Autowired
  SettleRuleServiceApi settleRuleServiceApi;



  @RequestMapping(value = "/newSettleRule", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation("结算表入表")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "vueJson", value = "提交报文", required = true, dataType = "form")
  })
  public BaseResponse newSettleRule(@RequestBody List<GoodProductSettleDto> GoodProductSettleDtoList, @RequestHeader HttpHeaders headers) {
    logger.info("GoodProductSettleDtoList{}", GoodProductSettleDtoList);

    for (GoodProductSettleDto goodProductSettleDto:GoodProductSettleDtoList) {
      if(goodProductSettleDto.getProductPrice()!= null || goodProductSettleDto.getProductPrice()!= ""){
        String str = goodProductSettleDto.getProductPrice().toString();
        str = str.substring(0,str.indexOf("元"));
        goodProductSettleDto.setProductPrice(str);
      }
      if(goodProductSettleDto.getGoodProductPrice()!= null || goodProductSettleDto.getGoodProductPrice()!= ""){
        String str = goodProductSettleDto.getGoodProductPrice().toString();
        str = str.substring(0,str.indexOf("元"));
        goodProductSettleDto.setGoodProductPrice(str);
      }

    }

    logger.info("GoodProductSettleDtoList {}",GoodProductSettleDtoList);
    BaseResponse baseResponse = settleRuleServiceApi.newSettleRule(GoodProductSettleDtoList);

    logger.info("baseResponse:",baseResponse.getMessage());
    return baseResponse;
  }

}
