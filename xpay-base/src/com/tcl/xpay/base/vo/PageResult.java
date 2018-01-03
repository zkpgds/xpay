package com.tcl.xpay.base.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <br>
 * <b>功能：</b>查询分页结果<br>
 */
public class PageResult<V extends BaseVO> implements Serializable {

	private List<V> dataList = new ArrayList<V>();

	private PageBean pageBean;

	public PageResult(List<V> dataList, PageBean pageBean) {
		super();
		this.dataList = dataList;
		this.pageBean = pageBean;
	}
	
	public PageResult(){
		
	}

	public List<V> getDataList() {
		return dataList;
	}

	public void setDataList(List<V> dataList) {
		this.dataList = dataList;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

}
