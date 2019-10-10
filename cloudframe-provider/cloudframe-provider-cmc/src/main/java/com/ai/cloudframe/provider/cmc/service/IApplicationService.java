package com.ai.cloudframe.provider.cmc.service;

import com.ai.cloudframe.provider.api.sys.model.dto.ApiAppDto;
import com.ai.cloudframe.provider.api.sys.model.dto.ApiDto;
import com.ai.cloudframe.provider.api.sys.model.dto.ApplicationDto;
import com.ai.cloudframe.provider.api.sys.model.vo.AddApplicationVo;
import com.ai.cloudframe.provider.api.sys.model.vo.ApplicationProductVo;
import com.ai.cloudframe.provider.cmc.entity.Application;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-19
 */
public interface IApplicationService extends IService<Application> {

  IPage<ApiAppDto> applicationQuery(@RequestBody Map map);

  /**
   * 应用查询.
   *
   * @param param
   * @return
   */
  IPage<Application> getApplicationInfo(Map param);

  /**
   * 更新应用.
   *
   * @param applicationVo
   * @return
   */
  boolean updateApplication(AddApplicationVo applicationVo);

  /**
   * 部署应用.
   *
   * @return
   */
  boolean deployApplication();

  /**
   * 启动/停止应用.
   *
   * @return
   */
  boolean startOrStopApplication(String tag);


  ApplicationProductVo getApplicationProductRelation( ApiDto apiDto);
}
