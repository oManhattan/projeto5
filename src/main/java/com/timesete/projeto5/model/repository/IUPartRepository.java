package com.timesete.projeto5.model.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import com.timesete.projeto5.model.entity.IUPartModel;

public interface IUPartRepository extends MongoRepository<IUPartModel, String> {
    
    
    @Query("{ 'id' : ?0 }")
    @Update("{ '$set': ?1 }")
    Integer updateIUPartById(final String id, final IUPartModel model);

    @Query("{ 'id' : ?0 }")
    Optional<IUPartModel> findIUPartById(final String id);
}
