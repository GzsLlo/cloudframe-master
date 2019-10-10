package com.ai.cloudframe.provider.sys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 系统管理.
 *
 * @author tangsz
 */

@SpringBootApplication
//@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {"com.ai.cloudframe.*",})
@MapperScan("com.ai.cloudframe.provider.sys.mapper")
public class CloudframeProviderSysApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudframeProviderSysApplication.class, args);
	}

}
