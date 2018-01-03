package com.tcl.xpay.base.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springside.modules.utils.Reflections;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.tcl.xpay.base.context.ContextHelper;
import com.tcl.xpay.base.model.UserToken;
import com.tcl.xpay.base.service.IBaseBusService;
import com.tcl.xpay.base.utils.WebUtils;
import com.tcl.xpay.base.view.DataGridJsonInfo;
import com.tcl.xpay.base.view.ResultJsonInfo;
import com.tcl.xpay.base.vo.BaseVO;
import com.tcl.xpay.base.vo.QueryParam;

/**
 * <br>
 * <b>功能：</b>增、删、改和查的基础controller类<br>
 */
public abstract class BaseCrudController<V extends BaseVO, T extends QueryParam> extends BaseController {

	protected abstract String getBaseUrl();

	protected abstract IBaseBusService<V> getBaseService();

	/**
	 * 列表
	 * 
	 * @param userToken
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(String userToken, HttpServletRequest request) {

		return renderView(getBaseUrl() + "/list", null);
	}

	/**
	 * 新增
	 * 
	 * @param userToken
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(String userToken, HttpServletRequest request) {
		return renderView(getBaseUrl() + "/edit", null);
	}

	/**
	 * 查看
	 * 
	 * @param userToken
	 *            用户token
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView view(String userToken, String id, HttpServletRequest request) {
		V maindata = getBaseService().findById((Serializable) ConvertUtils.convert(id, getPKType()));
		return renderView(getBaseUrl() + "/edit", "maindata", maindata);
	}

	/**
	 * 删除
	 * 
	 * @param userToken
	 *            用户token
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ResultJsonInfo delete(String userToken, String id, HttpServletRequest request) {
		UserToken user = ContextHelper.getUserToken(userToken);
		getBaseService().deleteByIds(user, (Serializable[]) ConvertUtils.convert(id.split(","), getPKType()));
		return new ResultJsonInfo(true, "删除成功");
	}

	/**
	 * 保存
	 * 
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public ResultJsonInfo save(@Valid V entity, HttpServletRequest request) {
		String userToken = request.getParameter("userToken");
		UserToken user = ContextHelper.getUserToken(userToken);
		getBaseService().saveOrUpdate(user, entity);
		return new ResultJsonInfo(true, "保存成功");
	}

	/**
	 * 加载grid数据
	 * 
	 * @param queryParam
	 * @return
	 */
	@RequestMapping("loadData")
	@ResponseBody
	public DataGridJsonInfo loadData(T queryParam, HttpServletRequest request) {
		// 获取当前用户
		UserToken userToken = ContextHelper.getUserToken();
		queryParam.setUserTokenInfo(userToken);
		return WebUtils.createDataGridInfo(getBaseService().findPage(queryParam));
	}

	/**
	 * 获取业务信息vo类型
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected Class<V> getBusInfoClazz() {
		return Reflections.getClassGenricType(getClass(), 0);
	}

	/**
	 * 获取主键类型
	 * 
	 * @return
	 */
	protected Class<?> getPKType() {
		return ReflectionUtils.findField(getBusInfoClazz(), "id").getType();
	}

	/**
	 * 判断是否显示所有状态
	 * 
	 * @param request
	 * @return
	 */
	protected boolean isAllStatus(HttpServletRequest request) {
		String userToken = request.getParameter("userToken");
		UserToken user = ContextHelper.getUserToken(userToken);
		String allStatus = request.getParameter("allStatus");
		if (user.getUserCode().equals("admin") && "true".equals(allStatus))
			return true;
		return false;
	}

	/**
	 * 获取userToken信息
	 * 
	 * @param request
	 * @return
	 */
	protected UserToken getUserToken(HttpServletRequest request) {
		String userToken = request.getParameter("userToken");
		UserToken user = ContextHelper.getUserToken(userToken);
		return user;
	}

	/**
	 * 导入Excel
	 * 
	 * @param request
	 * @return
	 * @throws SAXException
	 * @throws IOException
	 * @throws InvalidFormatException
	 */
	@RequestMapping(value = "/readExcel", method = RequestMethod.POST)
	@ResponseBody
	public ResultJsonInfo readExcel(MultipartFile file, HttpServletRequest request) throws InvalidFormatException, IOException, SAXException {
		return null;
	}

	/**
	 * 导出Excel
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/reportExcel", method = RequestMethod.POST)
	public ResultJsonInfo reportExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return null;
	}

}
