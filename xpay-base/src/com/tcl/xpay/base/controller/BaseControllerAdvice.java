package com.tcl.xpay.base.controller;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;
import com.tcl.xpay.base.context.ContextHelper;
import com.tcl.xpay.base.utils.WebUtils;
import com.tcl.xpay.base.view.ViewRenderer;
import com.tcl.xpay.exception.XpayBusinessException;
import com.tcl.xpay.exception.XpayLoginException;
import com.tcl.xpay.exception.XpayRightException;
import com.tcl.xpay.utils.DateUtils;
import com.tcl.xpay.utils.NumberUtils;

/**
 * <br>
 * <b>功能：</b>处理类型转换以及异常<br>
 */
@ControllerAdvice
public class BaseControllerAdvice {

	private Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 获取视图渲染器
	 */
	@Autowired
	private ViewRenderer viewRenderer;

	/**
	 * 处理数据类型的装换
	 *
	 * @param binder
	 * @param webRequest
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest webRequest) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		binder.registerCustomEditor(Short.class, new CustomNumberEditor(Short.class, true));
		binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
		binder.registerCustomEditor(Long.class, new CustomNumberEditor(Long.class, true));
		binder.registerCustomEditor(Float.class, new CustomNumberEditor(Float.class, true));
		binder.registerCustomEditor(Double.class, new CustomNumberEditor(Double.class, true));
		binder.registerCustomEditor(BigDecimal.class, new CustomNumberEditor(BigDecimal.class, new DecimalFormat(NumberUtils.PATTERN_DEFAULT_MONEY), true));
		binder.registerCustomEditor(BigInteger.class, new CustomNumberEditor(BigInteger.class, true));
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(new SimpleDateFormat(DateUtils.PATTERN_DEFAULT_DATETIME), true));
		binder.registerCustomEditor(java.sql.Timestamp.class, new CustomDateEditor(new SimpleDateFormat(DateUtils.PATTERN_DEFAULT_DATETIME), true));
	}

	/**
	 * 默认错误异常处理
	 *
	 * @param request
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception ex) {
		logger.error("后台异常", ex);
		Map<String, Object> errorInfo = Maps.newHashMap();

		String errorMsg = null;
		if (ex instanceof XpayBusinessException) {
			errorMsg = StringUtils.defaultIfBlank(ex.getMessage(), "系统异常。");
		} else {
			String message = ex.getMessage();
			if (message != null && message.indexOf(XpayBusinessException.class.getName()) >= 0) {
				errorMsg = StringUtils.substringBefore(message, "\r");
				errorMsg = StringUtils.substringBefore(errorMsg, "\n");
				errorMsg = StringUtils.substringAfter(errorMsg, XpayBusinessException.class.getName() + ":").trim();
			} else {
				errorMsg = message;
			}
		}

		errorInfo.put("message", StringUtils.defaultIfBlank(errorMsg, "系统异常。"));

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("error", errorInfo);

		if (ContextHelper.isWebDevice() && WebUtils.isAsynRequest(request)) {// 异步请求异常
			return viewRenderer.renderJsonView(model);
		} else {
			// 根据不同错误转向不同页面
			if (ex instanceof XpayRightException) {
				return new ModelAndView("/common/503", model);
			} else if (ex instanceof XpayLoginException) {
				return new ModelAndView("/common/relogin", model);
			} else if (ex instanceof XpayBusinessException) {
				return new ModelAndView("/common/500", model);
			} else {
				return viewRenderer.render("/common/500", model);
			}
		}

	}

}
