package com.sendtomoon.mozart.entity;

import java.io.Serializable;

public class DNSRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String data;
	private String name;
	private String ttl;
	private String type;

	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the ttl
	 */
	public String getTtl() {
		return ttl;
	}

	/**
	 * @param ttl
	 *            the ttl to set
	 */
	public void setTtl(String ttl) {
		this.ttl = ttl;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

}
