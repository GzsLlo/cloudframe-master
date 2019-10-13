package com.ai.cloudframe.provider.customer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {"com.ai.cloudframe.*",})
@MapperScan("com.ai.cloudframe.provider.customer.mapper")
public class CloudframeProviderCustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudframeProviderCustomerApplication.class, args);
    }

}
