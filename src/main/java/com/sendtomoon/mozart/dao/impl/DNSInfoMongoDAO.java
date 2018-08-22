package com.sendtomoon.mozart.dao.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.sendtomoon.mozart.entity.DNSListDTO;

@Component
public class DNSInfoMongoDAO {
	@Autowired
	private MongoTemplate mongoTemplate;
	
	
	public void saveDNS(DNSListDTO dto) {
		mongoTemplate.save(dto);
	}

}
