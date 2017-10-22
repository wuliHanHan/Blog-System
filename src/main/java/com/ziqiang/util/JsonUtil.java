package com.ziqiang.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

public class JsonUtil {

	public static Gson getGson() {
		return new Gson();
	}

	/**
	 * 将对象转为JSON字符串(忽略NULL值)
	 * 
	 * @param src
	 * @return
	 */
	public static String toJson(Object src) {
		return getGson().toJson(src);
	}

	/**
	 * 将对象转为JSON字符串(不忽略NULL值)
	 * 
	 * @param src
	 * @param serializeNulls
	 * @return
	 */
	public static String toJson(Object src, boolean serializeNulls) {
		if (serializeNulls)
			return new GsonBuilder().serializeNulls().create().toJson(src);
		return toJson(src);
	}

	/**
	 * 将JSON字符串转为对象
	 * 
	 * @param json
	 * @param classOfT
	 * @return
	 * @throws JsonSyntaxException
	 */
	public static <T> T fromJson(String json, Class<T> classOfT)
			throws JsonSyntaxException {
		return getGson().fromJson(json, classOfT);
	}

	/**
	 * 从请求体中读取客户端发送的JSON串
	 * 
	 * @param stream
	 *            输入流
	 * @return String 类型，接收到的JSON串
	 */
	public static String readStringFromRequestBody(InputStream stream) {
		StringBuffer sb = new StringBuffer();
		char[] buf = new char[2048];
		int len = -1;
		try {
			InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
			while ((len = reader.read(buf)) != -1) {
				sb.append(new String(buf, 0, len));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
				}
			}
		}
		return sb.toString();
	}
	
	/**
	 * 回写响应
	 * @param json
	 * @param response
	 */
	public static void writeString(String json, HttpServletResponse response) {
		ServletOutputStream os = null;
		try {
			os = response.getOutputStream();
			os.write(json.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(null != os){
				try {
					os.close();
				} catch (IOException e) {
				}
			}
		}
	}
}
