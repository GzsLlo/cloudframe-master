package com.ai.cloudframe.provider.cmc.controller;


import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.provider.api.sys.model.dto.ChargeOffDto;
import com.ai.cloudframe.provider.api.sys.model.vo.AirQualityVo;
import com.ai.cloudframe.provider.api.sys.model.vo.ChargeOffVo;
import com.ai.cloudframe.provider.api.sys.service.BillDetailFeignServiceApi;
import com.ai.cloudframe.provider.cmc.entity.Bill;
import com.ai.cloudframe.provider.cmc.service.IApiStatisticsService;
import com.ai.cloudframe.provider.cmc.service.IBillDetailService;
import com.ai.cloudframe.provider.cmc.service.IBillService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import com.ai.cloudframe.common.base.support.BaseController;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-17
 */
@RestController
@Api(description = "出账相关接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class BillDetailController extends BaseController implements BillDetailFeignServiceApi {

  @Autowired
  IBillDetailService iBillDetailService;

  @Autowired
  IBillService iBillService;

  @Autowired
  IApiStatisticsService iApiStatisticsService;

  @Override
  public BaseResponse chargeOff(String cycleCode) {
    boolean flag=false;
    //查找该账期所有未出账的明细记录
    List<ChargeOffDto> list=iBillDetailService.getInfoBycycleCode(cycleCode);
    for (ChargeOffDto each:list) {
      Map para = new HashMap();
      para.put("USER_ID",each.getUserId());
      para.put("GOOD_ID",each.getGoodId());
      para.put("PRODUCT_ID",each.getProductId());
      para.put("CYCLE_CODE",each.getCycleCode());
      para.put("BILL_DETAIL_ID",each.getBilldetailId());
      //一次性产品
      if("0".equals(each.getChargeType())){
        //判断该客户该商品的该产品是否已经出过账
        List<ChargeOffDto> list2=iBillDetailService.isAlreadyExit(para);
        if(list2.size()<=0){ //没有出过账
          Bill bill=new Bill();
          BeanUtils.copyProperties(each,bill);
          UUID uuid = UUID.randomUUID();
          String uuidStr = uuid.toString();
          bill.setBillId(uuidStr);
          bill.setProductNum("1");
          Double settlemoney = Double.parseDouble(bill.getGoodProductPrice())*Double.parseDouble(bill.getSettleScale())*Double.parseDouble(bill.getProductNum());
          settlemoney = (double) Math.round(settlemoney * 100) / 100;
          bill.setSettleMoney(settlemoney.toString());
          bill.setSettleDate(LocalDateTime.now());
         flag=iBillService.save(bill);
        }else{//出过
          logger.info("no operate 1!");
        }
        //状态改成已出帐
        int count=iBillDetailService.updatebillStatus(para);
      }else if ("1".equals(each.getChargeType())){//按月收费产品
        //判断该客户该商品的该产品在本月是否已经出过账
        List<ChargeOffDto> list2=iBillDetailService.isAlreadyExitMonth(para);
        if(list2.size()<=0){ //本月没有出过账
          Bill bill=new Bill();
          BeanUtils.copyProperties(each,bill);
          UUID uuid = UUID.randomUUID();
          String uuidStr = uuid.toString();
          bill.setBillId(uuidStr);
          bill.setProductNum("1");
          Double settlemoney = Double.parseDouble(bill.getGoodProductPrice())*Double.parseDouble(bill.getSettleScale())*Double.parseDouble(bill.getProductNum());
          settlemoney = (double) Math.round(settlemoney * 100) / 100;
          bill.setSettleMoney(settlemoney.toString());
          bill.setSettleDate(LocalDateTime.now());
          flag=iBillService.save(bill);
        }else{//出过
          logger.info("no operate 2!");
        }
        //状态改成已出帐
        int count=iBillDetailService.updatebillStatus(para);
      }else{//按使用量收费
        //判断该客户该商品的该产品是不是已经有一部分已经出过账
        List<ChargeOffDto> list2=iBillDetailService.isAlreadyExitByUse(para);
        int num=0;//已经出账的使用量
        int count =0;//截止目前的使用量
       if(list2.size()<=0){
         num=0;
       }else{
         num=Integer.parseInt((list2.get(0).getProductNum()));
       }
       //截止目前的使用量
        List<ChargeOffDto> list3=iApiStatisticsService.getUseCount(para);
       if(list3.size()<=0){//没有使用量
         count =0;
       }else{
         if(list3.get(0)==null){
           count =0;
         }else{
           count=Integer.parseInt((list3.get(0).getUseCount()));
         }
        }
      //没有出账的使用量
        int useCount = count-num;
       if(useCount>0){
         Bill bill=new Bill();
         BeanUtils.copyProperties(each,bill);
         UUID uuid = UUID.randomUUID();
         String uuidStr = uuid.toString();
         bill.setBillId(uuidStr);
         bill.setProductNum(Integer.toString(useCount));
         Double settlemoney = Double.parseDouble(bill.getGoodProductPrice())*Double.parseDouble(bill.getSettleScale())*Double.parseDouble(bill.getProductNum());
         settlemoney = (double) Math.round(settlemoney * 100) / 100;
         bill.setSettleMoney(settlemoney.toString());
         bill.setSettleDate(LocalDateTime.now());
         flag=iBillService.save(bill);
       }else{
         logger.info("no operate 3!");
       }
       //截至出账日期之前的所有数据改成已出帐状态
      int up=iApiStatisticsService.updateIschargeoff(para);
      }
       //不改出账状态，因为可以反复出账
    }
    return BaseResponse.success();
  }




  @Override
  public BaseResponse<Map> getCustBillTable(Map selectParams) {
    //根据订单来查询账单
//    List<ChargeOffVo> tableList=new ArrayList<>();
    Map resultMap = new HashMap();
    // 先找出该客户已经出账的order_id,一次性和月付在明细表，使用量在账单表
    IPage<ChargeOffVo> orderlist=iBillDetailService.getallorderId(selectParams);
    List<ChargeOffVo> list = orderlist.getRecords();
    if(list.size()<=0){
      resultMap.put("records",new ArrayList<>());
      resultMap.put("size",0);
      logger.debug("tableList:{}",new ArrayList<>());
      return BaseResponse.success(resultMap);
    }
    for (ChargeOffVo each:list) {
      //将产品名称，产品单价，产品数量，优惠价格拼接
      StringBuffer productName=new StringBuffer();
      StringBuffer goodproductPrice=new StringBuffer();
      StringBuffer productNum =new StringBuffer();
      StringBuffer salemoney=new StringBuffer();
       Double summony = 0.0;
      Map para = new HashMap();
      para.put("ORDER_ID",each.getOrderId());
      para.put("CYCLE_CODE",selectParams.get("CYCLE_CODE"));
      //该订单的所有信息，逐条显示
      List<ChargeOffVo> list2=iBillDetailService.gettableInfoByorderId(para);
      //
      if(list2.size()<=0){//该订单没有商品，一般不可能
        resultMap.put("records",new ArrayList<>());
        resultMap.put("size",0);
        logger.debug("tableList:{}",new ArrayList<>());
        return BaseResponse.success(resultMap);
      }
      for (ChargeOffVo item:list2) {
        productName.append(item.getProductName()).append("\n");
        goodproductPrice.append(item.getGoodProductPrice());
        productNum.append(item.getProductNum()).append("\n");
        salemoney.append("0").append("元").append("\n");
        if("0".equals(item.getChargeType())){
          goodproductPrice.append("元").append("\n");
        }else  if ("1".equals(item.getChargeType())){
          goodproductPrice.append("元/月").append("\n");
        }else {
          goodproductPrice.append("元/次").append("\n");
        }
        summony = summony + Double.parseDouble(item.getGoodProductPrice())*Double.parseDouble(item.getProductNum());
//        tableList.add(item);
      }
      //计算总价有问题，不能跨月，因为演示只有当月，所以简化了逻辑
      //
      summony = (double) Math.round(summony * 100) / 100;
      each.setSummoney(summony.toString()+"元");
      each.setGoodProductPrice(goodproductPrice.toString());
      each.setProductName(productName.toString());
      each.setProductNum(productNum.toString());
      each.setSalemoney(salemoney.toString());

    }
    resultMap.put("records",orderlist.getRecords());
    resultMap.put("total",orderlist.getTotal());
    resultMap.put("page",Long.valueOf(selectParams.get("page").toString()));
    resultMap.put("size",orderlist.getSize());
    logger.debug("tableList:{}",orderlist.getRecords());
    return BaseResponse.success(resultMap);
  }

  @Override
  public BaseResponse<Map> updateSettleScale(Map selectParams) {
    int count=iBillDetailService.updateSettleScale(selectParams);
    return count == 1?BaseResponse.success():BaseResponse.fail();
  }

  @Override
  public BaseResponse<Map> getMyBillTableOnce(Map selectParams) {
    Map resultMap = new HashMap();
    IPage<ChargeOffVo> billlist=iBillDetailService.getMyBillTableOnce(selectParams);
    resultMap.put("records",billlist.getRecords());
    resultMap.put("total",billlist.getTotal());
    resultMap.put("page",Long.valueOf(selectParams.get("page").toString()));
    resultMap.put("size",billlist.getSize());
    Map<ChargeOffVo,String> map =iBillDetailService.getPartnerBillTableOnceCount(selectParams);
    resultMap.put("countmoney",map.get("COUNTMONEY"));
    logger.debug("billlist:{}",billlist);
    return BaseResponse.success(resultMap);
  }

  @Override
  public BaseResponse<Map> getSelect2Good(Map selectParams) {
    Map resultMap = new HashMap();
    List<ChargeOffVo> selectlist=iBillDetailService.getSelect2Good(selectParams);
    resultMap.put("selectlist",selectlist);
    return BaseResponse.success(resultMap);
  }


  @Override
  public BaseResponse<Map> getPartnerBillTableOnce(Map selectParams) {
    Map resultMap = new HashMap();
    IPage<ChargeOffVo> billlist=iBillDetailService.getPartnerBillTableOnce(selectParams);
    resultMap.put("records",billlist.getRecords());
    resultMap.put("total",billlist.getTotal());
    resultMap.put("page",Long.valueOf(selectParams.get("page").toString()));
    resultMap.put("size",billlist.getSize());
    logger.debug("billlist:{}",billlist);
    return BaseResponse.success(resultMap);
  }


  @Override
  public BaseResponse<Map> getPartnerBillTableByUse(Map selectParams) {
    Map resultMap = new HashMap();
    //一个用户订购了商品，两次出账，
    IPage<ChargeOffVo> billlist=iBillDetailService.getPartnerBillTableByUse(selectParams);
    resultMap.put("records",billlist.getRecords());
    resultMap.put("total",billlist.getTotal());
    resultMap.put("page",Long.valueOf(selectParams.get("page").toString()));
    resultMap.put("size",billlist.getSize());
    logger.debug("billlist:{}",billlist);
    return BaseResponse.success(resultMap);
  }

  @Override
  public BaseResponse<Map> getSettleRuleTable(Map selectParams) {
    Map resultMap = new HashMap();
    IPage<ChargeOffVo> billlist=iBillDetailService.getSettleRuleTable(selectParams);
    resultMap.put("records",billlist.getRecords());
    resultMap.put("total",billlist.getTotal());
    resultMap.put("page",Long.valueOf(selectParams.get("page").toString()));
    resultMap.put("size",billlist.getSize());
    logger.debug("billlist:{}",billlist);
    return BaseResponse.success(resultMap);
  }




}
