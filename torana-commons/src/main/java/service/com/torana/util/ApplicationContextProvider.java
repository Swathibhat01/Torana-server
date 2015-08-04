package com.torana.util;
/**
 * @author torana
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.torana.biz.ServiceManager;


public class ApplicationContextProvider implements ApplicationContextAware {
	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationContextProvider.class);
	private static ApplicationContext ctx = null;
	public static ApplicationContext getApplicationContext() {  
		return ctx;    
	}
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		// Assign the ApplicationContext into a static method

		LOGGER.info("application context initialized");
		this.ctx = ctx;
	}

	public static ServiceManager getServiceManager(){
		ApplicationContext context = ApplicationContextProvider.getApplicationContext();
		ServiceManager serviceManager = (ServiceManager)context.getBean("serviceManager");
		return serviceManager;
	}
}