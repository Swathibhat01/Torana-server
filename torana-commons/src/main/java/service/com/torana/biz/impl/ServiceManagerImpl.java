package com.torana.biz.impl;

import java.util.Map;

import com.torana.biz.ServiceManager;


/**
 * Provide base service layer by implementing {@link ServiceManager} interface.
 * Here project related services are registered. 
 *  
 * */
public class ServiceManagerImpl implements ServiceManager{
	
	public Map<String, Object> servicesMap;

	public Map<String, Object> getServicesMap() {
		return servicesMap;
	}

	public void setServicesMap(Map<String, Object> servicesMap) {
		this.servicesMap = servicesMap;
	}

	public Object getService(String key){
		return servicesMap.get(key);
	}
}
