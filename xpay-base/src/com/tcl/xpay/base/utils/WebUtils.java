package com.tcl.xpay.base.utils;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springside.modules.web.Servlets;

import com.alibaba.fastjson.JSON;
import com.tcl.xpay.base.view.DataGridJsonInfo;
import com.tcl.xpay.base.vo.PageBean;
import com.tcl.xpay.base.vo.PageResult;
import com.tcl.xpay.exception.XpayRuntimeException;

/**
 * <br>
 * <b>功能：</b>web工具类<br>
 */
public class WebUtils {

	// -- header 常量定义 --//
	private static final String HEADER_ENCODING = "encoding";
	private static final String HEADER_NOCACHE = "no-cache";
	private static final String DEFAULT_ENCODING = "UTF-8";
	private static final boolean DEFAULT_NOCACHE = true;

	/**
	 * <br>
	 * <b>功能：</b>判断是否异步请求<br>
	 *
	 * @param request
	 * @return
	 */
	public final static boolean isAsynRequest(HttpServletRequest request) {
		String requestedWith = request.getHeader("x-requested-with");
		// 表明是一个Ajax的post请求，并且不是使用隐藏的iframe实现的
		if ("XMLHttpRequest".equalsIgnoreCase(requestedWith)) {
			return true;
		}
		return false;
	}

	/**
	 * 直接输出内容的简便函数.
	 * <p/>
	 * eg. render("text/plain", "hello", "encoding:GBK"); render("text/plain",
	 * "hello", "no-cache:false"); render("text/plain", "hello", "encoding:GBK",
	 * "no-cache:false");
	 *
	 * @param headers
	 *            可变的header数组，目前接受的值为"encoding:"或"no-cache:",默认值分别为UTF-8和true.
	 */
	public static void render(final HttpServletResponse response, final String contentType, final String content, final String... headers) {
		initResponseHeader(response, contentType, headers);
		try {
			response.getWriter().write(content);
			response.getWriter().flush();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * 直接输出文本.
	 *
	 * @see #render(String, String, String...)
	 */
	public static void renderText(final HttpServletResponse response, final String text, final String... headers) {
		render(response, "text/plain", text, headers);
	}

	/**
	 * 直接输出HTML.
	 *
	 * @see #render(String, String, String...)
	 */
	public static void renderHtml(final HttpServletResponse response, final String html, final String... headers) {
		render(response, "text/html", html, headers);
	}

	/**
	 * 直接输出XML.
	 *
	 * @see #render(String, String, String...)
	 */
	public static void renderXml(final HttpServletResponse response, final String xml, final String... headers) {
		render(response, "text/xml", xml, headers);
	}

	/**
	 * 直接输出JSON.
	 *
	 * @param jsonString
	 *            json字符串.
	 * @see #render(String, String, String...)
	 */
	public static void renderJson(final HttpServletResponse response, final String jsonString, final String... headers) {
		render(response, "application/json", jsonString, headers);
	}

	/**
	 * 直接输出JSON,使用Jackson转换Java对象.
	 *
	 * @param data
	 *            可以是List<POJO>, POJO[], POJO, 也可以Map名值对.
	 * @see #render(String, String, String...)
	 */
	public static void renderJson(final HttpServletResponse response, final Object data, final String... headers) {
		initResponseHeader(response, "application/json", headers);
		try {
			JSON.writeJSONStringTo(data, response.getWriter());
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * 直接输出支持跨域Mashup的JSONP.
	 *
	 * @param callbackName
	 *            callback函数名.
	 * @param object
	 *            Java对象,可以是List<POJO>, POJO[], POJO ,也可以Map名值对, 将被转化为json字符串.
	 */
	public static void renderJsonp(final HttpServletResponse response, final String callbackName, final Object object, final String... headers) {
		String jsonString = null;
		if (object instanceof String) {
			jsonString = (String) object;
		} else {
			jsonString = JSON.toJSONString(object);
		}
		String result = new StringBuilder().append(callbackName).append("(").append(jsonString).append(");").toString();
		// 渲染Content-Type为javascript的返回内容,输出结果为javascript语句,
		// 如callback197("{html:'Hello World!!!'}");
		render(response, "text/javascript", result, headers);
	}

	/**
	 * 分析并设置contentType与headers.
	 */
	private static void initResponseHeader(final HttpServletResponse response, final String contentType, final String... headers) {
		// 分析headers参数
		String encoding = DEFAULT_ENCODING;
		boolean noCache = DEFAULT_NOCACHE;
		for (String header : headers) {
			String headerName = StringUtils.substringBefore(header, ":");
			String headerValue = StringUtils.substringAfter(header, ":");

			if (StringUtils.equalsIgnoreCase(headerName, HEADER_ENCODING)) {
				encoding = headerValue;
			} else if (StringUtils.equalsIgnoreCase(headerName, HEADER_NOCACHE)) {
				noCache = Boolean.parseBoolean(headerValue);
			} else {
				throw new IllegalArgumentException(headerName + "不是一个合法的header类型");
			}
		}
		// 设置headers参数
		String fullContentType = contentType + ";charset=" + encoding;
		response.setContentType(fullContentType);
		if (noCache) {
			Servlets.setNoCacheHeader(response);
		}
	}

	/**
	 * 获取语言信息
	 * 
	 * @param request
	 * @return
	 */
	public static Locale getLocal(HttpServletRequest request) {
		return RequestContextUtils.getLocale(request);
	}

	/**
	 * 根据参数前缀，构造bean
	 *
	 * @param request
	 * @param prefix
	 * @param bean
	 */
	public static <T> T populate(HttpServletRequest request, String prefix, T bean) {
		try {
			prefix = StringUtils.isNotBlank(prefix) ? prefix + "." : prefix;
			BeanUtils.populate(bean, Servlets.getParametersStartingWith(request, prefix));
		} catch (Exception e) {
			throw new XpayRuntimeException("populate fail", e);
		}
		return bean;
	}

	/**
	 * 构建datagrid的JSON信息
	 *
	 * @param rows
	 * @param page
	 * @return
	 */
	public static DataGridJsonInfo createDataGridInfo(List<?> rows, PageBean page) {
		return createDataGridInfo(rows, page, null, null);
	}

	public static DataGridJsonInfo createDataGridInfo(PageResult<?> pageResult) {
		return createDataGridInfo(pageResult.getDataList(), pageResult.getPageBean(), null, null);
	}

	/**
	 * 构建datagrid的JSON信息
	 *
	 * @param rows
	 * @param page
	 * @param footer
	 * @param other
	 * @return
	 */
	public static DataGridJsonInfo createDataGridInfo(List<?> rows, PageBean page, List<?> footer, Map<String, Object> other) {
		DataGridJsonInfo dataGridJsonInfo = null;
		if (page == null) {
			dataGridJsonInfo = new DataGridJsonInfo(rows, null, null);
		} else {
			dataGridJsonInfo = new DataGridJsonInfo(rows, page.getPageNo(), page.getTotalCount());
		}
		dataGridJsonInfo.setFooter(footer);
		dataGridJsonInfo.setOther(other);
		return dataGridJsonInfo;
	}

	/**
	 * 获取访问的真实ip
	 *
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Forwarded-For");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}
