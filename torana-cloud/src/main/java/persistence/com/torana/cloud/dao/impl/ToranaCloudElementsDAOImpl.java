package com.torana.cloud.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.torana.hibernate.core.dao.impl.GenericDAOImpl;

import com.torana.cloud.dao.ToranaCloudElementsDAO;
import com.torana.cloudelements.CloudElements;
import com.torana.hibernate.exception.ApplicationException;
import com.torana.hibernate.exception.DataException;

public class ToranaCloudElementsDAOImpl extends GenericDAOImpl<CloudElements, Integer> implements ToranaCloudElementsDAO{

	private static final Logger LOGGER = LoggerFactory.getLogger(ToranaCloudElementsDAOImpl.class);
	/**
	 * Retrieves List of Cloud Elements
	 * @return List of Cloud Elements
	 * @throws DataException
	 * @throws ApplicationException
	 */
	public List<CloudElements> getAllCloudsDAO() throws DataException, ApplicationException{

		LOGGER.info("Inside getAllCloudsDAO of ToranaCloudElementsDAOImpl");
		try{
			Criteria criteria = getSession().createCriteria(CloudElements.class);
			return criteria.list();
			
		}catch(HibernateException e){
			throw new DataException(e.getMessage());
		}catch(Exception e){
			throw new ApplicationException(e.getMessage());
		}
	}
	/**
	 * Saves Cloud Element Details
	 * @return Saved Cloud Element Object
	 * @throws DataException
	 * @throws ApplicationException
	 */
	public CloudElements saveCloudElementDAO(CloudElements cloud) throws DataException, ApplicationException{
		LOGGER.info("Inside saveCloudElementDAO of ToranaCloudElementsDAOImpl");
		try{
			boolean newRecord = _saveOrUpdateIsNew(cloud);
			System.out.println("-Result of Save-"+newRecord);
			_flush();

			return cloud;
		}catch(HibernateException e){
			throw new DataException(e.getMessage());
		}catch(Exception e){
			throw new ApplicationException(e.getMessage());
		}
	}
	/**
	 * Updates Cloud Element Details
	 * @return Updated Cloud Element Object
	 * @throws DataException
	 * @throws ApplicationException
	 */
	public CloudElements updateCloudElementDAO(CloudElements cloud) throws DataException, ApplicationException{
		LOGGER.info("Inside updateCloudElementDAO of ToranaCloudElementsDAOImpl");
		try{
			_saveOrUpdate(cloud);
			_flush();

			return cloud;
		}catch(HibernateException e){
			throw new DataException(e.getMessage());
		}catch(Exception e){
			throw new ApplicationException(e.getMessage());
		}
	}
	/**
	 * Deletes Cloud Element given Id
	 * @return Boolean Value
	 * @throws DataException
	 * @throws ApplicationException
	 */
	public boolean deleteCloudElementByIdDAO(Integer objectId) throws DataException, ApplicationException{
		LOGGER.info("Inside deleteCloudElementByIdDAO of ToranaCloudElementsDAOImpl");
		try{
//			String queryString = "delete Cloud where cloudId ="+objectId;
//			Query query = getSession().createQuery(queryString);
//			int result = query.executeUpdate();
//			
//			return result >0 ? true:false;
			return removeById(objectId);
		}catch(HibernateException e){
			throw new DataException(e.getMessage());
		}catch(Exception e){
			throw new ApplicationException(e.getMessage());
		}
	}
	/**
	 * Retrieves Cloud Element given Id
	 * @return Cloud Element Object Retrieved
	 * @throws DataException
	 * @throws ApplicationException
	 */
	@Override
	public CloudElements getCloudElementByIdDAO(Integer cloudId) throws DataException,
			ApplicationException {
		LOGGER.info("Inside getCloudElementByIdDAO of ToranaCloudElementsDAOImpl");
		try{
//			 Criteria criteria = getSession().createCriteria(CloudElements.class)
//					.add(Restrictions.eq("active", (byte)1))
//					.add(Restrictions.eq("cloudId",cloudId));;
//					 return (CloudElements) criteria.uniqueResult();
			return find(cloudId);

		}catch(HibernateException e){
			LOGGER.error("Data Exception: Error while Getting the Cloud  : ",e);
			throw new DataException(e.getMessage());
		}catch(Exception e){
			LOGGER.error("Application Exception: Error while Getting the Cloud By Id : ",e);
			throw new ApplicationException(e.getMessage());
		}
	}
}
