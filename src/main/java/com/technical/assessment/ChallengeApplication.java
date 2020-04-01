package com.technical.assessment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ChallengeApplication implements WebMvcConfigurer {

	@Value("${swagger.ui}")
	private String swaggerUi;

	@Value("${app.resources}")
	private String appResources;

	public static void main(String[] args) {
		SpringApplication.run(ChallengeApplication.class, args);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(swaggerUi)
				.addResourceLocations(appResources);
	}

}
