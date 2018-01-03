package com.tcl.xpay.base.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * <br>
 * <b>功能：</b>session对象，会话信息相关<br>
 *     注意json序列化必须要有public的get方法或者public的属性
 */
public class SessionBean implements Serializable {

    private String sessionId;

    /**
     *  是否已经验证用户，控制session用户是否已登录的属性
     */
    private boolean authcFlag = false;

    private String userTokenId;//令牌 ,认证后才有令牌

    private String host;

    private String userDevice;//用户设备

    private long expireTime = 1800000; //默认1800秒

    private long loginTime; //毫秒

    private long updateTime; //毫秒



    //json的序列化有缺陷。value不要放太复杂的类
    //不建议放太多东西进去
    //如果要放入其它对象，先用json转字符串
    private Map<String, String> attribute = new HashMap<String, String>();

    public SessionBean(){

    }

    public SessionBean(String sessionId){
        this.sessionId = sessionId;
    }


    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public boolean isAuthcFlag() {
        return authcFlag;
    }

    public void setAuthcFlag(boolean authcFlag) {
        this.authcFlag = authcFlag;
    }

    public String getUserTokenId() {
        return userTokenId;
    }

    public void setUserTokenId(String userTokenId) {
        this.userTokenId = userTokenId;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUserDevice() {
        return userDevice;
    }

    public void setUserDevice(String userDevice) {
        this.userDevice = userDevice;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(long loginTime) {
        this.loginTime = loginTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }


    public Map<String, String> getAttribute() {
        return attribute;
    }

    public void setAttribute(Map<String, String> attribute) {
        this.attribute = attribute;
    }

    public String getAttributes(String key) {
        if(key==null || key.trim().equals("")){
            return null;
        }
        return attribute.get(key);
    }

    public void setAttribute(String key, String value) {
        if(key!=null && !key.trim().equals("")){
            attribute.put(key, value);
        }
    }
}
