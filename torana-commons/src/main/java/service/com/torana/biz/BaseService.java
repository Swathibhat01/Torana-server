package com.torana.biz;

import com.torana.hibernate.core.dao.DAOFactory;

/**
 * @author bhanuchander
 * @version 2.0
 * @since 26th Jun 2014
 * */
public interface BaseService {

	public DAOFactory getDaoFactory();

	public void setDaoFactory(DAOFactory daoFactory);

}
