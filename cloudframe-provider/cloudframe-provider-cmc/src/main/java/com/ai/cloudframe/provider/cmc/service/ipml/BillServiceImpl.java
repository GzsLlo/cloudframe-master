package com.ai.cloudframe.provider.cmc.service.ipml;

import com.ai.cloudframe.provider.api.sys.model.vo.ChargeOffVo;
import com.ai.cloudframe.provider.cmc.entity.Bill;
import com.ai.cloudframe.provider.cmc.mapper.BillMapper;
import com.ai.cloudframe.provider.cmc.service.IBillService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-19
 */
@Service
public class BillServiceImpl extends ServiceImpl<BillMapper, Bill> implements IBillService {

  @Override
  public IPage<ChargeOffVo> getCustBillTable(Map selectParms) {
    Long pageNum = ((Integer) selectParms.get("page")).longValue();
    Long pageSize = ((Integer) selectParms.get("pageSize")).longValue();
    Page<ChargeOffVo> page = new Page<>(pageNum, pageSize);
    return this.baseMapper.getCustBillTable(page,selectParms);
  }
}
