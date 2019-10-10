package com.ai.cloudframe.web.controller;

import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.common.base.support.BaseController;
import com.ai.cloudframe.provider.api.sys.model.dto.GoodsDto;
import com.ai.cloudframe.provider.api.sys.model.dto.OrderDto;
import com.ai.cloudframe.provider.api.sys.model.vo.GoodsOrderVo;
import com.ai.cloudframe.provider.api.sys.model.vo.GoodsProductVo;
import com.ai.cloudframe.provider.api.sys.model.vo.GoodsVo;
import com.ai.cloudframe.provider.api.sys.service.BillDetailFeignServiceApi;
import com.ai.cloudframe.provider.api.sys.service.GoodsFeignServiceApi;
import com.ai.cloudframe.provider.api.sys.service.OrderFeignServiceApi;
import com.ai.cloudframe.provider.api.sys.service.ParamServiceApi;
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
import java.util.UUID;

@RestController
@Api(description = "商品订购接口")
@CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = "*")
public class GoodsOrderController extends BaseController {

  @Autowired
  GoodsFeignServiceApi goodsFeignServiceApi;

  @Autowired
  OrderFeignServiceApi orderFeignServiceApi;

  @Autowired
  BillDetailFeignServiceApi  billDetailFeignServiceApi;

  @Autowired
  private ParamServiceApi paramServiceApi;

  @RequestMapping(value = "/goodsOrderSave", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation("商品订购")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "vueJson", value = "提交报文", required = true, dataType = "form")
  })
  public BaseResponse goodsSave(@RequestBody List<OrderDto> orderDtoList, @RequestHeader HttpHeaders headers) {
    logger.info("orderDtoList{}", orderDtoList);
    for (OrderDto each : orderDtoList) {
      UUID uuid = UUID.randomUUID();
      String uuidStr = uuid.toString();
      each.setOrderId(uuidStr);
    }


    BaseResponse baseResponse = orderFeignServiceApi.goodsOrderSave(orderDtoList);
    logger.info("baseResponseOrderSave:", baseResponse);
    if(!"0000".equals(baseResponse.getCode())){
      return baseResponse;
    }
    //插入账单明细表
    BaseResponse baseResponse2 = orderFeignServiceApi.billdetailSave(orderDtoList);
    logger.info("baseResponse2:", baseResponse2.getData());

    return baseResponse2;
  }



  @RequestMapping(value = "/queryTable/goodOrderInfoList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation("订单列表查询")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "vueJson", value = "提交报文", required = true, dataType = "form")
  })
  public BaseResponse goodInfoList(@RequestBody GoodsOrderVo goodsOrderVo, @RequestHeader HttpHeaders headers) {
    logger.info("goodsOrderVo{}", goodsOrderVo);

    BaseResponse<Map> baseResponse = orderFeignServiceApi.orderInfoqueryTable(goodsOrderVo);
    logger.info("baseResponse:", baseResponse.getData());

    for (Map each:(List<Map>)baseResponse.getData().get("records")) {

      if(!StringUtils.isEmpty(each.get("goodId"))) {

        BaseResponse<List<GoodsProductVo>> goodProd = goodsFeignServiceApi.goodProdInfoquery(each.get("goodId").toString());
        List<GoodsProductVo> list=goodProd.getData();

        StringBuffer prdName=new StringBuffer();
        StringBuffer priceStr=new StringBuffer();
        StringBuffer prdType=new StringBuffer();
        StringBuffer salePrice=new StringBuffer();

        for (GoodsProductVo goodsProductVo:list ) {
          String unit="元";
          if("0".equals(goodsProductVo.getChargeType())){
            unit="元/次";
          }else if("1".equals(goodsProductVo.getChargeType())){
            unit="元/月";
          }
          prdName.append(goodsProductVo.getProductName()).append("\n");
          priceStr.append(goodsProductVo.getProductPrice()).append(unit).append("\n");
          salePrice.append(goodsProductVo.getGoodProductPrice()).append(unit).append("\n");

          if(!StringUtils.isEmpty(goodsProductVo.getIntroduceProductType())) {
            BaseResponse<String> codeToName2 = paramServiceApi
                .codeToName("product_type",goodsProductVo.getIntroduceProductType());
            prdType.append( codeToName2.getData()).append("\n");
          }

        }
        each.put("subProNames", prdName.toString().trim());
        each.put("subProPrices", priceStr.toString().trim());
        each.put("subProType",prdType.toString().trim());
        each.put("salePrice",salePrice.toString().trim());
      }

    }

    return baseResponse;
  }

}
