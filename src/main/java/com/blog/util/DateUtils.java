package com.blog.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {

	public static final String DATE_FROMAT_CN = "yyyy-MM-dd HH:mm:ss";

	public static final String DATE_FROMAT_EN = "dd/MM/yyyy HH:mm:ss";
	
	/**
	 * 获得格式化对象
	 * @param pattern
	 * @return
	 */
	public static DateFormat getDateFormat(String pattern) {
		return new SimpleDateFormat(pattern);
	}

	/**
	 * 获得当前时间
	 * 
	 * @return
	 */
	public static String getCurDateTime(String fromatStr) {
		DateFormat df = new SimpleDateFormat(fromatStr);
		return df.format(new Date().getTime());
	}

	/**
	 * 时间加减
	 * 
	 * @param amount
	 * @return
	 */
	public static Date dateSub(int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, amount);
		return calendar.getTime();
	}

	// 按格式取系统日期
	public static String getDateWithYMD() {
		Calendar now = Calendar.getInstance();
		int month = now.get(Calendar.MONTH) + 1;
		int day = now.get(Calendar.DAY_OF_MONTH);
		int year = now.get(Calendar.YEAR);
		String date = year + "-" + month + "-" + day;
		return date;
	}

	/**
	 * 获得前天
	 * 
	 * @return
	 */
	public static String getdayBeforeYesterday() {
		Date date = new Date();// 取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, -2);//
		date = calendar.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(date);
		return dateString;
	}

	/**
	 * 根据输入的日期字符串 和 提前天数 ， * 获得 指定日期提前几天的日期对象 *
	 * 
	 * @param dateString
	 *            日期对象 ，格式如1-31-1900 *
	 * @param lazyDays
	 *            倒推的天数
	 * @return 指定日期倒推指定天数后的日期对象 *
	 * @throws ParseException
	 */
	public static Date getDate(String dateString, int beforeDays)
			throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date inputDate = dateFormat.parse(dateString);
		Calendar cal = Calendar.getInstance();
		cal.setTime(inputDate);
		int inputDayOfYear = cal.get(Calendar.DAY_OF_YEAR);
		cal.set(Calendar.DAY_OF_YEAR, inputDayOfYear - beforeDays);
		return cal.getTime();
	}
	
	public static Date getDateStr(Date dateString, int beforeDays)
			throws ParseException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateString);
		int inputDayOfYear = cal.get(Calendar.DAY_OF_YEAR);
		cal.set(Calendar.DAY_OF_YEAR, inputDayOfYear - beforeDays);
		return cal.getTime();
	}

	/**
	 * 日期转换为字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String date2String(Date date) {
		return date2String(date, "yyyy-MM-dd");
	}

	/**
	 * 日期转换为字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String date2String(Date date, String fromatStr) {
		DateFormat df = new SimpleDateFormat(fromatStr);
		return df.format(date);
	}

	/**
	 * 字符串转为日期
	 * 
	 * @param dateStr
	 * @param fromatStr
	 * @return
	 * @throws ParseException
	 */
	public static Date String2Date(String dateStr, String fromatStr) {
		SimpleDateFormat sdf = new SimpleDateFormat(fromatStr);
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 字符串转为日期
	 * 
	 * @param dateStr
	 * @param fromatStr
	 * @return
	 * @throws ParseException
	 */
	public static Date String2Date(String dateStr) {
		return String2Date(dateStr, "yyyy-MM-dd");
	}

	/**
	 * 获得当前日期
	 * 
	 * @return
	 */
	public static String getCurDate() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(new Date());
	}
	
	/**
	 * 获得当前日期
	 * 
	 * @return
	 */
	public static String getCurTime() {
		DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
		return df.format(new Date());
	}
	
	/**
	 * 获得当前日期
	 * 
	 * @return
	 */
	public static String getCurDate(String fromatStr) {
		DateFormat df = new SimpleDateFormat(fromatStr);
		return df.format(new Date());
	}

	/**
	 * 格式化时间
	 * 
	 * @param dateStr
	 * @return
	 */
	public static String formatDate(String dateStr) {
		return formatDate(dateStr, DateUtils.DATE_FROMAT_CN);
	}

	/**
	 * 格式化时间
	 * 
	 * @param dateStr
	 * @return
	 */
	public static String formatDate(String dateStr, String formatStr) {
		DateFormat df = new SimpleDateFormat(formatStr);
		try {
			Date date = df.parse(dateStr);
			return date2String(date, formatStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将日期字符串转日期类型
	 * 
	 * @param date
	 * @return
	 */
	public static Date parseDate(String date,String pattern) {
		DateFormat df = new SimpleDateFormat(pattern);
		try {
			return df.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 将日期字符串转日期类型
	 * 
	 * @param date
	 * @return
	 */
	public static Date parseDate(String date) {
		DateFormat df = new SimpleDateFormat();
		try {
			return df.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
