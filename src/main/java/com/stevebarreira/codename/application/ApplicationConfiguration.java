package com.stevebarreira.codename.application;

import java.util.Random;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

	@Bean
	public Random generator(){
		return new Random();
	}
}
