package com.sendtomoon.mozart.entity;

import java.io.Serializable;

import org.apache.http.impl.client.BasicCookieStore;

public class LoginInfoDTO implements Serializable {

	private static final long serialVersionUID = -8511065330781577814L;

	private BasicCookieStore cookie;
	private String error_code;
	private String httpStatus;
	private String date;
	private String ErrMsg;
	private String Result;
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
	 * @return the httpStatus
	 */
	public String getHttpStatus() {
		return httpStatus;
	}

	/**
	 * @param httpStatus
	 *            the httpStatus to set
	 */
	public void setHttpStatus(String httpStatus) {
		this.httpStatus = httpStatus;
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

	/**
	 * @return the cookie
	 */
	public BasicCookieStore getCookie() {
		return cookie;
	}

	/**
	 * @param cookie the cookie to set
	 */
	public void setCookie(BasicCookieStore cookie) {
		this.cookie = cookie;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StokDTO [cookie=" + cookie + ", error_code=" + error_code + ", httpStatus=" + httpStatus + ", date="
				+ date + "]";
	}

	/**
	 * @return the errMsg
	 */
	public String getErrMsg() {
		return ErrMsg;
	}

	/**
	 * @param errMsg the errMsg to set
	 */
	public void setErrMsg(String errMsg) {
		ErrMsg = errMsg;
	}

	/**
	 * @return the result
	 */
	public String getResult() {
		return Result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		Result = result;
	}



}
