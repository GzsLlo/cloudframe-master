package com.ai.cloudframe.provider.cmc.service.ipml;

import com.ai.cloudframe.provider.api.sys.model.dto.ChargeOffDto;
import com.ai.cloudframe.provider.api.sys.model.vo.ChargeOffVo;
import com.ai.cloudframe.provider.cmc.entity.BillDetail;
import com.ai.cloudframe.provider.cmc.mapper.BillDetailMapper;
import com.ai.cloudframe.provider.cmc.service.IBillDetailService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-17
 */
@Service
public class BillDetailServiceImpl extends ServiceImpl<BillDetailMapper, BillDetail> implements IBillDetailService {

  @Override
  public List<ChargeOffDto> getInfoBycycleCode(String cycleCode) {
    return this.baseMapper.getInfoBycycleCode(cycleCode);
  }


  @Override
  public List<ChargeOffDto> isAlreadyExit(Map map) {
    return this.baseMapper.isAlreadyExit(map);
  }


  @Override
  public List<ChargeOffDto> isAlreadyExitByUse(Map map) {
    return this.baseMapper.isAlreadyExitByUse(map);
  }

  @Override
  public IPage<ChargeOffVo> getallorderId(Map map) {
    Long pageNum = Long.valueOf(map.get("page").toString());
    Long pageSize = Long.valueOf( map.get("pageSize").toString());
    Page<ChargeOffDto> page = new Page<>(pageNum, pageSize);
    return this.baseMapper.getallorderId(page,map);
  }


  @Override
  public IPage<ChargeOffVo> getMyBillTableOnce(Map map) {
    Long pageNum = Long.valueOf(map.get("page").toString());
    Long pageSize = Long.valueOf( map.get("pageSize").toString());
    Page<ChargeOffVo> page = new Page<>(pageNum, pageSize);
    return this.baseMapper.getMyBillTableOnce(page,map);
  }





  @Override
  public IPage<ChargeOffVo> getPartnerBillTableOnce(Map map) {
    Long pageNum = Long.valueOf(map.get("page").toString());
    Long pageSize = Long.valueOf( map.get("pageSize").toString());
    Page<ChargeOffVo> page = new Page<>(pageNum, pageSize);
    return this.baseMapper.getPartnerBillTableOnce(page,map);
  }

  @Override
  public Map<ChargeOffVo, String> getPartnerBillTableOnceCount(Map map) {
    return this.baseMapper.getPartnerBillTableOnceCount(map);
  }

  @Override
  public List<ChargeOffVo> getSelect2Good(Map map) {
    return this.baseMapper.getSelect2Good(map);
  }

  @Override
  public IPage<ChargeOffVo> getPartnerBillTableByUse(Map map) {
    Long pageNum = Long.valueOf(map.get("page").toString());
    Long pageSize = Long.valueOf( map.get("pageSize").toString());
    Page<ChargeOffVo> page = new Page<>(pageNum, pageSize);
    return this.baseMapper.getPartnerBillTableByUse(page,map);
  }

  @Override
  public IPage<ChargeOffVo> getSettleRuleTable(Map map) {
    Long pageNum = Long.valueOf(map.get("page").toString());
    Long pageSize = Long.valueOf( map.get("pageSize").toString());
    Page<ChargeOffVo> page = new Page<>(pageNum, pageSize);
    return this.baseMapper.getSettleRuleTable(page,map);
  }


  @Override
  public List<ChargeOffVo> gettableInfoByorderId(Map map) {
    return this.baseMapper.gettableInfoByorderId(map);
  }


  @Override
  public List<ChargeOffDto> isAlreadyExitMonth(Map map) {
    return this.baseMapper.isAlreadyExitMonth(map);
  }


  @Override
  public int updatebillStatus(Map map) {
    return this.baseMapper.updatebillStatus(map);
  }

  @Override
  public int updateSettleScale(Map map) {
    return this.baseMapper.updateSettleScale(map);
  }
}
