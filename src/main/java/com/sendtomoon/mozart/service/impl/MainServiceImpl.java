package com.sendtomoon.mozart.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sendtomoon.mozart.base.BaseComponent;
import com.sendtomoon.mozart.dao.ErrorMongoDAO;
import com.sendtomoon.mozart.entity.ErrorDTO;
import com.sendtomoon.mozart.entity.LoginInfoDTO;
import com.sendtomoon.mozart.entity.WanPageDTO;
import com.sendtomoon.mozart.service.GetIP;
import com.sendtomoon.mozart.service.GetIPFromMAC1200R;
import com.sendtomoon.mozart.service.MainService;
import com.sendtomoon.mozart.tools.HttpsClient;

@Component
public class MainServiceImpl extends BaseComponent implements MainService {

	@Autowired
	GetIP getIP;
	
	@Autowired
	HttpsClient httpsClient;
	
	@Autowired
	ErrorMongoDAO errorMongoDAO;
	
	@Value("${mailSwitch}")
	private boolean mailSwitch;
	
	@Autowired
	GetIPFromMAC1200R mac1200r;
	

	// 更新
	@Override
	public void renewtoGoddy() {
		LoginInfoDTO stok = getIP.getStok();// 获取stok
		if (null == stok) {
			logger.error("MainServiceImpl-renewtoGoddy:Stok is null.");
			ErrorDTO error = new ErrorDTO();
			error.setErrorInfo("MainServiceImpl-renewtoGoddy:Stok is null.");
			error.setErrorSence("renewtoGoddy-getStok");
			errorMongoDAO.saveError(error);
			return;
		}
		WanPageDTO wp = getIP.getWanPage(stok.getCookie());
		if (null == wp || StringUtils.isBlank(wp.getData().getData()[0].getPppoe_ip_addr())) {
			logger.error("MainServiceImpl-renewtoGoddy:WanPage is null.");
			ErrorDTO error = new ErrorDTO();
			error.setErrorInfo("MainServiceImpl-renewtoGoddy:WanPage is null.");
			error.setErrorSence("renewtoGoddy-getWanPage");
			errorMongoDAO.saveError(error);
			return;
		}
		getIP.renewDNS(wp.getData().getData()[0].getPppoe_ip_addr());

		if (mailSwitch) {
			getIP.sendEMailForIP(getIP.encryptByPublicKey(wp.getData().getData()[0].getPppoe_ip_addr()));
		}
	}



	@Override
	public void renewForMAC1200R() {
		mac1200r.getIP();
	}
}
