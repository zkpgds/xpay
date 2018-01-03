package com.tcl.xpay.base.service;

import java.io.Serializable;
import java.util.List;

import com.tcl.xpay.base.model.UserToken;
import com.tcl.xpay.base.vo.BaseVO;
import com.tcl.xpay.base.vo.PageResult;
import com.tcl.xpay.base.vo.QueryParam;

/**
 * <br>
 * <b>功能：</b>业务service基础接口<br>
 */
public interface IBaseBusService<V extends BaseVO> {

	/**
	 * 根据id查询实体
	 * 
	 * @param id
	 * @return
	 */
	V findById(Serializable id);

	/**
	 * 根据id删除实体
	 * 
	 * @param userToken
	 * @return 刪除记录数
	 */
	int deleteById(UserToken userToken, Serializable id);

	/**
	 * 根据id删除实体
	 * 
	 * @param userToken
	 * @param ids
	 * @return
	 */
	int deleteByIds(UserToken userToken, Serializable[] ids);

	/**
	 * 保存或更新
	 * 
	 * @param userToken
	 * @param maindata
	 * @return
	 */
	int saveOrUpdate(UserToken userToken, V maindata);

	/**
	 * 新增
	 * 
	 * @param userToken
	 * @param maindata
	 *            实体
	 * @return 保存数据的记录数
	 */
	int insert(UserToken userToken, V maindata);

	/**
	 * 批量新增
	 * 
	 * @param userToken
	 * @param maindata
	 *            实体集合
	 * @return
	 */
	void batchInsert(UserToken userTokenInfo, List<V> maindatas);

	/**
	 * 更新
	 * 
	 * @param userToken
	 * @param maindata
	 *            实体
	 * @return 更新数据的记录数
	 */
	int update(UserToken userToken, V maindata);

	/**
	 * 根据BaseQuery条件查询分页数据
	 * 
	 * @param queryParam
	 *            查询条件
	 * @return
	 */
	PageResult<V> findPage(QueryParam queryParam);

}
