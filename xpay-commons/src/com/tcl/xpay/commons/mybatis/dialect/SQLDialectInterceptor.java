package com.tcl.xpay.commons.mybatis.dialect;

import java.util.Properties;

import org.apache.commons.lang3.ClassUtils;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.RowBounds;
import org.springside.modules.utils.Reflections;

/**
 * 为mybatis提供基于方言(Dialect)的查询的插件
 * 
 * 将拦截Executor.query()方法实现查询方言的插入.
 * 
 * 配置文件内容:
 * <pre>
 * 	&lt;plugins>
 *		&lt;plugin interceptor="com.bc.core.base.persistence.SQLDialectInterceptor">
 *			&lt;property name="dialectClass" value="com.bc.core.base.persistence.dialect.MySQLDialect"/>
 *		&lt;/plugin>
 *	&lt;/plugins>
 * </pre>
 * 
 */

@Intercepts({ @Signature(type = StatementHandler.class, method = "getBoundSql", args = {}) })
public class SQLDialectInterceptor implements Interceptor {

	private Dialect dialect;

	public Object intercept(Invocation invocation) throws Throwable {
		return invocation.proceed();
	}

	public Object plugin(Object target) {
		if (target instanceof StatementHandler) {
			StatementHandler statementHandler = (StatementHandler) target;
			RowBounds rowBounds = null;
			if (statementHandler instanceof RoutingStatementHandler) {
				StatementHandler targetStmtHandler = (StatementHandler) Reflections.getFieldValue(statementHandler, "delegate");
				rowBounds = (RowBounds) Reflections.getFieldValue(targetStmtHandler, "rowBounds");
			} else {
				rowBounds = (RowBounds) Reflections.getFieldValue(statementHandler, "rowBounds");
			}
			String sql = fixPageSql(rowBounds, statementHandler.getBoundSql());
			Reflections.setFieldValue(statementHandler.getBoundSql(), "sql", sql);
		}
		return Plugin.wrap(target, this);
	}

	private String fixPageSql(RowBounds rowBounds, BoundSql boundSql) {
		String sql = boundSql.getSql().trim();
		int offset = rowBounds.getOffset();
		int limit = rowBounds.getLimit();
		if (dialect.supportsLimit() && (offset != RowBounds.NO_ROW_OFFSET || limit != RowBounds.NO_ROW_LIMIT)) {
			if (dialect.supportsLimitOffset()) {
				sql = dialect.getLimitString(sql, offset, limit);
				offset = RowBounds.NO_ROW_OFFSET;
			} else {
				sql = dialect.getLimitString(sql, 0, limit);
			}
			limit = RowBounds.NO_ROW_LIMIT;

			Reflections.setFieldValue(rowBounds, "offset", offset);
			Reflections.setFieldValue(rowBounds, "limit", limit);

		}
		return sql;
	}

	public void setProperties(Properties properties) {
		String dialectClass = properties.getProperty("dialect_class");
		try {
			dialect = (Dialect) ClassUtils.getClass(dialectClass).newInstance();
		} catch (Exception e) {
			throw new RuntimeException("cannot create dialect instance by dialectClass:" + dialectClass, e);
		}
	}

}
