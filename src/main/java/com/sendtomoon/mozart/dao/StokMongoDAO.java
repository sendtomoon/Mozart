package com.sendtomoon.mozart.dao;

import com.sendtomoon.mozart.entity.LoginInfoDTO;
import com.sendtomoon.mozart.entity.WanPageDTO;

public interface StokMongoDAO {
	
	public void saveStok(LoginInfoDTO stok);

	public void saveWanPage(WanPageDTO wanPage);
}
