package com.sendtomoon.mozart.tools;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public interface HttpsClient {

	public void httpsClient(String ip) throws KeyManagementException, NoSuchAlgorithmException, IOException;

}
