package com.eureka.discovery.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.eureka.discovery.model.Train;

@Repository
public interface TrainRepository extends MongoRepository<Train, String> {

}
