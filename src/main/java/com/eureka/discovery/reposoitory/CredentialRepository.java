package com.eureka.discovery.reposoitory;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.eureka.discovery.model.Credentials;

@Repository
public interface CredentialRepository extends MongoRepository<Credentials, String>{

}
