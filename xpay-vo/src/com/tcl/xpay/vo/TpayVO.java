/**
 * com.tcl.xpay.vo.TpayVO
 */
package com.tcl.xpay.vo;

import com.tcl.xpay.base.vo.BaseVO;

/**
 * <br>
 * <b>功能：</b>实体类<br>
 */
@SuppressWarnings("serial")
public class TpayVO extends BaseVO {

	/**
	 * 主键 db_column: id
	 */
	private java.lang.Integer id;
	/**
	 * 商户编号 db_column: merchant_no
	 */
	private java.lang.String merchantNo;
	/**
	 * 业务订单号 db_column: out_trade_no
	 */
	private java.lang.String outTradeNo;
	/**
	 * 业务订单创建时间 db_column: out_trade_time
	 */
	private java.util.Date outTradeTime;
	/**
	 * 通知业务时间 db_column: out_notify_time
	 */
	private java.util.Date outNotifyTime;
	/**
	 * 业务回调地址 db_column: out_notify_url
	 */
	private java.lang.String outNotifyUrl;
	/**
	 * 业务订单来源 db_column: out_trade_source
	 */
	private java.lang.String outTradeSource;
	/**
	 * 业务扩展（JSON串） db_column: out_trade_ext
	 */
	private java.lang.String outTradeExt;
	/**
	 * 优惠券id db_column: out_notify_status
	 */
	private java.lang.String outNotifyStatus;
	/**
	 * 支付订单号 db_column: trade_no
	 */
	private java.lang.String tradeNo;
	/**
	 * 支付时间 db_column: pay_time
	 */
	private java.util.Date payTime;
	/**
	 * 支付状态（待支付、已支付、退款） db_column: pay_status
	 */
	private java.lang.String payStatus;
	/**
	 * 渠道交易号（一级渠道 微信、支付宝、银行） db_column: channel_trade_no
	 */
	private java.lang.String channelTradeNo;
	/**
	 * 代理渠道交易号（通联、汇银通） db_column: channel_trade_no_proxy
	 */
	private java.lang.String channelTradeNoProxy;
	/**
	 * 渠道商户号 db_column: channel_merchant_no
	 */
	private java.lang.String channelMerchantNo;
	/**
	 * 渠道路由（通联、汇银通、微信、支付宝、银行） db_column: channel_route
	 */
	private java.lang.String channelRoute;
	/**
	 * 订单创建时间 db_column: create_time
	 */
	private java.util.Date createTime;
	/**
	 * 更新时间 db_column: update_time
	 */
	private java.util.Date updateTime;
	/**
	 * 渠道支付通知时间（例如：通联收到支付宝通知的时间，或者支付宝收到银行通知的时间） db_column: channel_pay_time
	 */
	private java.util.Date channelPayTime;
	/**
	 * 总金额 db_column: total_amount
	 */
	private java.math.BigDecimal totalAmount;
	/**
	 * 支付金额 db_column: pay_amount
	 */
	private java.math.BigDecimal payAmount;
	/**
	 * 折扣金额 db_column: discount_amount
	 */
	private java.math.BigDecimal discountAmount;
	/**
	 * 版本号 db_column: version
	 */
	private java.lang.String version;
	/**
	 * 用户id db_column: user_id
	 */
	private java.lang.String userId;
	/**
	 * 用户关联id db_column: user_rel_id
	 */
	private java.lang.String userRelId;
	/**
	 * 用户类型 db_column: user_type
	 */
	private java.lang.String userType;
	/**
	 * 用户昵称 db_column: nickname
	 */
	private java.lang.String nickname;
	/**
	 * 商户应用编码 db_column: app_id
	 */
	private java.lang.String appId;
	/**
	 * 第三方用户标识 db_column: third_app_mark
	 */
	private java.lang.String thirdAppMark;

	public TpayVO() {
	}

	public TpayVO(java.lang.Integer id) {
		this.id = id;
	}

	public java.lang.Integer getId() {
		return this.id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public java.lang.String getMerchantNo() {
		return this.merchantNo;
	}

	public void setMerchantNo(java.lang.String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public java.lang.String getOutTradeNo() {
		return this.outTradeNo;
	}

	public void setOutTradeNo(java.lang.String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public java.util.Date getOutTradeTime() {
		return this.outTradeTime;
	}

	public void setOutTradeTime(java.util.Date outTradeTime) {
		this.outTradeTime = outTradeTime;
	}

	public java.util.Date getOutNotifyTime() {
		return this.outNotifyTime;
	}

	public void setOutNotifyTime(java.util.Date outNotifyTime) {
		this.outNotifyTime = outNotifyTime;
	}

	public java.lang.String getOutNotifyUrl() {
		return this.outNotifyUrl;
	}

	public void setOutNotifyUrl(java.lang.String outNotifyUrl) {
		this.outNotifyUrl = outNotifyUrl;
	}

	public java.lang.String getOutTradeSource() {
		return this.outTradeSource;
	}

	public void setOutTradeSource(java.lang.String outTradeSource) {
		this.outTradeSource = outTradeSource;
	}

	public java.lang.String getOutTradeExt() {
		return this.outTradeExt;
	}

	public void setOutTradeExt(java.lang.String outTradeExt) {
		this.outTradeExt = outTradeExt;
	}

	public java.lang.String getOutNotifyStatus() {
		return this.outNotifyStatus;
	}

	public void setOutNotifyStatus(java.lang.String outNotifyStatus) {
		this.outNotifyStatus = outNotifyStatus;
	}

	public java.lang.String getTradeNo() {
		return this.tradeNo;
	}

	public void setTradeNo(java.lang.String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public java.util.Date getPayTime() {
		return this.payTime;
	}

	public void setPayTime(java.util.Date payTime) {
		this.payTime = payTime;
	}

	public java.lang.String getPayStatus() {
		return this.payStatus;
	}

	public void setPayStatus(java.lang.String payStatus) {
		this.payStatus = payStatus;
	}

	public java.lang.String getChannelTradeNo() {
		return this.channelTradeNo;
	}

	public void setChannelTradeNo(java.lang.String channelTradeNo) {
		this.channelTradeNo = channelTradeNo;
	}

	public java.lang.String getChannelTradeNoProxy() {
		return this.channelTradeNoProxy;
	}

	public void setChannelTradeNoProxy(java.lang.String channelTradeNoProxy) {
		this.channelTradeNoProxy = channelTradeNoProxy;
	}

	public java.lang.String getChannelMerchantNo() {
		return this.channelMerchantNo;
	}

	public void setChannelMerchantNo(java.lang.String channelMerchantNo) {
		this.channelMerchantNo = channelMerchantNo;
	}

	public java.lang.String getChannelRoute() {
		return this.channelRoute;
	}

	public void setChannelRoute(java.lang.String channelRoute) {
		this.channelRoute = channelRoute;
	}

	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}

	public java.util.Date getChannelPayTime() {
		return this.channelPayTime;
	}

	public void setChannelPayTime(java.util.Date channelPayTime) {
		this.channelPayTime = channelPayTime;
	}

	public java.math.BigDecimal getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(java.math.BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public java.math.BigDecimal getPayAmount() {
		return this.payAmount;
	}

	public void setPayAmount(java.math.BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	public java.math.BigDecimal getDiscountAmount() {
		return this.discountAmount;
	}

	public void setDiscountAmount(java.math.BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	public java.lang.String getVersion() {
		return this.version;
	}

	public void setVersion(java.lang.String version) {
		this.version = version;
	}

	public java.lang.String getUserId() {
		return this.userId;
	}

	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}

	public java.lang.String getUserRelId() {
		return this.userRelId;
	}

	public void setUserRelId(java.lang.String userRelId) {
		this.userRelId = userRelId;
	}

	public java.lang.String getUserType() {
		return this.userType;
	}

	public void setUserType(java.lang.String userType) {
		this.userType = userType;
	}

	public java.lang.String getNickname() {
		return this.nickname;
	}

	public void setNickname(java.lang.String nickname) {
		this.nickname = nickname;
	}

	public java.lang.String getAppId() {
		return this.appId;
	}

	public void setAppId(java.lang.String appId) {
		this.appId = appId;
	}

	public java.lang.String getThirdAppMark() {
		return this.thirdAppMark;
	}

	public void setThirdAppMark(java.lang.String thirdAppMark) {
		this.thirdAppMark = thirdAppMark;
	}

}
