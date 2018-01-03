/**
 * com.tcl.xpay.service.impl.TpayService
 */
package com.tcl.xpay.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcl.xpay.base.service.BaseBusService;
import com.tcl.xpay.dao.ITpayDAO;
import com.tcl.xpay.model.Tpay;
import com.tcl.xpay.service.ITpayService;
import com.tcl.xpay.vo.TpayVO;

/**
 * <br>
 * <b>功能：</b>Service<br>
 */
@Service("tpayService")
@Transactional
public class TpayService extends BaseBusService<Tpay, TpayVO> implements ITpayService {

	@Autowired
	private ITpayDAO dao;

	@Override
	protected ITpayDAO getDao() {
		return dao;
	}

	@Override
	public TpayVO findVO(Serializable id) {
		return getDao().findVO(id);
	}

	@Override
	public int updateById(TpayVO VO) {
		return getDao().updateById(VO);
	}
}
