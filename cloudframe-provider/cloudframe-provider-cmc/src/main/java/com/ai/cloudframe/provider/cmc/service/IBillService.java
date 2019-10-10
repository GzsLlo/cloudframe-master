package com.ai.cloudframe.provider.cmc.service;

import com.ai.cloudframe.provider.api.sys.model.vo.ChargeOffVo;
import com.ai.cloudframe.provider.cmc.entity.Bill;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-19
 */
public interface IBillService extends IService<Bill> {

  IPage<ChargeOffVo> getCustBillTable(Map selectParms);
}
