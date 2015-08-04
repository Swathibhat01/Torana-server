package com.torana.cloud.biz;

import java.util.List;

import com.torana.biz.BaseService;
import com.torana.cloudelements.CloudElements;
import com.torana.hibernate.exception.ApplicationException;
import com.torana.hibernate.exception.DataException;
/**
 * 
 * @author Torana
 *
 *This is the Torana Cloud Elements Serveice
 */
public interface ToranaCloudElementsService extends BaseService{
	/**
	 * Retrieves List of Cloud Elements
	 * @return List of Cloud Elements
	 * @throws DataException
	 * @throws ApplicationException
	 */
	List<CloudElements> getAllCloudElementsService() throws DataException, ApplicationException;
	/**
	 * Saves Cloud Element Details
	 * @return Saved Cloud Element Object
	 * @throws DataException
	 * @throws ApplicationException
	 */
	CloudElements saveCloudElementService(CloudElements cloud) throws DataException, ApplicationException;
	/**
	 * Updates Cloud Element Details
	 * @return Updated Cloud Element Object
	 * @throws DataException
	 * @throws ApplicationException
	 */
	CloudElements updateCloudElementService(CloudElements cloud) throws DataException, ApplicationException;
	/**
	 * Retrieves Cloud Element given Id
	 * @return Cloud Element Object Retrieved
	 * @throws DataException
	 * @throws ApplicationException
	 */
	CloudElements getCloudElementByIdService(Integer cloudId) throws DataException, ApplicationException;
	/**
	 * Deletes Cloud Element given Id
	 * @return Boolean Value
	 * @throws DataException
	 * @throws ApplicationException
	 */
	boolean deleteCloudElementByIdService(Integer cloudId) throws DataException, ApplicationException;
}
