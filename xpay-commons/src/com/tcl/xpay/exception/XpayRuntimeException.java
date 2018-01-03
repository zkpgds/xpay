package com.tcl.xpay.exception;

/**
 * <br>
 * <b>功能：</b>运行时异常<br>
 */
@SuppressWarnings("serial")
public class XpayRuntimeException extends RuntimeException {

	public XpayRuntimeException(String message) {
		super(message);
	}

	public XpayRuntimeException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
