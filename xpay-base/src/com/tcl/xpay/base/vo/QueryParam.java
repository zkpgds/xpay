package com.tcl.xpay.base.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.tcl.xpay.base.model.UserToken;

/**
 * <br>
 * <b>功能：</b>查询参数<br>
 */
@SuppressWarnings("serial")
public class QueryParam implements Serializable {

	private String userToken; 
	
	private UserToken userTokenInfo;
	
	public UserToken getUserTokenInfo() {
		return userTokenInfo;
	}

	public void setUserTokenInfo(UserToken userTokenInfo) {
		this.userTokenInfo = userTokenInfo;
	}

	private Object defaultParamBean;

	private PageBean pageBean;

	private OrderBean orderBean;

	/**
	 * 其他的过滤条件
	 */
	private Map<String, Object> extendParams = new HashMap<String, Object>();
	

	public QueryParam() {

	}

	public QueryParam(Object defaultParamBean) {
		this.defaultParamBean = defaultParamBean;
	}

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public OrderBean getOrderBean() {
		return orderBean;
	}

	public void setOrderBean(OrderBean orderBean) {
		this.orderBean = orderBean;
	}

	public Object getParamBean() {
		return defaultParamBean;
	}

	public void setParamBean(Object paramBean) {
		this.defaultParamBean = paramBean;
	}

	public Map<String, Object> getExtendParams() {
		return extendParams;
	}

	public void addExtendParam(String extendParamKey, Object extendParamValue) {
		this.extendParams.put(extendParamKey, extendParamValue);
	}

	public void removeExtendParam(String extendParamKey) {
		this.extendParams.remove(extendParamKey);
	}

}
