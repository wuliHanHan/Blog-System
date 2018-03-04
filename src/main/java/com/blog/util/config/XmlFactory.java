package com.blog.util.config;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.log4j.Logger;

/**
 * @desc	xml配置文件读取
 * @author	wlh
 */
public class XmlFactory {
	
	private static final Logger logger = Logger.getLogger(XmlFactory.class);

	private static Set<XMLConfiguration> xmlSet = new HashSet<XMLConfiguration>();

	public static XmlFactory xmlFactory;

	/**
	 * 私有构造函数
	 */
	private XmlFactory(){}

	/**
	 * 单例方法
	 * @return
	 */
	public static XmlFactory getInstance(){
		if(xmlFactory == null){
			synchronized (XmlFactory.class) {
				xmlFactory = new XmlFactory();
			}
		}
		return xmlFactory;
	}

	public static void addConfiguration(String path){
		try {
			XMLConfiguration xmlConfiguration = new XMLConfiguration(path);
			xmlSet.add(xmlConfiguration);
			CompositeFactory.addConfiguration(xmlConfiguration);
		} catch (ConfigurationException e) {
			logger.error("XmlFactory addConfiguration fail,fileName is ["+path+"]",e);
		}
	}


	/**
	 * *****************************************************************************************
	 * 
	 * 1、提供一些仅读取xml文件的方法
	 * 
	 * 2、便利xmlSet，依次读取
	 * 
	 * *****************************************************************************************
	 */
	
}
