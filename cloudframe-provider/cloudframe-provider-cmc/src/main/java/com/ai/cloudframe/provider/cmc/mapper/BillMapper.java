package com.ai.cloudframe.provider.cmc.mapper;

import com.ai.cloudframe.provider.api.sys.model.vo.ChargeOffVo;
import com.ai.cloudframe.provider.cmc.entity.Bill;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-19
 */
public interface BillMapper extends BaseMapper<Bill> {
  IPage<ChargeOffVo> getCustBillTable(@Param("pg")Page page, @Param("selectParms") Map selectParms);
}
