package com.tcl.xpay.base.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.query.Param;

import com.tcl.xpay.base.model.BaseEntity;
import com.tcl.xpay.base.vo.BaseVO;

/**
 * <br>
 * <b>功能：</b>详细的功能描述<br>
 */
public interface IBaseEntityDAO<E extends BaseEntity, V extends BaseVO> extends IBaseDAO {

	/**
	 * 根据id获取实体
	 * 
	 * @param id
	 *            实体id
	 * @return 实体
	 */
	V findById(Serializable id);

	/**
	 * 根据商户号获取实体
	 * 
	 * @param id
	 *            实体id
	 * @return 实体
	 */
	V findByMchNo(Serializable mchNo);

	/**
	 * 根据id查询
	 * 
	 * @param ids
	 * @return
	 */
	List<V> findByIds(@Param("ids") Serializable... ids);

	/**
	 * 根据id删除实体
	 * 
	 * @param id
	 * @return
	 */
	int deleteById(Serializable id);

	/**
	 * 根据id删除实体
	 * 
	 * @param ids
	 * @return
	 */
	int deleteByIds(@Param("ids") Serializable... ids);

	/**
	 * 根据paramBean条件进行删除
	 * 
	 * @param paramBean
	 * @return
	 */
	int deleteBy(E paramBean);

	/**
	 * 新增
	 * 
	 * @param entity
	 * @return 保存数据的记录数
	 */
	int insert(Object entity);

	/**
	 * 批量新增
	 * 
	 * @param entities
	 *            实体集合
	 */
	int batchInsert(@Param("entities") List<?> entities);

	/**
	 * 更新
	 * 
	 * @param entity
	 *            实体
	 * @return 更新数据的记录数
	 */
	int update(Object entity);

	/**
	 * 批量更新
	 * 
	 * @param entities
	 */
	void batchUpdate(@Param("entities") List<E> entities);

}
