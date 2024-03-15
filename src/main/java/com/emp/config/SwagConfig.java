package com.emp.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;

public class SwagConfig {
	@Bean
	public GroupedOpenApi controllerApi() {
		return GroupedOpenApi.builder()
				.group("employee-api")
				.packagesToScan("com.emp.controller")
				.build();


	

	    }
}
