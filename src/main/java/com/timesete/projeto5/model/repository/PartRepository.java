package com.timesete.projeto5.model.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import com.timesete.projeto5.model.entity.PartModel;


public interface PartRepository extends MongoRepository<PartModel, String> {
    
    @Query(value = "{'_id': ?0}")
    Optional<PartModel> findPartById(final String id);

    @Query("{ '_id': ?0 }")
    @Update("{ '$set': ?1 }")
    Integer updatePartById(final String id, final PartModel model);

}
