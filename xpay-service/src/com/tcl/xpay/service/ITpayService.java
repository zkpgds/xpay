/**
 * com.tcl.xpay.service.ITpayService
 */
package com.tcl.xpay.service;

import java.io.Serializable;

import com.tcl.xpay.base.service.IBaseBusService;
import com.tcl.xpay.vo.TpayVO;

/**
 * <br>
 * <b>功能：</b>Service<br>
 */
public interface ITpayService extends IBaseBusService<TpayVO> {
	/**
	 * 查询支付信息
	 * 
	 * @param id
	 * @return
	 */
	TpayVO findVO(Serializable id);

	/**
	 * 更新支付信息
	 * 
	 * @param VO
	 * @return
	 */
	int updateById(TpayVO VO);
}
