package com.troningt.todolist.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
	@Value("${spring.application.name}")
	String appName;
	@Value("${spring.profiles.active}")
	String profile;
    @Bean
	public ModelMapper modelMapper() {
		System.out.print(appName);
		return new ModelMapper();
	}

}