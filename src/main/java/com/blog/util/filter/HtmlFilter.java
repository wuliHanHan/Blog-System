package com.blog.util.filter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blog.util.listener.SystemListener;
import com.blog.util.thread.HtmlThread;

public class HtmlFilter implements Filter{
	
	public void init(FilterConfig arg0) throws ServletException {
		
	}
	
	public void destroy() {
		
	}
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
        String articleId = req.getServletPath().substring(14);
        File file = new File(SystemListener.getContext().getRealPath("/")+"/html/"+articleId);
        
        if(!file.exists()){
        	ResponseWrapper wrapper = new ResponseWrapper((HttpServletResponse) response);  
        	filterChain.doFilter(request, wrapper);  
        	
        	// 这里是生成静态页
        	String content = wrapper.getResult(); 
        	HtmlThread.addHtml(new HtmlObj(file.getPath(),content));
        	
        	response.setCharacterEncoding("UTF-8");  
            // 重置响应输出的内容长度  
            response.setContentLength(-1);  
            // 输出最终的结果  
            PrintWriter out = response.getWriter();  
            out.write(content);  
            out.flush();  
            out.close(); 
        }else{
        	response.setCharacterEncoding("UTF-8");  
            // 重置响应输出的内容长度  
            response.setContentLength(-1);  
        	PrintWriter out = response.getWriter();  
        	InputStreamReader reader = new InputStreamReader(new FileInputStream(file),"utf-8");
        	char[] c = new char[1024];
        	int b = 0;
        	while((b=reader.read(c)) > -1){
        		out.write(c,0,b);  
        	}
            out.flush();  
            out.close(); 
        }
         
	}
	
}
