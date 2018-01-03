package com.tcl.xpay.base.context;

import org.apache.commons.lang3.StringUtils;

import com.tcl.xpay.base.model.Device;
import com.tcl.xpay.base.model.SessionBean;
import com.tcl.xpay.base.model.SessionInfo;
import com.tcl.xpay.base.model.SsoTokenBean;
import com.tcl.xpay.base.model.UserToken;
import com.tcl.xpay.context.ContextScope;


/**
 * <br>
 * <b>功能：</b>上下文帮助类<br>
 */
public class ContextHelper {

    public final static String CONTEXT_SSOTOKEN_BEAN = "CONTEXT_SSOTOKEN_BEAN";//redis里面的SessionBean，放到本地线程类中，方便线程后面继续使用
    public final static String CONTEXT_SESSION_BEAN = "CONTEXT_SESSION_BEAN";//redis里面的sessionBean，放到本地线程类中，方便线程后面继续使用
    public final static String CONTEXT_SYS_USER = "CONTEXT_SYS_USER";
	public final static String CONTEXT_SYS_USER_TOKEN = "CONTEXT_SYS_USER_TOKEN";
	public final static String CONTEXT_SYS_USER_DEVICE = "CONTEXT_SYS_USER_DEVICE";


    public static SessionBean getSessionBean() {
        return ContextScope.getParameter(CONTEXT_SESSION_BEAN);
    }
    public static void setSessionBean(SessionBean sessionBean) {
        ContextScope.setParameter(CONTEXT_SESSION_BEAN, sessionBean);
    }

    public static SsoTokenBean getSsoTokenBean() {
        return ContextScope.getParameter(CONTEXT_SSOTOKEN_BEAN);
    }
    public static void setSsoTokenBean(SsoTokenBean ssoTokenBean) {
        ContextScope.setParameter(CONTEXT_SSOTOKEN_BEAN, ssoTokenBean);
    }

    public static void setUserToken(UserToken user) {
        ContextScope.setParameter(CONTEXT_SYS_USER, user);
    }

    public static UserToken getUserToken() {
        return (UserToken) ContextScope.getParameter(CONTEXT_SYS_USER);
    }

    /**
     * 获取当前用户
     * @return
     */
    public static UserToken getUserToken(String userToken) {
        return (UserToken) ContextScope.getParameter(CONTEXT_SYS_USER);
    }
    
	/**
	 * 获取当前用户id
	 * @return
	 */
	public static Integer getUserId(String userToken) {
		return getUserToken(userToken).getUserId();
	}


	/**
	 * 获取session信息
	 * @return
	 */
	public static SessionInfo getSessionInfo() {
		String token = getTokenId();
		if (StringUtils.isBlank(token)) {
			return null;
		}
		return SessionHelper.getSessionInfo(token);
	}

	/**
	 * 获取用户token
	 * @return
	 */
	public static String getTokenId() {
		return ContextScope.getParameter(CONTEXT_SYS_USER_TOKEN);
	}

	/**
	 * 设置上下文用户token
	 * @param userToken
	 */
	public static void setTokenId(String userToken) {
		ContextScope.setParameter(CONTEXT_SYS_USER_TOKEN, userToken);
	}

	/**
	 * 获取使用的设备
	 * @return
	 */
	public static Device getUserDevice() {
		Device device = ContextScope.getParameter(CONTEXT_SYS_USER_DEVICE);
		if (device == null) {
			SessionInfo sessionInfo = getSessionInfo();
			if (sessionInfo == null) {
				return Device.DEVICE_WEB;
			} else {
				return sessionInfo.getDevice();
			}
		} else {
			return device;
		}
	}

	/**
	 * 设置用户使用的设备
	 * @param userDevice
	 */
	public static void setUserDevice(Device userDevice) {
		ContextScope.setParameter(CONTEXT_SYS_USER_DEVICE, userDevice);
	}

	/**
	 * 是否web端
	 * @return
	 */
	public static boolean isWebDevice() {
		Device device = ContextHelper.getUserDevice();
		if (device == Device.DEVICE_APP) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 释放上下文信息
	 */
	public static void releaseContext() {
		ContextScope.releaseContext();
	}

}
