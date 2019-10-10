package com.ai.cloudframe;


import com.ai.cloudframe.common.config.properties.CloudFrameProperties;
import com.ai.cloudframe.common.config.properties.GwRulesProperties;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.alibaba.csp.sentinel.adapter.gateway.sc.exception.SentinelGatewayBlockExceptionHandler;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.result.view.ViewResolver;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * tangsz
 */
@Configuration
@RefreshScope
public class GatewayConfiguration {

  private final List<ViewResolver> viewResolvers;
  private  ServerCodecConfigurer serverCodecConfigurer;


  @Resource
  protected CloudFrameProperties cloudFrameProperties;



  public GatewayConfiguration(ObjectProvider<List<ViewResolver>> viewResolversProvider,
                              ServerCodecConfigurer serverCodecConfigurer) {
    this.viewResolvers = viewResolversProvider.getIfAvailable(Collections::emptyList);
    this.serverCodecConfigurer = serverCodecConfigurer;
  }


  /**
   * 配置SentinelGatewayBlockExceptionHandler，限流后异常处理
   *
   * @return
   */
  @Bean
  @Order(Ordered.HIGHEST_PRECEDENCE)
  public SentinelGatewayBlockExceptionHandler sentinelGatewayBlockExceptionHandler() {
    return new SentinelGatewayBlockExceptionHandler(viewResolvers, serverCodecConfigurer);
  }

  /**
   * 配置SentinelGatewayFilter
   *
   * @return
   */
  @Bean
  @Order(-1)
  public GlobalFilter sentinelGatewayFilter() {
    return new SentinelGatewayFilter();
  }

  @PostConstruct
  public void doInit() {
    initGatewayRules();
  }

  /**
   * 配置限流规则
   */
  private void initGatewayRules() {
    List<GwRulesProperties> gwRules = cloudFrameProperties.getGwRules();
    Set<GatewayFlowRule> rules = new HashSet<>();
    gwRules.forEach((item) ->{
      rules.add(new GatewayFlowRule(item.getName())
              .setCount(item.getCount())
              .setIntervalSec(item.getIntervalSec())
      );
    });
    GatewayRuleManager.loadRules(rules);
  }
}
