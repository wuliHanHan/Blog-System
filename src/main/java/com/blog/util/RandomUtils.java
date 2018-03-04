package com.blog.util;

import java.util.Random;

public class RandomUtils {

	/**
	 * 长度
	 */
	public static final int LENGTH = 15;

	/**
	 * 随机数上限
	 */
	private static final int max = 9999;

	/**
	 * 随机数下限
	 */
	private static final int min = 1000;

	/**
	 * 生成指定范围的随机数
	 * 
	 * @return
	 */
	public static long getRandom() {
		return new Random().nextInt(max) % (max - min + 1) + min;
	}

	/**
	 * 产生一个固定长度的随机字符串
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomStr(int length) {
		String str = "abcdefghigklmnopkrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sf = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(62);// 0~61
			sf.append(str.charAt(number));

		}
		return sf.toString();
	}

	/**
	 * 产生一个固定长度的随机字符串
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomStr() {
		return getRandomStr(LENGTH);
	}

}
