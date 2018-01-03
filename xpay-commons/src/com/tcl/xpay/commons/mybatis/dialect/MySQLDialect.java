package com.tcl.xpay.commons.mybatis.dialect;

/**
 * 
 * <br>
 * <b>功能：</b>详细的功能描述<br>
 */
public class MySQLDialect extends Dialect {

	public boolean supportsLimitOffset() {
		return true;
	}

	public boolean supportsLimit() {
		return true;
	}

	public String getLimitString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder) {
		if (offset > 0) {
			return sql + " limit " + offset + "," + limitPlaceholder;
		} else {
			return sql + " limit " + limitPlaceholder;
		}
	}

}
