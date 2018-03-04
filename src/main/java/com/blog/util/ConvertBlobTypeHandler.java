package com.blog.util;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

/**
 * @author wlh
 * @desc Blob转换String处理器，解决MyBatis存储blob字段后，出现乱码的问题
 */
public class ConvertBlobTypeHandler extends BaseTypeHandler<String> {

	private static final String DEFAULT_CHARSET = "utf-8";

	@Override
	public String getNullableResult(ResultSet rs, String columnName) throws SQLException {

		Blob blob = rs.getBlob(columnName);
		if (null == blob) {
			return null;
		}

		byte[] returnValue = null;
		returnValue = blob.getBytes(1, (int) blob.length());

		try {
			// ###把byte转化成string
			return new String(returnValue, DEFAULT_CHARSET);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Blob Encoding Error!");
		}
	}

	@Override
	public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		Blob blob = rs.getBlob(columnIndex);
		if (null == blob) {
			return null;
		}

		byte[] returnValue = null;
		returnValue = blob.getBytes(1, (int) blob.length());

		try {
			// ###把byte转化成string
			return new String(returnValue, DEFAULT_CHARSET);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Blob Encoding Error!");
		}
	}

	@Override
	public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		Blob blob = cs.getBlob(columnIndex);
		if (null == blob) {
			return null;
		}
		
		byte[] returnValue = null;
		returnValue = blob.getBytes(1, (int) blob.length());
		try {
			// ###把byte转化成string
			return new String(returnValue, DEFAULT_CHARSET);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Blob Encoding Error!");
		}
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int parameterIndex, String parameter, JdbcType jdbcType)
			throws SQLException {
		ByteArrayInputStream bis = null;
		try {
			// ###把String转化成byte流
			bis = new ByteArrayInputStream(parameter.getBytes(DEFAULT_CHARSET));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Blob Encoding Error!");
		}
		ps.setBinaryStream(parameterIndex, bis, parameter.length());
	}

}
