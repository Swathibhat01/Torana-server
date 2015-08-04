package com.torana.cloud.biz.impl;

import static com.torana.cloud.util.CloudConstants.CLOUD_ELEMENTS_DAO;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.torana.biz.impl.BaseServiceImpl;
import com.torana.cloud.biz.ToranaCloudElementsService;
import com.torana.cloud.dao.ToranaCloudElementsDAO;
import com.torana.cloudelements.CloudElements;
import com.torana.hibernate.exception.ApplicationException;
import com.torana.hibernate.exception.DataException;

public class ToranaCloudElementsServiceImpl extends BaseServiceImpl implements ToranaCloudElementsService{

	private static final Logger LOGGER = LoggerFactory.getLogger(ToranaCloudElementsServiceImpl.class);
	/**
	 * Retrieves List of Cloud Elements
	 * @return List of Cloud Elements
	 * @throws DataException
	 * @throws ApplicationException
	 */
	public List<CloudElements> getAllCloudElementsService() throws DataException, ApplicationException{
		ToranaCloudElementsDAO cloudElementsDAO = (ToranaCloudElementsDAO) getDaoFactory().getDaosMap().get(CLOUD_ELEMENTS_DAO);
		return cloudElementsDAO.getAllCloudsDAO();
	}
	/**
	 * Saves Cloud Element Details
	 * @return Saved Cloud Element Object
	 * @throws DataException
	 * @throws ApplicationException
	 */
	public CloudElements saveCloudElementService(CloudElements cloud) throws DataException,
			ApplicationException {
		ToranaCloudElementsDAO  cloudElementsDAO = (ToranaCloudElementsDAO) getDaoFactory().getDaosMap().get(CLOUD_ELEMENTS_DAO);
		return cloudElementsDAO.saveCloudElementDAO(cloud);
	}
	/**
	 * Updates Cloud Element Details
	 * @return Updated Cloud Element Object
	 * @throws DataException
	 * @throws ApplicationException
	 */
	public CloudElements updateCloudElementService(CloudElements cloud) throws DataException,
			ApplicationException {
		ToranaCloudElementsDAO cloudElementsDAO = (ToranaCloudElementsDAO) getDaoFactory().getDaosMap().get(CLOUD_ELEMENTS_DAO);
		return cloudElementsDAO.updateCloudElementDAO(cloud);
	}
	/**
	 * Deletes Cloud Element given Id
	 * @return Boolean Value
	 * @throws DataException
	 * @throws ApplicationException
	 */
	public boolean deleteCloudElementByIdService(Integer cloudId)
			throws DataException, ApplicationException {
		ToranaCloudElementsDAO  cloudElementsDAO = (ToranaCloudElementsDAO) getDaoFactory().getDaosMap().get(CLOUD_ELEMENTS_DAO);
		return cloudElementsDAO.deleteCloudElementByIdDAO(cloudId);
	}
	/**
	 * Retrieves Cloud Element given Id
	 * @return Cloud Element Object Retrieved
	 * @throws DataException
	 * @throws ApplicationException
	 */
	@Override
	public CloudElements getCloudElementByIdService(Integer cloudId)
			throws DataException, ApplicationException {
		ToranaCloudElementsDAO  cloudElementsDAO = (ToranaCloudElementsDAO) getDaoFactory().getDaosMap().get(CLOUD_ELEMENTS_DAO);
		return cloudElementsDAO.getCloudElementByIdDAO(cloudId);
	}

}
