/**
 * com.tcl.xpay.dao.ITpayDAO
 */
package com.tcl.xpay.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.tcl.xpay.base.dao.IBaseEntityDAO;
import com.tcl.xpay.model.Tpay;
import com.tcl.xpay.vo.TpayVO;

/**
 * <br>
 * <b>功能：</b>DAO接口<br>
 */
@Repository
public interface ITpayDAO extends IBaseEntityDAO<Tpay, TpayVO> {

	TpayVO findVO(Serializable id);

	int updateById(TpayVO VO);
}
