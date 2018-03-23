package com.sendtomoon.mozart.dao;

import com.sendtomoon.mozart.entity.StokDTO;
import com.sendtomoon.mozart.entity.WanPageDTO;

public interface StokMongoDAO {
	public void saveStok(StokDTO stok);
	public void saveWanPage(WanPageDTO wanPage);
}
