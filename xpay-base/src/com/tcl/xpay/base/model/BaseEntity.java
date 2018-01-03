package com.tcl.xpay.base.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * <br>
 * <b>功能：</b> 统一定义id的entity基类.<br>
 * 基类统一定义id的属性名称、数据类型<br>
 */
@SuppressWarnings("serial")
public class BaseEntity implements Serializable {

	private Date updateTime;

	public BaseEntity() {

	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
