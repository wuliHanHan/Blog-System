package com.blog.util;

import java.io.UnsupportedEncodingException;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * 字符帮助类
 * 
 * 
 * 
 */
public class StringUtils {

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return (null == str || "".equals(str)) ? true : false;
	}

	/**
	 * 转换数字保留2位小数
	 * (非四舍五入)
	 * @param str
	 * @return
	 */
	public static String convertFloat(String str) {
		return formatMoney(str);
	}
	
	/**
	 * 格式化货币  单位：豪
	 * 保留2位小数(非四舍五入)
	 */
	public static String formatMoney(String str) {
		if (isEmpty(str))
			return "0.00";
		Double d = Double.valueOf(str);
		DecimalFormat formater = new DecimalFormat("###,##0.00");
		formater.setRoundingMode(RoundingMode.FLOOR);
		return formater.format(d/10000);
	}
	
	/**
	 * 格式化数字(非四舍五入)
	 * @param num
	 * @date 2015-01-13
	 * @return
	 */
	public static double convertFloat(double num) {
		DecimalFormat df=new DecimalFormat("##0.00");
		df.setRoundingMode(RoundingMode.FLOOR); // 不四舍五入
		return Double.valueOf(df.format(num));
	}
	
	/**
	 * 获得中文字符串
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String getUtf8String(String str) throws UnsupportedEncodingException {
		if (isEmpty(str))
			return "";
		return new String(str.getBytes("ISO-8859-1"), "UTF-8");
	}
}
