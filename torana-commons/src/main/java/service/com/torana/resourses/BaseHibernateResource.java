package com.torana.resourses;

import com.torana.biz.ServiceManager;
import com.torana.util.ApplicationContextProvider;

/**
 * @author bhanuchander
 * @version 2.0
 * @since 26th Jun 2014
 * */
public class BaseHibernateResource extends BaseResource{

	/** This {@value ServiceManger} field injected from  resource-bean.xml */
	private ServiceManager serviceManager;

	/** 
	 * This method used for getting Service Manager object reference 
	 * @return {@link ServiceManger}
	 */
	public ServiceManager getServiceManager() {
		if(serviceManager==null){
			serviceManager = ApplicationContextProvider.getServiceManager();
		}
		return serviceManager;
	}

	/** 
	 * @param serviceManger
	 * This method used for setting Service Manager object reference 
	 * */
	public void setServiceManager(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}
}
