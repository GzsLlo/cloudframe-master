package com.ai.cloudframe.provider.api.sys.service;


import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.provider.api.sys.model.dto.OrderDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@FeignClient(value = "${cloudframe.microservice.cmc}")
@Component
public interface BillDetailFeignServiceApi {
  @PostMapping(value = "/chargeOff",consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  BaseResponse chargeOff(@RequestBody String cycleCode);

  @PostMapping(value = "/queryTable/getbCustBillTable/")
  @ResponseBody
  BaseResponse<Map> getCustBillTable(@RequestBody Map selectParams);

  @PostMapping(value = "/updateSettleScale")
  @ResponseBody
  BaseResponse<Map> updateSettleScale(@RequestBody Map selectParams);


  @PostMapping(value = "/queryTable/getMyBillTableOnce/")
  @ResponseBody
  BaseResponse<Map> getMyBillTableOnce(@RequestBody Map selectParams);

  @PostMapping(value = "/getSelect2Good/")
  @ResponseBody
  BaseResponse<Map> getSelect2Good(@RequestBody Map selectParams);


  @PostMapping(value = "/queryTable/getPartnerBillTableOnce/")
  @ResponseBody
  BaseResponse<Map> getPartnerBillTableOnce(@RequestBody Map selectParams);


  @PostMapping(value = "/queryTable/getPartnerBillTableByUse/")
  @ResponseBody
  BaseResponse<Map> getPartnerBillTableByUse(@RequestBody Map selectParams);

  @PostMapping(value = "/queryTable/getSettleRuleTable/")
  @ResponseBody
  BaseResponse<Map> getSettleRuleTable(@RequestBody Map selectParams);


}
