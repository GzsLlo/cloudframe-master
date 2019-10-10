package com.ai.cloudframe.provider.cmc.service;

import com.ai.cloudframe.provider.api.sys.model.dto.ChannelDto;
import com.ai.cloudframe.provider.api.sys.model.vo.ChannelVo;
import com.ai.cloudframe.provider.cmc.entity.Channel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-25
 */
public interface IChannelService extends IService<Channel> {

  IPage<ChannelDto> channelInfoqueryTable(ChannelDto channelDto);

  IPage<ChannelVo> getTableByChannelId (Map map);

}
