package com.ai.cloudframe.provider.cmc.mapper;

import com.ai.cloudframe.provider.cmc.entity.ApiFlowControl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-18
 */
public interface ApiFlowControlMapper extends BaseMapper<ApiFlowControl> {

  int updateApiFlowById(@Param("control") ApiFlowControl control);

}
