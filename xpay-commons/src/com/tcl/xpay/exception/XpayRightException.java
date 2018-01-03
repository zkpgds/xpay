package com.tcl.xpay.exception;

/**
 * <br>
 * <b>功能：</b>权限异常<br>
 */
public class XpayRightException extends RuntimeException{

    public XpayRightException(String message){
        super(message);
    }

    public XpayRightException(String message, Throwable throwable){
        super(message, throwable);
    }
}
