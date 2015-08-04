package com.torana.cloud.dao;

import java.util.List;

import com.torana.cloudelements.CloudElements;
import com.torana.hibernate.exception.ApplicationException;
import com.torana.hibernate.exception.DataException;

public interface ToranaCloudElementsDAO {
	/**
	 * Retrieves List of Cloud Elements
	 * @return List of Cloud Elements
	 * @throws DataException
	 * @throws ApplicationException
	 */
	List<CloudElements> getAllCloudsDAO() throws DataException, ApplicationException;
	/**
	 * Saves Cloud Element Details
	 * @return Saved Cloud Element Object
	 * @throws DataException
	 * @throws ApplicationException
	 */
	CloudElements saveCloudElementDAO(CloudElements cloud) throws DataException, ApplicationException;
	/**
	 * Updates Cloud Element Details
	 * @return Updated Cloud Element Object
	 * @throws DataException
	 * @throws ApplicationException
	 */
	CloudElements updateCloudElementDAO(CloudElements cloud) throws DataException, ApplicationException;
	/**
	 * Retrieves Cloud Element given Id
	 * @return Cloud Element Object Retrieved
	 * @throws DataException
	 * @throws ApplicationException
	 */
	CloudElements getCloudElementByIdDAO(Integer cloudId)throws DataException, ApplicationException;
	/**
	 * Deletes Cloud Element given Id
	 * @return Boolean Value
	 * @throws DataException
	 * @throws ApplicationException
	 */
	boolean deleteCloudElementByIdDAO(Integer cloudId) throws DataException, ApplicationException;
}
