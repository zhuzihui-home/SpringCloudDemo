package com.froad.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author ZHUZIHUI
 * @date 2018年9月28日
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Value("${spring.application.name:blog-service}")
	private String resouuceId;
	
	/**
	 * 全局参数
	 */
	private List<Parameter> parameter() {
		List<Parameter> params = new ArrayList<>();
		params.add(new ParameterBuilder().name("Authorization")
				.description("Authorization Bearer Token")
				.modelRef(new ModelRef("string"))
				.parameterType("header")
				.required(false)
				.build());
		return params;
	}
	
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.froad.controller"))
				.paths(PathSelectors.any())
				.build()
				.globalOperationParameters(this.parameter());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title(resouuceId + " api")
				.description(resouuceId + "微服务")
				.termsOfServiceUrl("")
				.version("1.0")
				.build();
	}
}
