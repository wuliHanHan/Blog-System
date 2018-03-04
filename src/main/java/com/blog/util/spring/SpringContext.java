package com.blog.util.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContext implements ApplicationContextAware {   
	
	private static ApplicationContext context;
	
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		this.context=context;   
	}
	
	public static ApplicationContext getContext() {
		return context;
	}
	
	public static <T> T getBean(String name) throws BeansException {
         return (T) context.getBean(name);
	}
	
}
