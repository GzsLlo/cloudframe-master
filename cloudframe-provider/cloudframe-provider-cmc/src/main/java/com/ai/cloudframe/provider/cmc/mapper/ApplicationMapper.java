package com.ai.cloudframe.provider.cmc.mapper;

import com.ai.cloudframe.provider.api.sys.model.dto.ApiAppDto;
import com.ai.cloudframe.provider.api.sys.model.dto.ApiDto;
import com.ai.cloudframe.provider.api.sys.model.dto.ApplicationDto;
import com.ai.cloudframe.provider.api.sys.model.vo.AddApplicationVo;
import com.ai.cloudframe.provider.api.sys.model.vo.ApplicationProductVo;
import com.ai.cloudframe.provider.cmc.entity.Application;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-19
 */
public interface ApplicationMapper extends BaseMapper<Application> {

  IPage<ApiAppDto> applicationQuery(@Param("page") Page page, @Param("map") Map map);

  int updateByAppId(@Param("map") Map map);

  IPage<Application> getApplicationInfo(@Param("pg") Page page, @Param("param") Map param);

  /**
   * 更新应用.
   *
   * @param applicationVo
   * @return
   */
  int updateApplication(@Param("applicationVo") AddApplicationVo applicationVo);

  ApplicationProductVo getApplicationProductRelation(@Param("apiDto") ApiDto apiDto);

}
