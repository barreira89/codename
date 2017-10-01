package com.stevebarreira.codename.repository;

import com.stevebarreira.codename.model.dto.Codenames;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordlistRepository extends MongoRepository<Codenames, String> {
	
}