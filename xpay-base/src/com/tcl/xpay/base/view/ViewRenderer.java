package com.tcl.xpay.base.view;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.google.common.collect.Maps;
import com.tcl.xpay.base.context.ContextHelper;

/**
 * <br>
 * <b>功能：</b>视图渲染器<br>
 */
@Component
public class ViewRenderer {

	/**
	 * 构建视图
	 * @param viewName
	 * @param modelMap
	 * @return
	 */
	public ModelAndView render(String viewName, Map<String, Object> modelMap) {
		if (ContextHelper.isWebDevice()) {
			return new ModelAndView(viewName, modelMap);
		} else {
			return renderJsonView(modelMap);
		}
	}

	/**
	 * 构建视图
	 * @param viewName
	 * @param attributeName
	 * @param attributeValue
	 * @return
	 */
	public ModelAndView render(String viewName, String attributeName, Object attributeValue) {
		Map<String, Object> modelMap = Maps.newHashMap();
		modelMap.put(attributeName, attributeValue);
		return render(viewName, modelMap);
	}

	/**
	 * 构建JSON视图
	 * @param modelMap
	 * @return
	 */
	public ModelAndView renderJsonView(Map<String, Object> modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addAllObjects(modelMap);
		modelAndView.setView(new MappingJackson2JsonView());
		return modelAndView;
	}
}
