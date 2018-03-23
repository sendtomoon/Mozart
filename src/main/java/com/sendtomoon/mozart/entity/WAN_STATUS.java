package com.sendtomoon.mozart.entity;

import java.io.Serializable;

public class WAN_STATUS implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3796580503157350819L;
	private String down_speed;
	private String error_code;
	private String gateway;
	private String ipaddr;
	private String link_status;
	private String netmask;
	private String phy_status;
	private String pri_dns;
	private String proto;
	private String snd_dns;
	private String up_speed;
	private String up_time;

	/**
	 * @return the down_speed
	 */
	public String getDown_speed() {
		return down_speed;
	}

	/**
	 * @param down_speed
	 *            the down_speed to set
	 */
	public void setDown_speed(String down_speed) {
		this.down_speed = down_speed;
	}

	/**
	 * @return the error_code
	 */
	public String getError_code() {
		return error_code;
	}

	/**
	 * @param error_code
	 *            the error_code to set
	 */
	public void setError_code(String error_code) {
		this.error_code = error_code;
	}

	/**
	 * @return the gateway
	 */
	public String getGateway() {
		return gateway;
	}

	/**
	 * @param gateway
	 *            the gateway to set
	 */
	public void setGateway(String gateway) {
		this.gateway = gateway;
	}

	/**
	 * @return the ipaddr
	 */
	public String getIpaddr() {
		return ipaddr;
	}

	/**
	 * @param ipaddr
	 *            the ipaddr to set
	 */
	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}

	/**
	 * @return the link_status
	 */
	public String getLink_status() {
		return link_status;
	}

	/**
	 * @param link_status
	 *            the link_status to set
	 */
	public void setLink_status(String link_status) {
		this.link_status = link_status;
	}

	/**
	 * @return the netmask
	 */
	public String getNetmask() {
		return netmask;
	}

	/**
	 * @param netmask
	 *            the netmask to set
	 */
	public void setNetmask(String netmask) {
		this.netmask = netmask;
	}

	/**
	 * @return the phy_status
	 */
	public String getPhy_status() {
		return phy_status;
	}

	/**
	 * @param phy_status
	 *            the phy_status to set
	 */
	public void setPhy_status(String phy_status) {
		this.phy_status = phy_status;
	}

	/**
	 * @return the pri_dns
	 */
	public String getPri_dns() {
		return pri_dns;
	}

	/**
	 * @param pri_dns
	 *            the pri_dns to set
	 */
	public void setPri_dns(String pri_dns) {
		this.pri_dns = pri_dns;
	}

	/**
	 * @return the proto
	 */
	public String getProto() {
		return proto;
	}

	/**
	 * @param proto
	 *            the proto to set
	 */
	public void setProto(String proto) {
		this.proto = proto;
	}

	/**
	 * @return the snd_dns
	 */
	public String getSnd_dns() {
		return snd_dns;
	}

	/**
	 * @param snd_dns
	 *            the snd_dns to set
	 */
	public void setSnd_dns(String snd_dns) {
		this.snd_dns = snd_dns;
	}

	/**
	 * @return the up_speed
	 */
	public String getUp_speed() {
		return up_speed;
	}

	/**
	 * @param up_speed
	 *            the up_speed to set
	 */
	public void setUp_speed(String up_speed) {
		this.up_speed = up_speed;
	}

	/**
	 * @return the up_time
	 */
	public String getUp_time() {
		return up_time;
	}

	/**
	 * @param up_time
	 *            the up_time to set
	 */
	public void setUp_time(String up_time) {
		this.up_time = up_time;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WAN_STATUS [down_speed=" + down_speed + ", error_code=" + error_code + ", gateway=" + gateway
				+ ", ipaddr=" + ipaddr + ", link_status=" + link_status + ", netmask=" + netmask + ", phy_status="
				+ phy_status + ", pri_dns=" + pri_dns + ", proto=" + proto + ", snd_dns=" + snd_dns + ", up_speed="
				+ up_speed + ", up_time=" + up_time + "]";
	}

}
