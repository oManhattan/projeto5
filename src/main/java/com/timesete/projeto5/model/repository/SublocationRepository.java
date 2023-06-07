package com.timesete.projeto5.model.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.ExistsQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import com.timesete.projeto5.model.entity.SublocationModel;

public interface SublocationRepository extends MongoRepository<SublocationModel, String> {

    @Query("{ 'id' : ?0 }")
    Optional<SublocationModel> findSublocationById(final String id);
    
    @ExistsQuery("{ 'id' : ?0 }")
    Boolean existsByName(String name);

    @Query("{ '_id': ?0 }")
    @Update("{ '$set': ?1 }")
    Integer updateSublocationById(final String id, final SublocationModel model);

    @Query("{'parts': {$in: [?0]}}")
    Optional<SublocationModel> findSublocationWithPartId(final String id);
}
