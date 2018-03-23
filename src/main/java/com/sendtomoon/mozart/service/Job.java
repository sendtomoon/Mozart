package com.sendtomoon.mozart.service;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public interface Job {

	public void changeDNS() throws KeyManagementException, NoSuchAlgorithmException, IOException;
	
}
