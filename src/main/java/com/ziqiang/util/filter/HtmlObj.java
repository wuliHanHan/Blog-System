package com.ziqiang.util.filter;

public class HtmlObj{
	
	private String fileUrl;
	
	private String content;
	
	public HtmlObj() {
	}

	public HtmlObj(String fileUrl, String content) {
		super();
		this.fileUrl = fileUrl;
		this.content = content;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
