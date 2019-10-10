package com.ai.cloudframe;


import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RestController;

/**
 * gateway 网关.
 *
 * @author tangsz
 */

@SpringBootApplication
@EnableEurekaClient
@RestController
@EnableApolloConfig
public class CloudframeGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudframeGatewayApplication.class, args);
	}

}
