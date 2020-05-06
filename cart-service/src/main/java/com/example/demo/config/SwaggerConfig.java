package com.example.demo.config;

import static springfox.documentation.builders.PathSelectors.regex;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * configuration file for Swagger
 * 
 * @author santosh kumar
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {


	/**
	 * application name
	 */
	@Value("${spring.application.name}")
	private String appName;

	/**
	 * 
	 * @return swagger controller
	 */
	@Bean
	public Docket exampleRESTApi10(Environment env) {
		List<Parameter> propertyList = new ArrayList<>();
		ParameterBuilder parameterBuilder = new ParameterBuilder();

		parameterBuilder.name("Accept-Language").modelRef(new ModelRef("String")).parameterType("header").required(true)
		.defaultValue("en").build();
		propertyList.add(parameterBuilder.build());
		return new Docket(DocumentationType.SWAGGER_2).groupName("E-Commerce App  " + appName + " API V1.0").select()
				.apis(RequestHandlerSelectors.basePackage("com.example.demo.cart"))
				.paths(regex("/cart.*")).build().apiInfo(metaData()).globalOperationParameters(propertyList);
	}

	/**
	 * 
	 * @return API Term and conditions,license and developer contact information
	 */
	private ApiInfo metaData() {
		return new ApiInfo(appName, ".", "API 1.0", "Terms of service",
				new Contact("santosh kumar", "", "a_santoshyadav@gmail.com"), "LICENSE OF API", "API LICENSE URL",
				Collections.emptyList());
	}
}
