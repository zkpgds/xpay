package com.tcl.xpay.exception;

/**
 * <br>
 * <b>功能：</b>登录相关错误，由spring拦截，返回到登录页面<br>
 */
public class XpayLoginException extends RuntimeException{

    public XpayLoginException(String message){
        super(message);
    }

    public XpayLoginException(String message, Throwable throwable){
        super(message, throwable);
    }
}
