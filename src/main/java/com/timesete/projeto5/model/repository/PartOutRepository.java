package com.timesete.projeto5.model.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.timesete.projeto5.model.entity.PartOutModel;

public interface PartOutRepository extends MongoRepository<PartOutModel, String>{
    
}
