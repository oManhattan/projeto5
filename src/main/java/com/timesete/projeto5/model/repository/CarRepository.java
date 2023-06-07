package com.timesete.projeto5.model.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.ExistsQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import com.timesete.projeto5.model.entity.CarModel;

public interface CarRepository extends MongoRepository<CarModel, String> {
    
    @ExistsQuery(value = "{ 'chassi' : ?0 }")
    public Boolean existsCarByChassi(final String chassi);

    @Query("{ '_id' : ?0 }")
    public Optional<CarModel> findCarById(final String id);

    @Query("{ '_id' : ?0 }")
    @Update("{ '$set': ?1 }")
    public Integer updateCarById(final String id, final CarModel car);
}
