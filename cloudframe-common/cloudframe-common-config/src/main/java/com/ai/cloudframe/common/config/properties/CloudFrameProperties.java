/*
 * Copyright (c) 2019. AI
 */

package com.ai.cloudframe.common.config.properties;


import com.ai.cloudframe.common.config.constant.Constant;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.*;

/**

 * @author tangsz
 */
@Data
@ConfigurationProperties(prefix = Constant.ROOT_PREFIX)
@Configuration
@Order(0)
public class CloudFrameProperties {
	private SwaggerProperties swagger = new SwaggerProperties();
	private List<GwRulesProperties> gwRules = new ArrayList<>();
	private Map<String, String> shiroFilter = new LinkedHashMap<>();
	private String allowedOrigins = "";
	private RedisProperties redisProperties = new RedisProperties();

	public SwaggerProperties getSwagger() {
		return swagger;
	}

	public void setSwagger(SwaggerProperties swagger) {
		this.swagger = swagger;
	}

	public List<GwRulesProperties> getGwRules() {
		return gwRules;
	}

	public void setGwRules(List<GwRulesProperties> gwRules) {
		this.gwRules = gwRules;
	}

	public Map<String, String> getShiroFilter() {
		return shiroFilter;
	}

	public void setShiroFilter(Map<String, String> shiroFilter) {
		this.shiroFilter = shiroFilter;
	}

	public String getAllowedOrigins() {
		return allowedOrigins;
	}

	public void setAllowedOrigins(String allowedOrigins) {
		this.allowedOrigins = allowedOrigins;
	}

	public RedisProperties getRedisProperties() {
		return redisProperties;
	}

	public void setRedisProperties(RedisProperties redisProperties) {
		this.redisProperties = redisProperties;
	}
}
