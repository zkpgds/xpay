package com.tcl.xpay.base.service;

import java.util.Date;

import com.tcl.xpay.base.model.UserToken;

/**
 * <br>
 * <b>功能：</b>业务service基础接口<br>
 */

public interface ILogService {
	/**
	 * 记录登录日志
	 * 
	 * @param userCode
	 * @param loginI
	 * @param loginTime
	 * @param loginUa
	 */
	void insertLoginLog(String userCode, String loginIp, Date loginTime, String loginUa);

	/**
	 * 记录业务日志
	 * 
	 * @param userToken
	 * @param moduleId
	 * @param content
	 * @param operateTime
	 */
	void insertBussLog(UserToken userToken, Integer moduleId, String content, Date operateTime);
}
