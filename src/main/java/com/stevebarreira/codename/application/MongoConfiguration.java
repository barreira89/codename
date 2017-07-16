package com.stevebarreira.codename.application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
public class MongoConfiguration extends AbstractMongoConfiguration {

	@Value("${spring.data.mongodb.port:27017}")
	private String mongoPort;
	@Value("${spring.data.mongodb.host:127.0.0.1}")
	private String mongoHost;
	@Value("${spring.data.mongodb.dbname:test}")
	private String mongoDatabase;
	
	@Override
	protected String getDatabaseName() {
		return mongoDatabase;
	}

	@Override
	public Mongo mongo() throws Exception {
		return new MongoClient(mongoHost + ":" + mongoPort);
	}

}
