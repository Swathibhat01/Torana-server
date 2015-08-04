package com.torana.generic.dao;

import java.util.List;

import com.torana.hibernate.exception.ApplicationException;
import com.torana.hibernate.exception.DataException;

/**
 * ToranaGenericResource
 */
public interface ToranaGenericDAO {
	/**
	 * ToranaGenericResource
	 */
	Object saveObjectDAO(Object object) throws DataException, ApplicationException;
	/**
	 * ToranaGenericResource
	 */
	Object updateObjectDAO(Object object) throws DataException, ApplicationException;
	/**
	 * ToranaGenericResource
	 */
	List<Object> getAllObjectsDAO(String queryId, List<String> whereParams) throws DataException, ApplicationException;
	/**
	 * ToranaGenericResource
	 */
	boolean deleteObjectByIdDAO(Class<? extends Object> className, Integer objectId) throws DataException, ApplicationException;
	/**
	 * ToranaGenericResource
	 */
	boolean deleteObjectsByIdsDAO(Class<? extends Object> className, Integer... objectIds) throws DataException, ApplicationException;
	/**
	 * ToranaGenericResource
	 */
	Object getObjectByIdDAO(Class<? extends Object> className, Integer objectId) throws DataException, ApplicationException;
	/**
	 * ToranaGenericResource
	 */
	Object[] getObjectsByIdsDAO(Class<? extends Object> className, Integer... objectIds) throws DataException, ApplicationException;
	/**
	 * ToranaGenericResource
	 */
	List<Object> getAllObjectByClassNameDAO(String className) throws DataException, ApplicationException;

}
