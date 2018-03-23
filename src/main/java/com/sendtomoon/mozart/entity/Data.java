package com.sendtomoon.mozart.entity;

import java.io.Serializable;
import java.util.Arrays;

public class Data implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DataDetail data[];
	private String vlan_count;

	/**
	 * @return the vlan_count
	 */
	public String getVlan_count() {
		return vlan_count;
	}
	/**
	 * @param vlan_count the vlan_count to set
	 */
	public void setVlan_count(String vlan_count) {
		this.vlan_count = vlan_count;
	}
	/**
	 * @return the data
	 */
	public DataDetail[] getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(DataDetail[] data) {
		this.data = data;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Data [data=" + Arrays.toString(data) + ", vlan_count=" + vlan_count + "]";
	}
}
