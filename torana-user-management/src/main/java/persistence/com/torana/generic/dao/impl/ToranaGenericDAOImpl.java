package com.torana.generic.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.torana.generic.dao.ToranaGenericDAO;
import com.torana.generic.util.GenericProperties;
import com.torana.hibernate.core.dao.HibernateBaseDAO;
import com.torana.hibernate.exception.ApplicationException;
import com.torana.hibernate.exception.DataException;
/**
 * ToranaGenericResource
 */
public class ToranaGenericDAOImpl extends HibernateBaseDAO implements ToranaGenericDAO{

	private static final Logger LOGGER = LoggerFactory.getLogger(ToranaGenericDAOImpl.class);

	/**
	 * ToranaGenericResource
	 */
	public Object saveObjectDAO(Object object) throws DataException, ApplicationException{

		LOGGER.info("Inside saveObjectService of ToranaGenericDAOImpl");
		try{
			boolean newRecord = _saveOrUpdateIsNew(object);
			LOGGER.info("-Result of Save-"+newRecord);
			System.out.println("-Result of Save-"+newRecord);
			_flush();

			return object;
		}catch(HibernateException e){
			LOGGER.error("Data Exception: Error while saving the Object : ",e);
			throw new DataException(e.getMessage());
		}catch(Exception e){
			LOGGER.error("Application Exception: Error while saving the Object : ",e);
			throw new ApplicationException(e.getMessage());
		}
	}
	/**
	 * ToranaGenericResource
	 */
	public Object updateObjectDAO(Object object) throws DataException, ApplicationException{

		LOGGER.info("Inside saveObjectService of ToranaGenericDAOImpl");
		try{

			_saveOrUpdate(object);
			_flush();

			return object;
		}catch(HibernateException e){
			LOGGER.error("Data Exception: Error while saving the Object : ",e);
			throw new DataException(e.getMessage());
		}catch(Exception e){
			LOGGER.error("Application Exception: Error while saving the Object : ",e);
			throw new ApplicationException(e.getMessage());
		}
	}
	/**
	 * ToranaGenericResource
	 */
	@SuppressWarnings("unchecked")
	public List<Object> getAllObjectsDAO(String queryId, List<String> whereParams) throws DataException, ApplicationException{

		LOGGER.info("Inside getAllObjects of ToranaGenericDAOImpl");
		try{
			Query query = getSession().createQuery(GenericProperties.getProperty(queryId));
			int i 		= 0;

			for (String whereParam : whereParams) {
				query.setString(i, whereParam);
				i++;
			}
			return query.list();

		}catch(HibernateException e){
			LOGGER.error("Data Exception: Error while getting the Object : ",e);
			throw new DataException(e.getMessage());
		}catch(Exception e){
			LOGGER.error("Application Exception: Error while getting the Object : ",e);
			throw new ApplicationException(e.getMessage());
		}
	}
	/**
	 * ToranaGenericResource
	 */
	@SuppressWarnings("unchecked")
	public List<Object> getAllObjectByClassNameDAO(String className) throws DataException, ApplicationException{

		LOGGER.info("Inside getAllObjectByClassNameDAO of ToranaGenericDAOImpl");
		try{
			Criteria criteria = getSession().createCriteria(className);
			return criteria.list();
			
		}catch(HibernateException e){
			LOGGER.error("Data Exception: Error while getting the Object by class name: ",e);
			throw new DataException(e.getMessage());
		}catch(Exception e){
			LOGGER.error("Application Exception: Error while getting the Object by class name: ",e);
			throw new ApplicationException(e.getMessage());
		}
	}
	/**
	 * ToranaGenericResource
	 */
	public boolean deleteObjectByIdDAO(Class<? extends Object> className, Integer objectId) throws DataException, ApplicationException{
		LOGGER.info("Inside deleteObjectByIdDAO of ToranaGenericDAOImpl"+className+" :objectId: "+objectId);
		
		try{
			boolean result = _deleteById(className, objectId);
			_flush();
			return result;
		}catch(HibernateException e){
			LOGGER.error("Data Exception: Error while deleting the Object Based On Id : ",e);
			throw new DataException(e.getMessage());
		}catch(Exception e){
			LOGGER.error("Application Exception: Error while deleting the Object Based On Id : ",e);
			throw new ApplicationException(e.getMessage());
		}
	}
	/**
	 * ToranaGenericResource
	 */
	public boolean deleteObjectsByIdsDAO(Class<? extends Object> className, Integer... objectIds) throws DataException, ApplicationException{
		LOGGER.info("Inside deleteObjectByIdDAO of ToranaGenericDAOImpl"+className+" :objectId: "+objectIds);
		
		try{
			_deleteById(className, objectIds);
			_flush();
			return true;
		}catch(HibernateException e){
			LOGGER.error("Data Exception: Error while deleting the Object Based On Id : ",e);
			throw new DataException(e.getMessage());
		}catch(Exception e){
			LOGGER.error("Application Exception: Error while deleting the Object Based On Id : ",e);
			throw new ApplicationException(e.getMessage());
		}
	}
	/**
	 * ToranaGenericResource
	 */
	public Object getObjectByIdDAO(Class<? extends Object> className, Integer objectId) throws DataException, ApplicationException{
		LOGGER.info("Inside getObjectByIdDAO of ToranaGenericDAOImpl"+className+" :objectId: "+objectId);
		
		try{
			Object result = _get(className, objectId);
			return result;
		}catch(HibernateException e){
			LOGGER.error("Data Exception: Error while deleting the Object Based On Id : ",e);
			throw new DataException(e.getMessage());
		}catch(Exception e){
			LOGGER.error("Application Exception: Error while deleting the Object Based On Id : ",e);
			throw new ApplicationException(e.getMessage());
		}
	}
	/**
	 * ToranaGenericResource
	 */
	public Object[] getObjectsByIdsDAO(Class<? extends Object> className, Integer... objectIds) throws DataException, ApplicationException{
		LOGGER.info("Inside getObjectByIdDAO of ToranaGenericDAOImpl"+className+" :objectId: "+objectIds);
		
		try{
			Object[] result = _get(className, objectIds);
			return result;
		}catch(HibernateException e){
			LOGGER.error("Data Exception: Error while deleting the Object Based On Id : ",e);
			throw new DataException(e.getMessage());
		}catch(Exception e){
			LOGGER.error("Application Exception: Error while deleting the Object Based On Id : ",e);
			throw new ApplicationException(e.getMessage());
		}
	}

}
