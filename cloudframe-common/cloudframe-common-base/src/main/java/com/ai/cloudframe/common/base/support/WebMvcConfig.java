package com.ai.cloudframe.common.base.support;

import com.ai.cloudframe.common.config.properties.CloudFrameProperties;
import com.ai.cloudframe.common.config.properties.SwaggerProperties;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Configuration
@EnableSwagger2
@ConditionalOnProperty(name = "swagger.enabled", havingValue = "true")
public class WebMvcConfig extends WebMvcConfigurationSupport {

	@Resource
	private CloudFrameProperties cloudFrameProperties;

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
						.apiInfo(apiInfo())
						.select()
						.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
						.paths(PathSelectors.any())

						.build()
						.securitySchemes(securitySchemes())
						.securityContexts(securityContexts())
						.enable(true);
	}

	private ApiInfo apiInfo() {
		SwaggerProperties swagger = cloudFrameProperties.getSwagger();
		return new ApiInfoBuilder()
						.title(swagger.getTitle())
						.description(swagger.getDescription())
						.version(swagger.getVersion())
						.license(swagger.getLicense())
						.licenseUrl(swagger.getLicenseUrl())
						.contact(new Contact(swagger.getContactName(), swagger.getContactUrl(), swagger.getContactEmail()))
						.build();
	}

	private List<ApiKey> securitySchemes() {
		return new ArrayList(Collections.singleton(new ApiKey("token", "token", "header")));
	}

	private List<SecurityContext> securityContexts() {
		return new ArrayList(
						Collections.singleton(SecurityContext.builder()
										.securityReferences(defaultAuth())
										.forPaths(PathSelectors.regex("^(?!auth).*$"))
										.build())
		);
	}

	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return new ArrayList(Collections.singleton(new SecurityReference("token", authorizationScopes)));
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/statics/**").addResourceLocations("classpath:/statics/");
		// 解决 SWAGGER 404报错
		registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		String[] allowedOrigins = cloudFrameProperties.getAllowedOrigins().split(",");
		registry.addMapping("/**")
						.allowedOrigins(allowedOrigins)
						.allowedMethods("GET", "HEAD", "POST","PUT", "DELETE", "OPTIONS")
						.allowCredentials(true).maxAge(3600);
	}
}