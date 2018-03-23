package com.sendtomoon.mozart.entity;

import java.io.Serializable;

public class WanPageDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9118788182335100118L;
	
	private String Result;
	
	private String ErrMsg;
	
	private Data Data;
	
	private String date;

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
	 * @return the data
	 */
	public Data getData() {
		return Data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Data data) {
		Data = data;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WanPageDTO [Result=" + Result + ", ErrMsg=" + ErrMsg + ", Data=" + Data + ", date=" + date + "]";
	}


}
