package com.tcl.xpay.base.view;

import java.util.List;
import java.util.Map;

import com.tcl.xpay.base.model.ToStringBean;

/**
 * <br>
 * <b>功能：</b>datagrid的JSON信息<br>
 */
public class DataGridJsonInfo extends ToStringBean {

	private List<?> rows;
	private List<?> footer;
	private Integer page;
	private Long total;
	private Map<String, ?> other;

	public DataGridJsonInfo(List<?> rows, Integer page, Long total) {
		this.rows = rows;
		this.page = page;
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	public List<?> getFooter() {
		return footer;
	}

	public void setFooter(List<?> footer) {
		this.footer = footer;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Map<String, ?> getOther() {
		return other;
	}

	public void setOther(Map<String, ?> other) {
		this.other = other;
	}

}
