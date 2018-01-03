/**
 * com.tcl.xpay.vo.TpayQueryParam
 */
package com.tcl.xpay.vo;

import com.tcl.xpay.base.vo.QueryParam;

/**
 * <br>
 * <b>功能：</b>查询参数<br>
 */
public class TpayQueryParam extends QueryParam {

	private TpayVO tpay;

	public TpayVO getTpay() {
		return tpay;
	}

	public void setTpay(TpayVO tpay) {
		super.setParamBean(tpay);
		this.tpay = tpay;
	}

}