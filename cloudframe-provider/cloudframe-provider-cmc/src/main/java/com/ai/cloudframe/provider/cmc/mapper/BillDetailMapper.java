package com.ai.cloudframe.provider.cmc.mapper;

import com.ai.cloudframe.provider.api.sys.model.dto.ChargeOffDto;
import com.ai.cloudframe.provider.api.sys.model.vo.ChargeOffVo;
import com.ai.cloudframe.provider.cmc.entity.BillDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-17
 */
public interface BillDetailMapper extends BaseMapper<BillDetail> {
  List<ChargeOffDto> getInfoBycycleCode(String cycleCode);

  List<ChargeOffDto> isAlreadyExit(@Param("map") Map map);

  List<ChargeOffDto> isAlreadyExitByUse(@Param("map") Map map);


  List<ChargeOffVo> getSelect2Good(@Param("map") Map map);

  IPage<ChargeOffVo> getallorderId(@Param("pg")Page page, @Param("map") Map map);

  IPage<ChargeOffVo> getPartnerBillTableOnce(@Param("pg")Page page, @Param("map") Map map);

  Map<ChargeOffVo, String> getPartnerBillTableOnceCount(@Param("map") Map map);

  IPage<ChargeOffVo> getMyBillTableOnce(@Param("pg")Page page, @Param("map") Map map);

  IPage<ChargeOffVo> getPartnerBillTableByUse(@Param("pg")Page page, @Param("map") Map map);

  IPage<ChargeOffVo> getSettleRuleTable(@Param("pg")Page page, @Param("map") Map map);


  List<ChargeOffVo> gettableInfoByorderId(@Param("map") Map map);

  List<ChargeOffDto> isAlreadyExitMonth(@Param("map") Map map);

  int updatebillStatus(@Param("map") Map map);


  int updateSettleScale(@Param("map") Map map);
}
