package com.blog.util;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * @功能：	基本工具类
 * @作者：	wlh
 */
public class BlogUtil {
	
	/**
	 * 判断集合是否非空
	 * @param collection
	 * @return
	 */
	public static boolean isEmptyCollection(Collection<?> collection){
		boolean flag = true;
		if(null != collection){
			if(collection.size() > 0){
				flag = false;
			}
		} 
		
		return flag;
	}
	
	/**
	 * 判断集合是否非空
	 * @param collection
	 * @return
	 */
	public static boolean isEmptyMap(Map<?,?> map){
		boolean flag = true;
		if(null != map){
			if(map.size() > 0){
				flag = false;
			}
		}
		return flag;
	}
	
	/**
	 * 数组去重
	 * @param str
	 * @return
	 */
	public static String[] repeat(String[] str){
		HashSet<String> set = new HashSet<String>();
		if(!StringUtils.isEmpty(str)){
			for(int i = 0;i<str.length;i++){
				set.add(str[i]);
			}
			return (String[])set.toArray(new String[set.size()]);
		}
		
		return null;
	}
	
	/**
	 * 防止sql自动注入
	 * @return
	 */
	public static String transactSQLInjection(String value){
		if(!StringUtils.isEmpty(value)){
//			return value.replaceAll(".*(['%;]+|(--)+).*", " ");
			return value.replaceAll("%","[%]").replaceAll("_","[_]").replaceAll("'","''");
		}
		
		return null;
	}
	
	/**
	 * @param time 格式固定 0000-00-00 00:00:00
	 * @return
	 */
	public static long strToTime(String time) {
		// 月
		int month = Integer.parseInt(time.substring(5, 7));
		// 日
		int day = Integer.parseInt(time.substring(8, 10));
		// 时
		int hour = Integer.parseInt(time.substring(11, 13));
		// 分
		int min = Integer.parseInt(time.substring(14, 16));
		// 秒
		int sin = Integer.parseInt(time.substring(17, 19));

		return (month * 30 * 24 * 60 * 60 + day * 24 * 60 * 60 + hour * 60 * 60 + min * 60 + sin) * 1000;
	}

	/**
	 * String数组为空判断
	 * @param str
	 * @return
	 */
	public static boolean isEmptyStringArray(String[] str){
		boolean flag = true;
		if(null != str){
			if(str.length > 0 && !str[0].trim().equals("")){
				flag = false;
			}
		}
		return flag;
	}
	
	/**
	 * int数组为空判断
	 * @param inter
	 * @return
	 */
	public static boolean isEmptyIntArray(Integer[] inter){
		boolean flag = true;
		if(null != inter){
			if(inter.length > 0){
				flag = false;
			}
		}
		return flag;
	}
	
	/**
	 * @desc 时间格式化 yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String formatTimeToStr(Date date){
		String timeStr = "";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		timeStr = formatter.format(date);
		return timeStr;
	} 
	
	/**
	 * @desc 获取本机IP(适用于Linux系统)
	 * @return Ip
	 */
	public static String getLocalIP() {
        String ip = "";
        try {
            Enumeration<?> e1 = (Enumeration<?>) NetworkInterface
                    .getNetworkInterfaces();
            while (e1.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) e1.nextElement();
                if (!ni.getName().equals("eth0")) {
                    continue;
                } else {
                    Enumeration<?> e2 = ni.getInetAddresses();
                    while (e2.hasMoreElements()) {
                        InetAddress ia = (InetAddress) e2.nextElement();
                        if (ia instanceof Inet6Address)
                            continue;
                        ip = ia.getHostAddress();
                    }
                    break;
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return ip;
    }
	

	/**
	 * 下拉级联拼装
	 * @param sb
	 * @param id
	 * @param content
	 */
	public static void buildSubOption(StringBuilder sb,String id,String content) {
		sb.append("<option value=");
		sb.append(id);
		sb.append(">");
		sb.append(content);
		sb.append("</option>");
	}
}
