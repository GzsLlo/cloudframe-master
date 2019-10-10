package com.ai.cloudframe.provider.cmc.service.ipml;

import com.ai.cloudframe.provider.api.sys.model.dto.GoodProductSettleDto;
import com.ai.cloudframe.provider.api.sys.model.dto.ProductGoodDto;
import com.ai.cloudframe.provider.cmc.entity.Product;
import com.ai.cloudframe.provider.cmc.entity.ProductGood;
import com.ai.cloudframe.provider.cmc.entity.SettleRule;
import com.ai.cloudframe.provider.cmc.mapper.ProductMapper;
import com.ai.cloudframe.provider.cmc.mapper.SettleRuleMapper;
import com.ai.cloudframe.provider.cmc.service.ISettleRuleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-17
 */
@Service
public class SettleRuleServiceImpl extends ServiceImpl<SettleRuleMapper, SettleRule> implements ISettleRuleService {




  @Autowired(required = false)
  private SettleRuleMapper settleRuleMapper = null;

  @Autowired(required = false)
  private ProductMapper productMapper = null;

  @Override
  @Transactional(rollbackFor=Exception.class)
  public boolean newSettleRule(List<GoodProductSettleDto> goodProductSettleDtoList) throws Exception {

    List<SettleRule> settleRuleList=new ArrayList<>();

    for (GoodProductSettleDto each:goodProductSettleDtoList) {
      SettleRule settleRule=new SettleRule();
      BeanUtils.copyProperties(each,settleRule);

      long timestamp = each.getStartDate().getTime();
      LocalDateTime localDateTimeStart = Instant.ofEpochMilli(timestamp).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
      settleRule.setStartDate(localDateTimeStart);

      long timestamp2 = each.getEndDate().getTime();
      LocalDateTime localDateTimeEnd = Instant.ofEpochMilli(timestamp2).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
      settleRule.setEndDate(localDateTimeEnd);

      Map<String,Object> map = new HashMap<>();
      map.put("goodId",settleRule.getGoodId());
      map.put("productId",settleRule.getProductId());
      List<GoodProductSettleDto> resultList =  productMapper.selectGoodProductSettleEffect(map);
      if(resultList.size() == 1){
        SettleRule settleRuleNew=new SettleRule();
        settleRuleNew.setRuleId(resultList.get(0).getRuleId());
        Date date2 = new Date();
        Instant instant2 = date2.toInstant();//An instantaneous point on the time-line.(时间线上的一个瞬时点。)
        ZoneId zoneId2 = ZoneId.systemDefault();
        LocalDateTime localDateTime2 = instant2.atZone(zoneId2).toLocalDateTime();
        settleRuleNew.setEndDate(localDateTime2);
        int flag = settleRuleMapper.updateById(settleRuleNew);
        if(flag!=1){
          return  false;
        }
        settleRuleList.add(settleRule);
      }else if (resultList.size() == 0){
        settleRuleList.add(settleRule);
      }else{
        throw new Exception("");
      }
    }

    for (SettleRule each2:settleRuleList) {
      int flag2 =settleRuleMapper.insert(each2);
      if(flag2 != 1){
        return  false;
      }
    }

    return true;
  }
}
