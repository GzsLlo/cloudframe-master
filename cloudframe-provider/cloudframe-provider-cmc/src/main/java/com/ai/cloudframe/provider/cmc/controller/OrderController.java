package com.ai.cloudframe.provider.cmc.controller;


import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.provider.api.sys.model.dto.OrderDto;
import com.ai.cloudframe.provider.api.sys.model.vo.GoodsOrderVo;
import com.ai.cloudframe.provider.api.sys.service.OrderFeignServiceApi;
import com.ai.cloudframe.provider.cmc.entity.BillDetail;
import com.ai.cloudframe.provider.cmc.entity.Order;
import com.ai.cloudframe.provider.cmc.service.IBillDetailService;
import com.ai.cloudframe.provider.cmc.service.IOrderService;
import com.ai.cloudframe.provider.cmc.service.IProductGoodService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import com.ai.cloudframe.common.base.support.BaseController;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-17
 */
@RestController
@Api(description = "商品订购相关接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class OrderController extends BaseController implements OrderFeignServiceApi {

  @Autowired
  IOrderService iOrderService;

  @Autowired
  IProductGoodService iProductGoodService;

  @Autowired
  IBillDetailService  iBillDetailService;

  @Override
  public BaseResponse goodsOrderSave(List<OrderDto> orderDtoList) {
    boolean flag;

    List<Order> orderList = new ArrayList<>();
    for (OrderDto each : orderDtoList) {
      QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
      orderQueryWrapper.eq("GOOD_ID",each.getGoodId()).eq("USER_ID",each.getUserId());
      List<Order> orderQuery = iOrderService.list(orderQueryWrapper);
      if(orderQuery.size()!=0){
       return BaseResponse.fail(each,"当前用户已订购该产品");
      }

      Order order = new Order();
      BeanUtils.copyProperties(each, order);
      order.setOrderDate(LocalDateTime.now());
      orderList.add(order);
    }
    flag = iOrderService.saveBatch(orderList);
    return flag == true ? BaseResponse.success() : BaseResponse.fail();
  }

  /**
   * <p>
   * 插入账单明细表
   * </p>
   *
   * @author lanmy
   * @since 2019-07-19
   */
  @Override
  public BaseResponse billdetailSave(List<OrderDto> orderDtoList){
    //查询先查
    boolean flag = false;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
    List<BillDetail> BillDetail=new ArrayList<>();
    for (OrderDto each:orderDtoList) {
      BillDetail billDetail=new BillDetail();
      LocalDateTime orderdate = each.getOrderDate();
      BeanUtils.copyProperties(each,billDetail);
      List<OrderDto>  list1=iProductGoodService.getInfoByGoodId(billDetail.getGoodId());
      for (OrderDto each1:list1) {
        BillDetail billDetail2=new BillDetail();
        BeanUtils.copyProperties(each1,billDetail2);

        String dateString = formatter.format(new Date());
        billDetail2.setCycleCode(dateString.substring(0,6));
        billDetail2.setBillStatus("0");//未出账
        billDetail2.setCreateDate(LocalDateTime.now());
        UUID uuid = UUID.randomUUID();
        String uuidStr = uuid.toString();
        billDetail2.setBilldetailId(uuidStr);
        billDetail2.setBillId("");
        billDetail2.setUserId(each.getUserId());
        billDetail2.setOrderId(each.getOrderId());
        BillDetail.add(billDetail2);
      }
      flag=iBillDetailService.saveBatch(BillDetail);
    }
    return flag==true?BaseResponse.success():BaseResponse.fail();
  }

  @Override
  public BaseResponse orderInfoqueryTable(GoodsOrderVo GoodsOrderVo) {
    IPage<GoodsOrderVo> goodsVoIPage = iOrderService.orderInfoqueryTable(GoodsOrderVo);
    logger.debug("goodsVoIPage: {}", goodsVoIPage);
    Map resultMap = new HashMap();
    resultMap.put("records", goodsVoIPage.getRecords());
    resultMap.put("total", goodsVoIPage.getTotal());
    resultMap.put("page", (null != GoodsOrderVo.getPage()) ? Long.valueOf(GoodsOrderVo.getPage()) : null);
    resultMap.put("size", goodsVoIPage.getSize());
    return BaseResponse.success(resultMap);
  }

}
