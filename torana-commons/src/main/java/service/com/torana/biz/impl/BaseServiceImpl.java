package com.torana.biz.impl;

import com.torana.biz.BaseService;
import com.torana.hibernate.core.dao.DAOFactory;
/**
 * @author bhanuchander
 * @version 2.0
 * @since 26th Jun 2014
 * */
public class BaseServiceImpl implements BaseService{

	private DAOFactory daoFactory = null;

	public DAOFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

}
