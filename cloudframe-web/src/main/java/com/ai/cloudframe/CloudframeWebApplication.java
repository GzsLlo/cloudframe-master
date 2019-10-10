package com.ai.cloudframe;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * .
 *
 * @author tangsz
 */

@EnableDiscoveryClient
//@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@EnableFeignClients
@SpringBootApplication
@ComponentScan(basePackages = {"com.ai.cloudframe.*",})
@EnableApolloConfig()
public class CloudframeWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudframeWebApplication.class, args);
	}

}
