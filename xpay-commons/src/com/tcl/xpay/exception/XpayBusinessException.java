package com.tcl.xpay.exception;


/**
 * <br>
 * <b>功能：</b>业务异常类<br>
 */
@SuppressWarnings("serial")
public class XpayBusinessException extends XpayRuntimeException {

	/**
	 * 错误代码
	 */
	private String errorCode;

	/**
	 * @param errorCode 错误代码
	 * @param message 错误信息
	 */
	public XpayBusinessException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public XpayBusinessException(String errorCode, String message, Throwable throwable) {
		super(message, throwable);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

}
