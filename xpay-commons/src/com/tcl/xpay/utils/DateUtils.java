package com.tcl.xpay.utils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import com.tcl.xpay.exception.XpayRuntimeException;

/**
 * <br>
 * <b>功能：</b>日期工具类<br>
 */
public class DateUtils {

	public final static String PATTERN_DEFAULT_DATE = "yyyy-MM-dd";
	public final static String PATTERN_DEFAULT_DATETIME = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 返回当前系统日期
	 * 
	 * @return
	 */
	public static String nowDate() {
		return DateUtils.formatDateTime(new Date());
	}

	/**
	 * 判断targetDate日期是否大于等于sourceDate日期
	 * 
	 * @param sourceDate
	 * @param targetDate
	 * @return
	 */
	public static boolean isLateOrEqualDate(Date sourceDate, Date targetDate) {
		return targetDate.getTime() >= sourceDate.getTime();
	}

	/**
	 * 日期格式化
	 * 
	 * @param date
	 * @param pattern
	 *            格式
	 * @return
	 */
	public static String formatDate(Date date, String pattern) {
		return new DateTime(date).toString(pattern);
	}

	/**
	 * 日期格式化，默认格式:yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		return formatDate(date, PATTERN_DEFAULT_DATE);
	}

	/**
	 * 日期时间格式化
	 * 
	 * @param dateTime
	 * @param pattern
	 * @return
	 */
	public static String formatDateTime(Date dateTime, String pattern) {
		return new DateTime(dateTime).toString(pattern);
	}

	/**
	 * 日期时间格式化 ,默认格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateTime
	 * @return
	 */
	public static String formatDateTime(Date dateTime) {
		return formatDateTime(dateTime, PATTERN_DEFAULT_DATETIME);
	}

	/**
	 * 将字符串转换为日期时间
	 * 
	 * @param text
	 * @param pattern
	 * @return
	 */
	public static Date toDateTime(String text, String pattern) {
		if (StringUtils.isBlank(text)) {
			return null;
		}
		try {
			return org.apache.commons.lang3.time.DateUtils.parseDate(text, pattern);
		} catch (ParseException e) {
			throw new XpayRuntimeException("日期转换错误", e);
		}
	}

	/**
	 * 将字符串转换为日期时间,默认格式：yyy-MM-dd HH:mm:ss
	 * 
	 * @param text
	 * @return
	 */
	public static Date toDateTime(String text) {
		return toDateTime(text, PATTERN_DEFAULT_DATETIME);
	}

	/**
	 * 将字符串转换为日期
	 * 
	 * @param text
	 * @param pattern
	 * @return
	 */
	public static Date toDate(String text, String pattern) {
		if (StringUtils.isBlank(text)) {
			return null;
		}
		try {
			return org.apache.commons.lang3.time.DateUtils.parseDate(text, pattern);
		} catch (ParseException e) {
			throw new XpayRuntimeException("日期转换错误", e);
		}
	}

	/**
	 * 将字符串转换为日期,默认格式：yyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static Date toDate(String text) {
		return toDateTime(text, PATTERN_DEFAULT_DATE);
	}

	/**
	 * 判断targetDate日期是否比sourceDate日期晚
	 * 
	 * @param sourceDate
	 * @param targetDate
	 * @return
	 */
	public static boolean isLateDate(Date sourceDate, Date targetDate) {
		return targetDate.getTime() > sourceDate.getTime();
	}

	/**
	 * @param type
	 *            1:表示年份,2:表示月份,3:表示周,5:表示天
	 * @param value
	 *            根据type来进行加减 比如(5,-1) 返回前一天日期
	 * @return
	 */
	public static Date toDateByType(int type, int value) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());
		gc.add(type, value);
		return gc.getTime();
	}

	public static Date getFirstDayOfMonth() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, 1);
		String date = formatDate(c.getTime());
		return toDate(date, DateUtils.PATTERN_DEFAULT_DATE);
	}

	/**
	 * <p>
	 * 计算俩个日期相差天数
	 * </p>
	 * 
	 * @param date
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static int getDateCount(Date startDate, Date endDate) {
		Calendar date0 = Calendar.getInstance();
		Calendar date1 = Calendar.getInstance();
		int count = 0;
		date0.setTime(startDate);
		date1.setTime(endDate);
		while (date1.compareTo(date0) >= 0) {
			date0.add(date0.DATE, 1);
			count++;
		}
		return count;
	}

	public static void main(String[] args) {
		// System.out.println(getFirstDayOfMonth());
		String DATE_FORMAT = "yyyyMMdd";
		Date startDate = DateUtils.toDate("20170326", DATE_FORMAT);
		Date endDate = DateUtils.toDate("20170425", DATE_FORMAT);
		System.out.println(DateUtils.getDateCount(startDate, endDate));
	}
}
