package com.sendtomoon.mozart.dao;

import com.sendtomoon.mozart.entity.ErrorDTO;

public interface ErrorMongoDAO {
	public void saveError(ErrorDTO DTO);
}
