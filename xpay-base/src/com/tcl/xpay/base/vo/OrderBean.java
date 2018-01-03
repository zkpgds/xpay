package com.tcl.xpay.base.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <br>
 * <b>功能：</b>排序bean<br>
 */
public class OrderBean implements Serializable{
	//-- 公共变量 --//
	public static final String ASC = "asc";
	public static final String DESC = "desc";

	private String[] sortFields = new String[0];
	private String[] sortTypes = new String[0];

	private List<Map<String, String>> orderList = null;

	/**
	 * 排序字段
	 */
	protected String orderBy = null;
	/**
	 * asc or desc
	 */
	protected String order = null;

	public String[] getSortFields() {
		return sortFields;
	}

	public void setSortFields(String[] sortFields) {
		this.sortFields = sortFields;
	}

	public String[] getSortTypes() {
		return sortTypes;
	}

	public void setSortTypes(String[] sortTypes) {
		this.sortTypes = sortTypes;
	}

	/**
	 * 获得排序字段,无默认值.多个排序字段时用','分隔.
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * 设置排序字段,多个排序字段时用','分隔.
	 */
	public void setOrderBy(final String orderBy) {
		if (isNotBlank(orderBy)) {
			sortFields = orderBy.split(",");
		}
	}

	private boolean isNotBlank(String value){
		return value != null && !"".equals(value.trim());
	}
	
	/**
	 * 获得排序方向.
	 */
	public String getOrder() {
		return order;
	}

	/**
	 * 设置排序方式向.
	 * @param order 可选值为desc或asc,多个排序字段时用','分隔.
	 */
	public void setOrder(final String order) {
		if (isNotBlank(order)) {
			String lowcaseOrder = order.toLowerCase();

			//检查order字符串的合法值
			String[] orders = lowcaseOrder.split(",");
			for (String orderStr : orders) {
				if (!DESC.equals(orderStr) && !ASC.equals(orderStr)) {
					throw new IllegalArgumentException("排序方向" + orderStr + "不是合法值");
				}
			}
			sortTypes = orders;
		}
		//		this.order = lowcaseOrder;
	}

	public List<Map<String, String>> getOrderList() {
		if (orderList == null) {
			orderList = new ArrayList<Map<String, String>>();
			for (int i = 0; i < sortFields.length; i++) {
				String sortFiled = sortFields[i];
				String sortType = null;
				if (i > sortTypes.length - 1) {
					sortType = "asc";
				} else {
					sortType = sortTypes[i];
				}
				Map<String, String> orderMap = new HashMap<String, String>();
				orderMap.put("sortField", sortFiled);
				orderMap.put("sortType", sortType);
				orderList.add(orderMap);
			}
		}
		return orderList;
	}
}
