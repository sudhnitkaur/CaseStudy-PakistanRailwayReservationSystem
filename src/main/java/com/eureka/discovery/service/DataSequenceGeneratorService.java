package com.eureka.discovery.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.eureka.discovery.model.UserSequence;

@Service
public class DataSequenceGeneratorService {

	@Autowired
	private MongoOperations mongoOperations;
	
	public Long getUserSequenceNumber(String sequenceName) {
		Query query = new Query(Criteria.where("id").is(sequenceName));
		Update update = new Update().inc("seq", 1);
		UserSequence counter =  mongoOperations.findAndModify(query, update, FindAndModifyOptions.options().returnNew(true).upsert(true), UserSequence.class);
		return !Objects.isNull(counter)? counter.getSeq():10001;
	}
	
}
