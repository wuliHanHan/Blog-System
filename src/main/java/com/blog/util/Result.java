package com.blog.util;

import org.apache.log4j.Logger;

import com.blog.util.config.CompositeFactory;

/**
 * @功能：	操作处理结果
 * @作者：	wlh
 */
public class Result {
	
	private static final Logger logger = Logger.getLogger(Result.class);
	
	// 操作结果
	private String resultCode;
	
	// 错误信息
	private String errorInfo;
	
	// 附属对象
	private Object object;
	
	public Result(String resultCode, String errorInfo) {
		super();
		this.resultCode = resultCode;
		this.errorInfo = CompositeFactory.getString(errorInfo);
	}
	
	public Result(String resultCode, String errorInfo, Object object) {
		super();
		this.resultCode = resultCode;
		this.errorInfo = CompositeFactory.getString(errorInfo);
		this.object = object;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
	
}
