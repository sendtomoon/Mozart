package com.sendtomoon.mozart.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.sendtomoon.mozart.dao.ErrorMongoDAO;
import com.sendtomoon.mozart.entity.ErrorDTO;

@Component
public class ErrorMongoDAOImpl implements ErrorMongoDAO {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void saveError(ErrorDTO dto) {
		mongoTemplate.save(dto);
	}

}
