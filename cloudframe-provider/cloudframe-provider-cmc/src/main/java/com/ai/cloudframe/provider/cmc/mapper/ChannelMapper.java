package com.ai.cloudframe.provider.cmc.mapper;

import com.ai.cloudframe.provider.api.sys.model.dto.ChannelDto;
import com.ai.cloudframe.provider.api.sys.model.vo.ChannelVo;
import com.ai.cloudframe.provider.cmc.entity.Channel;
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
 * @since 2019-07-25
 */
public interface ChannelMapper extends BaseMapper<Channel> {

  IPage<ChannelDto> channelInfoqueryTable(@Param("page") Page page, @Param("channelDto")ChannelDto channelDto);


  IPage<ChannelVo> getTableByChannelId(@Param("pg")Page page, @Param("map") Map map);

}
