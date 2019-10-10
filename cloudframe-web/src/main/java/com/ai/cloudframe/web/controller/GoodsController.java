package com.ai.cloudframe.web.controller;

import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.common.base.support.BaseController;
import com.ai.cloudframe.provider.api.sys.model.dto.*;
import com.ai.cloudframe.provider.api.sys.model.vo.GoodsProductVo;
import com.ai.cloudframe.provider.api.sys.model.vo.GoodsVo;
import com.ai.cloudframe.provider.api.sys.model.vo.ProdIntroProdVo;
import com.ai.cloudframe.provider.api.sys.service.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@Api(description = "商品表接口")
@CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = "*")
public class GoodsController extends BaseController {

  @Autowired
  private UserFeignServiceApi userFeignServiceApi;

  @Autowired
  private ParamServiceApi paramServiceApi;

  @Autowired
  private ChannelServiceApi channelServiceApi;

  @Autowired
  GoodsFeignServiceApi goodsFeignServiceApi;

  @Autowired
  GoodProductFeignServiceApi goodProductFeignServiceApi;


  @RequestMapping(value = "/goodsAndProdSave", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation("商品及商品关系新建")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "vueJson", value = "提交报文", required = true, dataType = "form")
  })
  public BaseResponse goodsAndProdSave(@RequestBody Map<String, Object> vueJson, @RequestHeader HttpHeaders headers) {
    logger.info("goodsAndProdSaveParam{}", vueJson);

    //产品信息
    String goodFormStr = (String) vueJson.get("newGoodForm");
    GoodsDto goodsDto = JSON.parseObject(goodFormStr,GoodsDto.class);

    UUID uuid = UUID.randomUUID();
    String uuidStr = uuid.toString();
    goodsDto.setGoodId(uuidStr);

    List<GoodsDto> goodsDtoList=new ArrayList<>();
    goodsDtoList.add(goodsDto);

    //插入产品表
    BaseResponse baseResponse = goodsFeignServiceApi.saveGoods(goodsDtoList);
    logger.info("baseResponse:", baseResponse.getData());

    //产品、原子产品关系表
    String jsonStr = (String)vueJson.get("prodTableData");
    List<ProdIntroProdVo> prodTableDataStrList = JSONArray.parseArray(jsonStr,ProdIntroProdVo.class);

    List<ProductGoodDto> productGoodDtoList=new ArrayList<>();

    for (ProdIntroProdVo each:prodTableDataStrList) {
      ProductGoodDto productGoodDto = new ProductGoodDto();
      productGoodDto.setGoodId(uuidStr);
      productGoodDto.setProductId(each.getProductId());
      productGoodDto.setStartDate(goodsDto.getStartDate());
      productGoodDto.setEndDate(goodsDto.getEndDate());
      productGoodDto.setGoodProductPrice(each.getProductSalePrice());

      productGoodDtoList.add(productGoodDto);
    }
    //插入产品、原子产品关系表
    BaseResponse baseResponse2 = goodProductFeignServiceApi.saveGoodsProduct(productGoodDtoList);
    return baseResponse2;
  }

  @RequestMapping(value = "/goodsSave", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation("商品新建")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "vueJson", value = "提交报文", required = true, dataType = "form")
  })
  public BaseResponse goodsSave(@RequestBody List<GoodsDto> goodsDtoList, @RequestHeader HttpHeaders headers) {
    logger.info("goodsDtoList{}", goodsDtoList);

    BaseResponse baseResponse = goodsFeignServiceApi.saveGoods(goodsDtoList);
    logger.info("baseResponse:", baseResponse.getData());

    return baseResponse;
  }


  @RequestMapping(value = "/goodsProductSave", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation("商品产品关系新建")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "vueJson", value = "提交报文", required = true, dataType = "form")
  })
  public BaseResponse goodsProductSave(@RequestBody List<ProductGoodDto> productGoodDtoList, @RequestHeader HttpHeaders headers) {
    logger.info("productGoodDtoList{}", productGoodDtoList);

    BaseResponse baseResponse = goodProductFeignServiceApi.saveGoodsProduct(productGoodDtoList);
    logger.info("baseResponse:", baseResponse.getData());

    return baseResponse;
  }

  @RequestMapping(value = "/goodsStatusUpdate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation("商品审核修改")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "vueJson", value = "提交报文", required = true, dataType = "form")
  })
  public BaseResponse goodsStatusUpdate(@RequestBody GoodsDto goodsDto, @RequestHeader HttpHeaders headers) {
    logger.info("goodsDto{}", goodsDto);
    goodsDto.setExamineDate(LocalDateTime.now());

    BaseResponse baseResponse = goodsFeignServiceApi.goodsStatusUpdate(goodsDto);
    logger.info("baseResponse:", baseResponse.getData());

    return baseResponse;
  }

  @RequestMapping(value = "/goodsUnderUpdate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation("商品下架修改")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "vueJson", value = "提交报文", required = true, dataType = "form")
  })
  public BaseResponse goodsUnderUpdate(@RequestBody GoodsDto goodsDto, @RequestHeader HttpHeaders headers) {
    logger.info("goodsDto{}", goodsDto);
    goodsDto.setUnderDate(LocalDateTime.now());

    BaseResponse baseResponse = goodsFeignServiceApi.goodsUnderUpdate(goodsDto);
    logger.info("baseResponse:", baseResponse.getData());

    return baseResponse;
  }


  @RequestMapping(value = "/goodsReleaseUpdate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation("商品发布修改")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "vueJson", value = "提交报文", required = true, dataType = "form")
  })
  public BaseResponse goodsReleaseUpdate(@RequestBody GoodsDto goodsDto, @RequestHeader HttpHeaders headers) {
    logger.info("goodsDto{}", goodsDto);
    goodsDto.setReleaseDate(LocalDateTime.now());

    BaseResponse baseResponse = goodsFeignServiceApi.goodsReleaseUpdate(goodsDto);
    logger.info("baseResponse:", baseResponse.getData());

    return baseResponse;
  }

  @RequestMapping(value = "/queryTable/goodInfoList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation("商品列表查询")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "vueJson", value = "提交报文", required = true, dataType = "form")
  })
  public BaseResponse goodInfoList(@RequestBody GoodsVo goodsVo, @RequestHeader HttpHeaders headers) {
    logger.info("goodsVo{}", goodsVo);

    BaseResponse<Map> baseResponse = goodsFeignServiceApi.goodInfoqueryTable(goodsVo);
    logger.info("baseResponse:", baseResponse.getData());

    for (Map each:(List<Map>)baseResponse.getData().get("records")) {

      if(!StringUtils.isEmpty(each.get("goodType"))) {
        BaseResponse<String> codeToName2 = paramServiceApi
            .codeToName("good_type",each.get("goodType").toString());
       each.put("goodTypeName", codeToName2.getData());
      }

      if(!StringUtils.isEmpty(each.get("examineStatus"))) {
        BaseResponse<String> codeToName2 = paramServiceApi
            .codeToName("examineStatus",each.get("examineStatus").toString());
        each.put("examineStatusName", codeToName2.getData());
      }

      if(!StringUtils.isEmpty(each.get("isExperienceProduct"))) {
        BaseResponse<String> codeToName2 = paramServiceApi
            .codeToName("if_status",each.get("isExperienceProduct").toString());
        each.put("isExperienceProductName", codeToName2.getData());
      }

      if(!StringUtils.isEmpty(each.get("channelId"))) {
        StringBuffer chName=new StringBuffer();

        String[] channelIds=each.get("channelId").toString().split(",");

        for (String channelId:channelIds) {
          ChannelDto param = new ChannelDto();
          param.setChannelId(channelId);
          BaseResponse channelToName = channelServiceApi.channelByChanId(param);
          chName.append(channelToName.getData()).append("\n");
        }

        each.put("channelName", chName.toString().trim());
      }

      if(!StringUtils.isEmpty(each.get("goodId"))) {

        BaseResponse<List<GoodsProductVo>> goodProd = goodsFeignServiceApi.goodProdInfoquery(each.get("goodId").toString());
        List<GoodsProductVo> list=goodProd.getData();

        StringBuffer prdName=new StringBuffer();
        StringBuffer priceStr=new StringBuffer();

        for (GoodsProductVo goodsProductVo:list ) {
          String unit="元";
          if("0".equals(goodsProductVo.getChargeType())){
            unit="元/次";
          }else if("1".equals(goodsProductVo.getChargeType())){
            unit="元/月";
          }

          prdName.append(goodsProductVo.getProductName()).append("\n");
          priceStr.append(goodsProductVo.getGoodProductPrice()).append(unit).append("\n");
        }

        each.put("subProNames", prdName.toString().trim());
        each.put("subProPrices", priceStr.toString().trim());
      }

    }

    return baseResponse;
  }


  @RequestMapping(value = "/queryGoodDetail", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation("商品详情查询")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "vueJson", value = "提交报文", required = true, dataType = "form")
  })
  public BaseResponse queryGoodDetail(@RequestBody GoodsVo goodsVo, @RequestHeader HttpHeaders headers) {
    logger.info("goodsVo{}", goodsVo);

    BaseResponse<Map> baseResponse = goodsFeignServiceApi.goodInfoqueryTable(goodsVo);
    logger.info("baseResponse:", baseResponse.getData());

    for (Map each:(List<Map>)baseResponse.getData().get("records")) {

      if(!StringUtils.isEmpty(each.get("goodType"))) {
        BaseResponse<String> codeToName2 = paramServiceApi
            .codeToName("good_type",each.get("goodType").toString());
        each.put("goodTypeName", codeToName2.getData());
      }

      if(!StringUtils.isEmpty(each.get("orderType"))) {
        BaseResponse<String> codeToName2 = paramServiceApi
            .codeToName("order_type",each.get("orderType").toString());
        each.put("orderTypeName", codeToName2.getData());
      }

      if(!StringUtils.isEmpty(each.get("examineStatus"))) {
        BaseResponse<String> codeToName2 = paramServiceApi
            .codeToName("examineStatus",each.get("examineStatus").toString());
        each.put("examineStatusName", codeToName2.getData());
      }

      if(!StringUtils.isEmpty(each.get("isExperienceProduct"))) {
        BaseResponse<String> codeToName2 = paramServiceApi
            .codeToName("if_status",each.get("isExperienceProduct").toString());
        each.put("isExperienceProductName", codeToName2.getData());
      }

      if(!StringUtils.isEmpty(each.get("createUserId"))) {
        BaseResponse<UserDto> codeToName2 = userFeignServiceApi.getUserNameById(each.get("createUserId").toString());
        if( codeToName2.getData() !=null) {
          each.put("createUserName", codeToName2.getData().getNickname());
        }
      }

      if(!StringUtils.isEmpty(each.get("examineUserId"))) {
        BaseResponse<UserDto> codeToName2 = userFeignServiceApi.getUserNameById(each.get("examineUserId").toString());
        if(codeToName2.getData()!=null) {
          each.put("examineUserName", codeToName2.getData().getNickname());
        }
      }

      if(!StringUtils.isEmpty(each.get("goodId"))) {

        BaseResponse<List<GoodsProductVo>> goodProd = goodsFeignServiceApi.goodProdInfoquery(each.get("goodId").toString());
        List<GoodsProductVo> list=goodProd.getData();

        for (GoodsProductVo gpvo:list) {
          if(!StringUtils.isEmpty(gpvo.getChargeType())) {
            BaseResponse<String> codeToName2 = paramServiceApi
                .codeToName("charge_type",gpvo.getChargeType());
            gpvo.setChargeName(codeToName2.getData());

            String unit="元";
            if("0".equals(gpvo.getChargeType())){
              unit="元/次";
            }else if("1".equals(gpvo.getChargeType())){
              unit="元/月";
            }

            gpvo.setProductPrice(gpvo.getProductPrice()+unit);
            gpvo.setGoodProductPrice(gpvo.getGoodProductPrice()+unit);
          }
        }

        each.put("subProList", list);
      }
    }

    return baseResponse;
  }

}
