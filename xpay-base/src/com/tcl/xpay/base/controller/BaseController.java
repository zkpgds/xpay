package com.tcl.xpay.base.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.tcl.xpay.base.view.ViewRenderer;


/**
 * <br>
 * <b>功能：</b>控制器基类<br>
 */
public abstract class BaseController implements ApplicationContextAware {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected ApplicationContext applicationContext;

	/**
	 * 构建视图
	 * @param viewName
	 * @param modelMap
	 * @return
	 */
	protected ModelAndView renderView(String viewName, Map<String, Object> modelMap) {
		return getViewRenderer().render(viewName, modelMap);
	}

	/**
	 * 构建视图
	 * @param viewName
	 * @param attributeName
	 * @param attributeValue
	 * @return
	 */
	protected ModelAndView renderView(String viewName, String attributeName, Object attributeValue) {
		return getViewRenderer().render(viewName, attributeName, attributeValue);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	/**
	 * 获取视图渲染器
	 * @return
	 */
	private ViewRenderer getViewRenderer() {
		return applicationContext.getBean(ViewRenderer.class);
	}

}
