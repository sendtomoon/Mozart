package com.sendtomoon.mozart.service;

import org.apache.http.client.CookieStore;

import com.sendtomoon.mozart.entity.StokDTO;
import com.sendtomoon.mozart.entity.WanPageDTO;

public interface GetIP {

	public StokDTO getStok();

	public WanPageDTO getWanPage(CookieStore cookie);

	public void renewDNS(String ipAddress);

	public void sendEMailForIP(String ip);
	
	public String encryptByPublicKey(String info);
}
