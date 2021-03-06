package com.ai.cloudframe.common.base.support;

import com.ai.cloudframe.common.base.constant.GlobalConstant;
import com.ai.cloudframe.common.config.constant.Constant;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.cloud.context.scope.refresh.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * tangsz
 */
@Component
public class PropertiesRefresher implements ApplicationContextAware {

  ApplicationContext applicationContext;

  @Autowired
  RefreshScope refreshScope;


  @ApolloConfigChangeListener({Constant.APPLICATION_NS, Constant.COMMON_NS})
  public void onChange(ConfigChangeEvent changeEvent) {
    refreshProperties(changeEvent);
  }

  public void refreshProperties(ConfigChangeEvent changeEvent) {
    this.applicationContext.publishEvent(new EnvironmentChangeEvent(changeEvent.changedKeys()));
    refreshScope.refreshAll();
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }
}
