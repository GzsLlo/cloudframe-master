package com.ai.cloudframe.provider.cmc.service;

import com.ai.cloudframe.common.base.response.BaseResponse;
import com.ai.cloudframe.provider.api.sys.model.dto.GoodProductSettleDto;
import com.ai.cloudframe.provider.cmc.entity.SettleRule;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-17
 */
public interface ISettleRuleService extends IService<SettleRule> {

  boolean newSettleRule(List<GoodProductSettleDto> goodProductSettleDtoList)  throws Exception;
}
