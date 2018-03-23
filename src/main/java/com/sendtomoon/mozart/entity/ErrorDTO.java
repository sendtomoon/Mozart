package com.sendtomoon.mozart.entity;

import com.sendtomoon.mozart.tools.TimeUtil;

public class ErrorDTO {

	private String errorSence;
	private String errorInfo;
	private String date = TimeUtil.now();

	/**
	 * @return the errorSence
	 */
	public String getErrorSence() {
		return errorSence;
	}

	/**
	 * @param errorSence
	 *            the errorSence to set
	 */
	public void setErrorSence(String errorSence) {
		this.errorSence = errorSence;
	}

	/**
	 * @return the errorInfo
	 */
	public String getErrorInfo() {
		return errorInfo;
	}

	/**
	 * @param errorInfo
	 *            the errorInfo to set
	 */
	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
}
