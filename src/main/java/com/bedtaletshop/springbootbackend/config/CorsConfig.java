package com.bedtaletshop.springbootbackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

	// Setting type with global
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// unblock to global
//		registry.addMapping("/**");
//		registry.addMapping("/product").allowedMethods("GET", "POST");
		registry.addMapping("/product/*").allowedMethods("*");
	}
}
