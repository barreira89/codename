package com.stevebarreira.codename.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class ApplicationConfiguration {

	@Bean
	public Random generator(){
		return new Random();
	}
}
