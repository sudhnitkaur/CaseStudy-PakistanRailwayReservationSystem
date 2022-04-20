package com.eureka.discovery.reposoitory;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.eureka.discovery.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, Long>{

}
