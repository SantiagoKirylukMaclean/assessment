package com.technical.assessment.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Value("${swagger.basePackage}")
    private String swaggerBasePackage;

    @Value("${swagger.regex}")
    private String swaggerRegex;

    @Value("${swagger.apiKey.name}")
    private String swaggerApiKeyName;

    @Value("${swagger.apiKey.keyname}")
    private String swaggerApiKeyKeyname;

    @Value("${swagger.apiKey.passAs}")
    private String swaggerApiPassAs;

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(swaggerBasePackage))
                .paths(regex(swaggerRegex))
                .build()
                .securitySchemes(Collections.singletonList(apiKey()));
    }
    private ApiKey apiKey() {
        return new ApiKey(swaggerApiKeyName, swaggerApiKeyKeyname, swaggerApiPassAs);
    }

}