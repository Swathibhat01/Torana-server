package com.torana.cloud.biz.impl;

import static com.torana.cloud.util.CloudConstants.CLOUD_DAO;

import java.util.List;

import com.sun.jersey.api.client.ClientResponse;
import com.torana.biz.impl.BaseServiceImpl;
import com.torana.cloud.biz.ToranaCloudService;
import com.torana.cloud.dao.ToranaCloudDAO;
import com.torana.cloudelements.RequestMap;
import com.torana.hibernate.exception.ApplicationException;
import com.torana.hibernate.exception.DataException;

public class ToranaCloudServiceImpl extends BaseServiceImpl implements ToranaCloudService{
	/**
	 * Returns List of Cloud  from the Neo4j Database.
	 * 
	 *
	 * @return clouds List Details 
	 **/
	public ClientResponse getAllCloudsService() throws DataException, ApplicationException{
		ToranaCloudDAO cloudDAO = (ToranaCloudDAO) getDaoFactory().getDaosMap().get(CLOUD_DAO);
		return cloudDAO.getAllCloudsDAO();
	}
	/**
	 * Returns Cloud given Id  from the Neo4j Database.
	 * 
	 *
	 * @return clouds Details 
	 **/
	public ClientResponse getCloudByIdService(String cloudId) throws DataException, ApplicationException{
		ToranaCloudDAO cloudDAO = (ToranaCloudDAO) getDaoFactory().getDaosMap().get(CLOUD_DAO);
		return cloudDAO.getCloudByIdDAO(cloudId);
	}
	/**
	 * Returns Cloud given label and value  from the Neo4j Database.
	 * 
	 *
	 * @return clouds Details 
	 **/
	public ClientResponse getAllCloudsByTypeService(String typelabel,String type) throws DataException, ApplicationException{
		ToranaCloudDAO cloudDAO = (ToranaCloudDAO) getDaoFactory().getDaosMap().get(CLOUD_DAO);
		return cloudDAO.getAllCloudsByTypeDAO(typelabel,type);
	}
	/**
	 * Update Cloud in the Neo4j Database.
	 * 
	 *
	 * @return clouds Details 
	 **/
	public ClientResponse updateCloudService(RequestMap node)
			throws DataException, ApplicationException {
		// TODO Auto-generated method stub
		ToranaCloudDAO cloudDAO = (ToranaCloudDAO) getDaoFactory().getDaosMap().get(CLOUD_DAO);
		return cloudDAO.updateCloudDAO(node);
	}
	/**
	 * Returns Clouds List given type  from the Neo4j Database.
	 * 
	 *
	 * @return clouds List 
	 **/
	@Override
	public ClientResponse getAllCloudsByListService(List<String> types)
			throws DataException, ApplicationException {
		// TODO Auto-generated method stub
		ToranaCloudDAO cloudDAO = (ToranaCloudDAO) getDaoFactory().getDaosMap().get(CLOUD_DAO);
		return cloudDAO.getAllCloudsByListDAO(types);
	}
	
}
