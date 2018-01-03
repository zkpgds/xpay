package com.tcl.xpay.base.model;

import java.io.Serializable;

/**
 * <br>
 * <b>功能：</b>用户Token信息<br>
 */
@SuppressWarnings("serial")
public class UserToken implements Serializable {

    /**
     * 商户编号
     */
    private String merchantNo;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户代码
     */
    private String userCode;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 是否管理员
     */
    private boolean isAdm;

    private String type;

    private String token;

    public UserToken() {

    }

    public UserToken(Integer userId, String userCode, String userName, boolean isAdm) {
        this.userId = userId;
        this.userCode = userCode;
        this.userName = userName;
        this.isAdm = isAdm;
    }


    public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isAdm() {
        return isAdm;
    }

    public void setAdm(boolean isAdm) {
        this.isAdm = isAdm;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
