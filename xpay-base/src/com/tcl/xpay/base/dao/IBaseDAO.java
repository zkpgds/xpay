package com.tcl.xpay.base.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.tcl.xpay.base.vo.QueryParam;

/**
 * <br>
 * <b>功能：</b>详细的功能描述<br>
 */
public interface IBaseDAO {
	/**
	 * 根据param条件查询数据
	 * 
	 * @param param
	 * @return
	 */
	<T> List<T> findBy(Object param);

	/**
	 * 根据条件查询唯一数据记录
	 * 
	 * @param param
	 * @return
	 */
	<T> T findUniqueBy(Object param);

	/**
	 * 是否存在指定条件的查询结果
	 * 
	 * @param param
	 * @return
	 */
	boolean hasBy(Object param);

	/**
	 * 根据queryParam条件查询分页数据
	 * 
	 * @param queryParam
	 *            查询条件
	 * @return
	 */
	<T> List<T> findPage(QueryParam queryParam);

	/**
	 * 根据queryParam条件查询总页数
	 * 
	 * @param queryParam
	 *            查询条件
	 * @return
	 */
	long findPageCount(QueryParam queryParam);

	/**
	 * 根据queryParam条件查询分页数据
	 * 
	 * @param queryParam
	 *            查询条件
	 * @param rowBounds
	 * @return
	 */
	<T> List<T> findPage(QueryParam queryParam, RowBounds rowBounds);

}
