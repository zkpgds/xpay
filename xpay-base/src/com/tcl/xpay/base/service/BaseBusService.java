package com.tcl.xpay.base.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;
import org.springside.modules.mapper.BeanMapper;
import org.springside.modules.utils.Reflections;

import com.tcl.xpay.base.dao.IBaseEntityDAO;
import com.tcl.xpay.base.model.BaseEntity;
import com.tcl.xpay.base.model.UserToken;
import com.tcl.xpay.base.vo.BaseVO;

/**
 * <br>
 * <b>功能：</b>service基类<br>
 */
public abstract class BaseBusService<E extends BaseEntity, V extends BaseVO> extends BaseService<E, V> implements IBaseBusService<V> {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public V findById(Serializable id) {
		return getDao().findById(id);
	}

	@Override
	public int deleteById(UserToken userTokenInfo, Serializable id) {
		return getDao().deleteById(id);
	}

	@Override
	public int deleteByIds(UserToken userTokenInfo, Serializable[] ids) {
		return getDao().deleteByIds(ids);
	}

	@Override
	public int saveOrUpdate(UserToken userToken, V maindata) {
		if (Reflections.getFieldValue(maindata, getPKName()) == null) {
			return insert(userToken, maindata);
		} else {
			return update(userToken, maindata);
		}
	}

	@Override
	public int insert(UserToken userTokenInfo, V maindata) {
		E entity = BeanMapper.map(maindata, getEntityClazz());
		int result = getDao().insert(entity);
		setPKValue(entity, maindata);
		return result;
	}

	@Override
	public void batchInsert(UserToken userTokenInfo, List<V> maindatas) {
		List<E> entitys = new ArrayList<E>();
		for (V v : maindatas) {
			E entity = BeanMapper.map(v, getEntityClazz());
			entitys.add(entity);
		}
		int result = getDao().batchInsert(entitys);
	}

	protected void setPKValue(E entity, V maindata) {
		try {
			String pkName = getPKName();
			Object keyValue = Reflections.getFieldValue(entity, pkName);
			Reflections.setFieldValue(maindata, pkName, keyValue);
		} catch (Exception e) {
			logger.error("设置主键值错误", e);
		}
	}

	@Override
	public int update(UserToken userTokenInfo, V maindata) {
		E entity = BeanMapper.map(maindata, getEntityClazz());
		return getDao().update(entity);
	}

	/*
	 * @Override public List<V> findPage(QueryParam queryParam) { return
	 * getDao().findPage(queryParam); }
	 */

	@SuppressWarnings("unchecked")
	protected Class<E> getEntityClazz() {
		return Reflections.getClassGenricType(getClass(), 0);
	}

	@SuppressWarnings("unchecked")
	protected Class<V> getBusInfoClazz() {
		return Reflections.getClassGenricType(getClass(), 1);
	}

	protected Class<?> getPKType() {
		return ReflectionUtils.findField(getEntityClazz(), getPKName()).getType();
	}

	protected String getPKName() {
		return "id";
	}

	protected abstract IBaseEntityDAO<E, V> getDao();
}
