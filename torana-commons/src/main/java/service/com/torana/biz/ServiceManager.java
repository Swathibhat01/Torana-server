package com.torana.biz;

import java.util.Map;

/**
 * @author bhanuchander
 * @version 2.0
 * @since 26th Jun 2014
 * */
public interface ServiceManager {

	String SERVICE_MANAGER = "serviceManager";

	public Map<String, Object> getServicesMap();

}
