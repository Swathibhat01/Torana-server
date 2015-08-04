package com.torana.generic.biz;

import java.util.List;

import com.torana.biz.BaseService;
import com.torana.hibernate.exception.ApplicationException;
import com.torana.hibernate.exception.DataException;
/**
 * ToranaGenericResource
 */
public interface ToranaGenericService extends BaseService {
	/**
	 * ToranaGenericResource
	 */
	Object saveObjectService(Object object) throws DataException, ApplicationException;
	/**
	 * ToranaGenericResource
	 */
	Object updateObjectService(Object object) throws DataException, ApplicationException;
	/**
	 * ToranaGenericResource
	 */
	List<Object> getAllObjectsService(String queryId, List<String> whereParams) throws DataException, ApplicationException;
	/**
	 * ToranaGenericResource
	 */
	boolean deleteObjectByIdService(Class<? extends Object> className, Integer objectId) throws DataException, ApplicationException;
	/**
	 * ToranaGenericResource
	 */
	boolean deleteObjectsByIdsDAO(Class<? extends Object> className, Integer... objectIds) throws DataException, ApplicationException;
	/**
	 * ToranaGenericResource
	 */
	Object[] getObjectsByIdsDAO(Class<? extends Object> className, Integer... objectIds) throws DataException, ApplicationException;
	/**
	 * ToranaGenericResource
	 */
	List<Object> getAllObjectByClassNameService(String className) throws DataException, ApplicationException;
	
}
