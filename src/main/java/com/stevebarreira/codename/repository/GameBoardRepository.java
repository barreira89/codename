package com.stevebarreira.codename.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stevebarreira.codename.model.GameBoards;
import com.stevebarreira.codename.model.dto.Codenames;

@Repository
public interface GameBoardRepository extends MongoRepository<GameBoards, String> {
	
}