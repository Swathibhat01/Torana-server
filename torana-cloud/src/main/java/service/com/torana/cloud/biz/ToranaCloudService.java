package com.torana.cloud.biz;

import java.util.List;

import com.sun.jersey.api.client.ClientResponse;
import com.torana.biz.BaseService;
import com.torana.cloudelements.RequestMap;
import com.torana.hibernate.exception.ApplicationException;
import com.torana.hibernate.exception.DataException;

public interface ToranaCloudService extends BaseService{
	/**
	 * Returns List of Cloud  from the Neo4j Database.
	 * 
	 *
	 * @return clouds List Details 
	 **/
	ClientResponse getAllCloudsService() throws DataException, ApplicationException;
	/**
	 * Returns Cloud given Id  from the Neo4j Database.
	 * 
	 *
	 * @return clouds Details 
	 **/
	ClientResponse getCloudByIdService(String cloudId) throws DataException, ApplicationException;
	/**
	 * Returns Cloud given label and value  from the Neo4j Database.
	 * 
	 *
	 * @return clouds Details 
	 **/
	ClientResponse getAllCloudsByTypeService(String typelabel,String type) throws DataException, ApplicationException;
	/**
	 * Update Cloud in the Neo4j Database.
	 * 
	 *
	 * @return clouds Details 
	 **/
	ClientResponse updateCloudService(RequestMap node) throws DataException, ApplicationException;
	/**
	 * Returns Clouds List given type  from the Neo4j Database.
	 * 
	 *
	 * @return clouds List 
	 **/
	ClientResponse getAllCloudsByListService(List<String> types) throws DataException, ApplicationException;
}
