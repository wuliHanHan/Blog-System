package com.blog.util.config;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;

/**
 * @desc	复合查询
 * @author	wlh
 */
public class CompositeFactory {
	
	private static CompositeConfiguration configuration;

	private CompositeFactory(){}

	public static CompositeConfiguration getInstance(){
		if(configuration == null){
			synchronized (CompositeFactory.class) {
				configuration = new CompositeConfiguration();
			}
		}
		return configuration;
	}

	public static void addConfiguration(Configuration config){
		configuration = getInstance();
		configuration.addConfiguration(config);
	}
	
	public static String getString(String key){
		System.out.println(key);
		return configuration.getString(key);
	}
	
}
