package com.blog.util;


/**
 * 生成随机唯一数
 * 
 * 
 * 
 */
public class UUID {
	
	/**
	 * 利用JAVA UUID生成一个全局唯一字符串
	 * @return
	 */
	public static synchronized String next() {
		String uuid = randomUUID();
		return MD5.digest(uuid);
	}
	
	/**
	 * 随机唯一标识
	 * @return
	 */
	public static synchronized String randomUUID() {
		return java.util.UUID.randomUUID().toString();
	}
	
}
