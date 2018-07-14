package com.stevebarreira.codename.repository;

import com.stevebarreira.codename.model.Games;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GamesRepository extends MongoRepository<Games, String>, PagingAndSortingRepository<Games, String> {

}
