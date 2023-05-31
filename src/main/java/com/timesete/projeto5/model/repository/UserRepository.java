package com.timesete.projeto5.model.repository;

import com.timesete.projeto5.model.entity.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import java.util.Optional;

public interface UserRepository extends MongoRepository<UserModel, String> {

    @Query(value = "{'login': ?0}")
    Optional<UserModel> findUserByLogin(String login);

    @Query(value = "{'_id': ?0}")
    Optional<UserModel> findUserById(String id);

    @Query("{ '_id': ?0 }")
    @Update("{ '$set': ?1 }")
    Integer updateUserById(final String id, final UserModel model);

}
