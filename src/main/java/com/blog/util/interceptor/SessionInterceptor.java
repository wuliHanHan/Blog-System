package com.blog.util.interceptor;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.blog.util.Constant;

/**
 * @desc	会话拦截器
 * @author	wlh
 */
public class SessionInterceptor extends HandlerInterceptorAdapter{
	
	private static final Logger logger = Logger.getLogger(SessionInterceptor.class);
	
	/**
	 * 不拦截的后缀
	 */
	private List<String> excludeMappingUrl;
	
	/**
	 * 处理时间  线程绑定
	 */
	private ThreadLocal<Long> dealTimeThradeLocal = new ThreadLocal<Long>();
	
	/**
	 * 请求前进行会话校验
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		long startTime = System.currentTimeMillis();
		dealTimeThradeLocal.set(startTime);
		
		String url = request.getRequestURI();
		String postFix = null;
		if(!StringUtils.isEmpty(url)){
			// 静态文件
			int index = url.lastIndexOf(".");
			if(index > -1){
				postFix = url.substring(index);
			}
			
			if(!StringUtils.isEmpty(postFix) && excludeMappingUrl.contains(postFix)){
				// 静态文件默认处理
				return true;
			}else{
				// 不是静态文件 且不是登录请求   判断session是否存在
				if(!url.contains("/login")){
					HttpSession session = request.getSession();
					if(null == session.getAttribute(Constant.USERINFO)){
						String basePath = request.getContextPath();
						// 未登录
						PrintWriter out = response.getWriter();
					    out.println("<html>");  
					    out.println("<script type='text/javascript'>");  
					    out.println("window.open('"+basePath+"/login','_top')");  
					    out.println("</script>");  
					    out.println("</html>");
						out.flush();
						return false;
					}
					return true;
				}
			}
		}
		
		return true;
	}
	
	
	public boolean isStaticFile(String url){
		if(!StringUtils.isEmpty(url)){
			// 静态文件
			int index = url.lastIndexOf(".");
			String postFix = null;
			if(index > -1){
				postFix = url.substring(index);
			}
			
			if(!StringUtils.isEmpty(postFix) && excludeMappingUrl.contains(postFix)){
				// 静态文件默认处理
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		if(!isStaticFile(request.getRequestURI())){
			long endTime = System.currentTimeMillis();
			long starTime = dealTimeThradeLocal.get();
			logger.info("本次请求["+request.getRequestURI()+"]处理时间："+(endTime-starTime)+"ms");
		}
		
		super.afterCompletion(request, response, handler, ex);
	}
	
	public List<String> getExcludeMappingUrl() {
		return excludeMappingUrl;
	}

	public void setExcludeMappingUrl(List<String> excludeMappingUrl) {
		this.excludeMappingUrl = excludeMappingUrl;
	}
}
