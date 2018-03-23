package com.sendtomoon.mozart.mapper;

import org.apache.ibatis.annotations.Insert;

import com.sendtomoon.mozart.entity.StokDTO;

public interface StokMapper {

	static final String insertSock = "INSERT INTO SOCK (ID,STOK,ERROR_CODE,HTTP_STATUS,DATE) VALUES (#{ID},#{STOK},#{ERROR_CODE},#{HTTP_STATUS},#{DATE})";
	
	@Insert(insertSock)
	void insertSock (StokDTO sock);
	
}
