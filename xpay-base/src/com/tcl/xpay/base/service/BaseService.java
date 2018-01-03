package com.tcl.xpay.base.service;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.tcl.xpay.base.dao.IBaseEntityDAO;
import com.tcl.xpay.base.model.BaseEntity;
import com.tcl.xpay.base.vo.BaseVO;
import com.tcl.xpay.base.vo.PageBean;
import com.tcl.xpay.base.vo.PageResult;
import com.tcl.xpay.base.vo.QueryParam;

/**
 * <br>
 * <b>功能：</b>service基类<br>
 */
public abstract class BaseService<E extends BaseEntity, V extends BaseVO> {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	public V findById(Serializable id) {
		return getDao().findById(id);
	}

	/**
	 * 根据ids查询
	 * 
	 * @param ids
	 * @return
	 */
	public List<V> findByIds(Serializable... ids) {
		return getDao().findByIds(ids);
	}

	/**
	 * 根据id删除实体
	 * 
	 * @return 刪除记录数
	 */
	public int deleteById(Serializable id) {
		return getDao().deleteById(id);
	}

	/**
	 * 根据id删除实体
	 * 
	 * @param ids
	 * @return
	 */
	public int deleteByIds(Serializable... ids) {
		return getDao().deleteByIds(ids);
	}

	/**
	 * 根据paramBean条件进行删除
	 * 
	 * @param paramBean
	 * @return
	 */
	public int deleteBy(E paramBean) {
		return getDao().deleteBy(paramBean);
	}

	/**
	 * 新增
	 * 
	 * @param entity
	 *            实体
	 * @return 保存数据的记录数
	 */
	public int insert(E entity) {
		return getDao().insert(entity);
	}

	public int insert(V entity) {
		return getDao().insert(entity);
	}

	/**
	 * 批量新增
	 * 
	 * @param entities
	 *            实体集合
	 */
	public void batchInsert(List<E> entities) {
		if (entities.isEmpty()) {
			return;
		}
		getDao().batchInsert(entities);
	}

	/**
	 * 更新
	 * 
	 * @param entity
	 *            实体
	 * @return 更新数据的记录数
	 */
	public int update(E entity) {
		return getDao().update(entity);
	}

	/**
	 * 批量更新
	 * 
	 * @param entities
	 *            实体集合
	 */
	public void batchUpdate(List<E> entities) {
		getDao().batchUpdate(entities);
	}

	/**
	 * 根据param条件查询数据
	 * 
	 * @param param
	 * @return
	 */
	public List<V> findBy(Object param) {
		return getDao().findBy(param);
	}

	/**
	 * 根据条件查询唯一数据记录
	 * 
	 * @param param
	 * @return
	 */
	public V findUniqueBy(Object param) {
		return getDao().findUniqueBy(param);
	}

	/**
	 * 根据BaseQuery条件查询分页数据
	 * 
	 * @param queryParam
	 *            查询条件
	 * @return
	 */
	public PageResult<V> findPage(QueryParam queryParam) {
		PageResult<V> pageResult = new PageResult<V>();
		List<V> dataList;
		if (queryParam != null && queryParam.getPageBean() != null) {
			PageBean pageBean = queryParam.getPageBean();
			queryParam.addExtendParam("_pageCountQuery_", "true");
			long totalCount = getDao().findPageCount(queryParam);
			queryParam.removeExtendParam("_pageCountQuery_");
			if (totalCount <= 0) {
				dataList = Lists.newArrayList();
			} else {
				dataList = getDao().findPage(queryParam, new RowBounds(pageBean.getFirst() - 1, pageBean.getPageSize()));
			}
			pageBean.setTotalCount(totalCount);
			pageResult.setPageBean(pageBean);
		} else {
			dataList = getDao().findPage(queryParam);
		}
		pageResult.setDataList(dataList);
		return pageResult;
	}

	protected abstract IBaseEntityDAO<E, V> getDao();
}
