package com.tcl.xpay.base.view;

import com.tcl.xpay.base.model.ToStringBean;

/**
 * <br>
 * <b>功能：</b>JSON结果信息<br>
 */
public class ResultJsonInfo extends ToStringBean {

	private boolean success;
	private Object content;

	public ResultJsonInfo(boolean success, Object content) {
		this.success = success;
		this.content = content;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

}
