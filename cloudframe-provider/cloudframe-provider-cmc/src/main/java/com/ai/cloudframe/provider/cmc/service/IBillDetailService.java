package com.ai.cloudframe.provider.cmc.service;

import com.ai.cloudframe.provider.api.sys.model.dto.ChargeOffDto;
import com.ai.cloudframe.provider.api.sys.model.vo.ChargeOffVo;
import com.ai.cloudframe.provider.cmc.entity.BillDetail;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-17
 */
public interface IBillDetailService extends IService<BillDetail> {

  List<ChargeOffDto> getInfoBycycleCode(String cycleCode);

  List<ChargeOffDto>  isAlreadyExit( Map map);

  List<ChargeOffDto>  isAlreadyExitByUse( Map map);


  IPage<ChargeOffVo> getallorderId( Map map);

  IPage<ChargeOffVo> getPartnerBillTableOnce(  Map map);

  Map<ChargeOffVo,String> getPartnerBillTableOnceCount(  Map map);

  List<ChargeOffVo> getSelect2Good (  Map map);

  IPage<ChargeOffVo> getMyBillTableOnce(  Map map);


  IPage<ChargeOffVo> getPartnerBillTableByUse( Map map);


  IPage<ChargeOffVo> getSettleRuleTable( Map map);

  List<ChargeOffVo>  gettableInfoByorderId( Map map);

  List<ChargeOffDto>  isAlreadyExitMonth( Map map);

 int updatebillStatus( Map map);

  int updateSettleScale( Map map);
}
