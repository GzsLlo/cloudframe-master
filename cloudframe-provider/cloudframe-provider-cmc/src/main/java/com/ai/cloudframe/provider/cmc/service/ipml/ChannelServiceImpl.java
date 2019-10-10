package com.ai.cloudframe.provider.cmc.service.ipml;

import com.ai.cloudframe.provider.api.sys.model.dto.ChannelDto;
import com.ai.cloudframe.provider.api.sys.model.vo.ChannelVo;
import com.ai.cloudframe.provider.cmc.entity.Channel;
import com.ai.cloudframe.provider.cmc.mapper.ChannelMapper;
import com.ai.cloudframe.provider.cmc.service.IChannelService;
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
 * @since 2019-07-25
 */
@Service
public class ChannelServiceImpl extends ServiceImpl<ChannelMapper, Channel> implements IChannelService {

  @Override
  public IPage<ChannelDto> channelInfoqueryTable(ChannelDto channelDto) {
    Long pageNum = (null != channelDto.getPage()) ? Long.valueOf( channelDto.getPage()) : null;
    Long pageSize = (null !=  channelDto.getPageSize())
        ? Long.valueOf(channelDto.getPageSize()) : null;

    Page<ChannelDto> page = new Page<>(pageNum, pageSize);

    return this.baseMapper.channelInfoqueryTable(page,channelDto);
  }

  @Override
  public IPage<ChannelVo> getTableByChannelId(Map map) {
    Long pageNum = Long.valueOf(map.get("page").toString());
    Long pageSize = Long.valueOf( map.get("pageSize").toString());
    Page<ChannelVo> page = new Page<>(pageNum, pageSize);
    return this.baseMapper.getTableByChannelId(page,map);
  }
}
