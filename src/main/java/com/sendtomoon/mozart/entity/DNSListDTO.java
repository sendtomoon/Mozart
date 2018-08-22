package com.sendtomoon.mozart.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DNSListDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private DNSInfo[] datas;
	
	private Date createdDate = new Date();

	public DNSListDTO(List<DNSInfo> list) {
		this.datas = new DNSInfo[list.size()];
		for (int i = 0; i < list.size(); i++) {
			this.datas[i] = list.get(i);
		}
	}

	public DNSListDTO() {
		super();
	}

	/**
	 * @return the datas
	 */
	public DNSInfo[] getDatas() {
		return datas;
	}

	/**
	 * @param datas the datas to set
	 */
	public void setDatas(DNSInfo[] datas) {
		this.datas = datas;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
