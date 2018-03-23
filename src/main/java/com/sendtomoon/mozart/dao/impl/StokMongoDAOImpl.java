package com.sendtomoon.mozart.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.sendtomoon.mozart.dao.StokMongoDAO;
import com.sendtomoon.mozart.entity.StokDTO;
import com.sendtomoon.mozart.entity.WanPageDTO;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class StokMongoDAOImpl implements StokMongoDAO {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void saveStok(StokDTO stok) {
		mongoTemplate.save(stok);
	}

	@Override
	public void saveWanPage(WanPageDTO wanPage) {
		mongoTemplate.save(wanPage);
	}

}
