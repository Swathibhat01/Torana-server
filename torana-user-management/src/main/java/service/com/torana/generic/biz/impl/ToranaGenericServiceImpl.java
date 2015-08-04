package com.torana.generic.biz.impl;

import static com.torana.generic.util.GenericConstants.GENERIC_DAO;

import java.util.List;

import com.torana.biz.impl.BaseServiceImpl;
import com.torana.generic.biz.ToranaGenericService;
import com.torana.generic.dao.ToranaGenericDAO;
import com.torana.hibernate.exception.ApplicationException;
import com.torana.hibernate.exception.DataException;
/**
 * ToranaGenericServiceImpl
 */
public class ToranaGenericServiceImpl extends BaseServiceImpl implements ToranaGenericService{

	/**
	 * saveObjectService
	 */
	public Object saveObjectService(Object object) throws DataException, ApplicationException{
		ToranaGenericDAO  genericDAO = (ToranaGenericDAO) getDaoFactory().getDaosMap().get(GENERIC_DAO);
		return genericDAO.saveObjectDAO(object);
	}
	/**
	 * saveObjectService
	 */
	public Object updateObjectService(Object object) throws DataException, ApplicationException{
		ToranaGenericDAO  genericDAO = (ToranaGenericDAO) getDaoFactory().getDaosMap().get(GENERIC_DAO);
		return genericDAO.updateObjectDAO(object);
	}
	/**
	 * saveObjectService
	 */
	public List<Object> getAllObjectsService(String queryId, List<String> whereParams) throws DataException, ApplicationException{
		ToranaGenericDAO  genericDAO = (ToranaGenericDAO) getDaoFactory().getDaosMap().get(GENERIC_DAO);
		return genericDAO.getAllObjectsDAO(queryId, whereParams);
	}
	/**
	 * saveObjectService
	 */
	public boolean deleteObjectByIdService(Class<? extends Object> className, Integer objectId) throws DataException, ApplicationException{
		ToranaGenericDAO  genericDAO = (ToranaGenericDAO) getDaoFactory().getDaosMap().get(GENERIC_DAO);
		return genericDAO.deleteObjectByIdDAO(className, objectId);
	}
	/**
	 * saveObjectService
	 */
	public boolean deleteObjectsByIdsDAO(Class<? extends Object> className, Integer... objectIds) throws DataException, ApplicationException{
		ToranaGenericDAO  genericDAO = (ToranaGenericDAO) getDaoFactory().getDaosMap().get(GENERIC_DAO);
		return genericDAO.deleteObjectsByIdsDAO(className, objectIds);
	}
	/**
	 * saveObjectService
	 */
	public Object[] getObjectsByIdsDAO(Class<? extends Object> className, Integer... objectIds) throws DataException, ApplicationException{
		ToranaGenericDAO  genericDAO = (ToranaGenericDAO) getDaoFactory().getDaosMap().get(GENERIC_DAO);
		return genericDAO.getObjectsByIdsDAO(className, objectIds);
	}
	/**
	 * saveObjectService
	 */
	public List<Object> getAllObjectByClassNameService(String className) throws DataException, ApplicationException{
		ToranaGenericDAO  genericDAO = (ToranaGenericDAO) getDaoFactory().getDaosMap().get(GENERIC_DAO);
		return genericDAO.getAllObjectByClassNameDAO(className);
	}
}
