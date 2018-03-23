package com.sendtomoon.mozart.entity;

public class NetworkPOJO {

	private WAN_STATUS wan_status;

	/**
	 * @return the wan_status
	 */
	public WAN_STATUS getWan_status() {
		return wan_status;
	}

	/**
	 * @param wan_status the wan_status to set
	 */
	public void setWan_status(WAN_STATUS wan_status) {
		this.wan_status = wan_status;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NetworkPOJO [wan_status=" + wan_status + "]";
	}
}
