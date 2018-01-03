package com.tcl.xpay.base.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * <br>
 * <b>功能：</b>token对象，用户信息相关<br>
 */
public class SsoTokenBean implements Serializable {

    private String tokenId;

    private String webSessionId;//同一时间只能有一个pc浏览器登录

    private String userCode;

    private String stringInstOfUser;//（frame_api模块）cn.rmt.framework.bean.UserToken对象的fastjson字符串

    private long expireMills;//失效时长，毫秒

    private long createTime;//创建时间，毫秒

    private long updateTime;//创建时间，毫秒

    private Map<String, String> rolesMap = null; //用户角色
    private Map<String, String> permsMap = null; //用户权限

    //json的序列化有缺陷。value不要放太复杂的类
    //不建议放太多东西进去
    //如果要放入其它对象，先用json转字符串
    private Map<String, String> attribute = new HashMap<String, String>();

    public SsoTokenBean(){

    }

    public SsoTokenBean(String tokenId){
        this.tokenId = tokenId;
    }


    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getWebSessionId() {
        return webSessionId;
    }

    public void setWebSessionId(String webSessionId) {
        this.webSessionId = webSessionId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getStringInstOfUser() {
        return stringInstOfUser;
    }

    public void setStringInstOfUser(String stringInstOfUser) {
        this.stringInstOfUser = stringInstOfUser;
    }

    public long getExpireMills() {
        return expireMills;
    }

    public void setExpireMills(long expireMills) {
        this.expireMills = expireMills;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }


    public Map<String, String> getPermsMap() {
        return permsMap;
    }

    public void setPermsMap(Map<String, String> permsMap) {
        this.permsMap = permsMap;
    }

    public Map<String, String> getRolesMap() {
        return rolesMap;
    }

    public void setRolesMap(Map<String, String> rolesMap) {
        this.rolesMap = rolesMap;
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
