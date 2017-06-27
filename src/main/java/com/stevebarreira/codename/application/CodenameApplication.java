package com.stevebarreira.codename.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@ComponentScan({"com.stevebarreira.codename"})
@EnableMongoRepositories("com.stevebarreira.codename.repository")
@EnableMongoAuditing
@SpringBootApplication
public class CodenameApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodenameApplication.class, args);
	}
}
