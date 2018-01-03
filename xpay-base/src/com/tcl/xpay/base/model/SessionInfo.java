package com.tcl.xpay.base.model;

import java.io.Serializable;

/**
 * <br>
 * <b>功能：</b>session信息<br>
 */
@SuppressWarnings("serial")
public class SessionInfo implements Serializable {

	/**
	 * 用户token
	 */
	private String token;
	/**
	 * 设备
	 */
	private Device device;

	public SessionInfo() {

	}

	public SessionInfo(String token, Device device) {
		this.token = token;
		this.device = device;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

}
