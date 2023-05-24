package com.timesete.projeto5.model.repository;

import com.timesete.projeto5.model.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserModel, Long> {
}
