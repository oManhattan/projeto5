package com.timesete.projeto5.model.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.data.mongodb.repository.ExistsQuery;

import com.timesete.projeto5.model.entity.LocationModel;

public interface LocationRepository extends MongoRepository<LocationModel, String> {
    
    @Query("{ 'id' : ?0 }")
    Optional<LocationModel> findLocationById(final String id);
    
    @Query("{ 'name' : ?0 }")
    Optional<LocationModel> findLocationByName(final String name);

    @ExistsQuery("{ 'id' : ?0 }")
    Boolean existsByName(String name);

    @Query("{ 'parts' : { $elemMatch: { 'id' : ?0 } } }")
    Optional<LocationModel> containsPart(final String id);

    @Query("{ '_id': ?0 }")
    @Update("{ '$set': ?1 }")
    Integer updateLocationById(final String id, final LocationModel model);

    @Query("{'parts': {$in: [?0]}}")
    Optional<LocationModel> findLocationWithPartId(final String id);
}
