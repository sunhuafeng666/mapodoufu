package com.louis.mango.admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
@Configuration
public class WebMvcConfigurer implements org.springframework.web.servlet.config.annotation.WebMvcConfigurer {
	public void addCoreMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedOrigins("*")
		.allowedMethods("POST","PUT","OPTIONS","DELETE","GET")
		.maxAge(168000)
		.allowedHeaders("*")
		.allowCredentials(true);
	}
}
