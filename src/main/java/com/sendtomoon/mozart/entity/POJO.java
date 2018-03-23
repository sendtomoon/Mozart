package com.sendtomoon.mozart.entity;

public class POJO {

	private String ip;
	private String address;

	public POJO() {

	}

	@Override
	public String toString() {
		return "POJO [ip=" + ip + ", address=" + address + "]";
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
