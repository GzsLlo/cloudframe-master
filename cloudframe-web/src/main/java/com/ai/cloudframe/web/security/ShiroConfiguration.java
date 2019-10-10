package com.ai.cloudframe.web.security;

import com.ai.cloudframe.common.config.properties.CloudFrameProperties;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisClusterManager;;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.Filter;

/**
 * tangsz
 */
@Configuration
public class ShiroConfiguration {

  @Bean
  public CloudFrameProperties CloudFrameConfig() {
    return new  CloudFrameProperties();
  }


  @Bean(name = "shiroFilter")
  public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
    ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
    shiroFilterFactoryBean.setSecurityManager(securityManager);
    Map<String, Filter> filterMap = new LinkedHashMap<>();
    filterMap.put("authc", new AjaxPermissionsAuthorizationFilter());
    shiroFilterFactoryBean.setFilters(filterMap);
    shiroFilterFactoryBean.setFilterChainDefinitionMap(CloudFrameConfig().getShiroFilter());
    return shiroFilterFactoryBean;
  }

  @Bean
  public SecurityManager securityManager() {
    DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
    securityManager.setRealm(userRealm());
    securityManager.setSessionManager(sessionManager());
    securityManager.setCacheManager(cacheManager());
    return securityManager;
  }


  @Bean
  public UserRealm userRealm() {
    UserRealm userRealm = new UserRealm();
    return userRealm;
  }


  @Bean
  public SessionManager sessionManager() {
    CustomerSessionManager mySessionManager = new CustomerSessionManager();
    mySessionManager.setSessionDAO(redisSessionDAO());
    return mySessionManager;
  }


  @Bean
  public RedisSessionDAO redisSessionDAO() {
    RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
    redisSessionDAO.setExpire(CloudFrameConfig().getRedisProperties().getExpire());
    redisSessionDAO.setKeyPrefix(CloudFrameConfig().getRedisProperties().getSessionId());
    redisSessionDAO.setRedisManager(redisManager());
    return redisSessionDAO;
  }

  public RedisClusterManager redisManager() {
    RedisClusterManager redisManager = new RedisClusterManager();
    redisManager.setHost(CloudFrameConfig().getRedisProperties().getHost());
    redisManager.setTimeout(CloudFrameConfig().getRedisProperties().getTimeout());
    redisManager.setPassword(CloudFrameConfig().getRedisProperties().getPassword());
    return redisManager;
  }


  @Bean
  public RedisCacheManager cacheManager() {
    RedisCacheManager redisCacheManager = new RedisCacheManager();
    redisCacheManager.setRedisManager(redisManager());
    return redisCacheManager;
  }

  @Bean(name = "credentialsMatcher")
  public HashedCredentialsMatcher hashedCredentialsMatcher() {
    HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
    hashedCredentialsMatcher.setHashAlgorithmName("md5");
    hashedCredentialsMatcher.setHashIterations(2);
    hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
    return hashedCredentialsMatcher;
  }

  @Bean
  public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
    return new LifecycleBeanPostProcessor();
  }


  @Bean
  @DependsOn({"lifecycleBeanPostProcessor"})
  public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
    DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
    advisorAutoProxyCreator.setProxyTargetClass(true);
    return advisorAutoProxyCreator;
  }

  @Bean
  public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
    AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
//    authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
    return authorizationAttributeSourceAdvisor;
  }

}
