package com.stevebarreira.codename.repository.impl;

import com.stevebarreira.codename.model.dto.Codenames;
import com.stevebarreira.codename.repository.WordListRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.SampleOperation;

public class WordListRepositoryCustomImpl implements WordListRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Codenames findRandomCodeName() {
        Aggregation sample = Aggregation.newAggregation(new SampleOperation(1));
        AggregationResults<Codenames> results = mongoTemplate.aggregate(sample, "CodeNames", Codenames.class);
        return results.getMappedResults().stream().findFirst().orElse(null);
    }
}
