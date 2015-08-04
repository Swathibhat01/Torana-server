package com.torana.hibernate.core.dao.impl;

import java.util.Map;

import com.torana.hibernate.core.dao.DAOFactory;
/**
 * @author torana
 * @version 1.0
 * @since 2nd feb 2015
 * */
public class DAOFactoryImpl implements DAOFactory{
	public Map<String, Object> daosMap;

	public Map<String, Object> getDaosMap() {
		return daosMap;
	}

	public void setDaosMap(Map<String, Object> daosMap) {
		this.daosMap = daosMap;
	}
	
}
