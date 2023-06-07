package com.timesete.projeto5.model.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.timesete.projeto5.model.entity.PartEntryModel;

public interface PartEntryRepository extends MongoRepository<PartEntryModel, String> {
    
    

}
