package com.stevebarreira.codename.repository;

import com.stevebarreira.codename.model.GameBoards;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameBoardRepository extends MongoRepository<GameBoards, String> {

}
