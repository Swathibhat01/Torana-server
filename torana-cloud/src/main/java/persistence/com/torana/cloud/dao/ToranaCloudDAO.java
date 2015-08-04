package com.torana.cloud.dao;

import java.util.List;

import com.sun.jersey.api.client.ClientResponse;
import com.torana.cloudelements.RequestMap;
import com.torana.hibernate.exception.ApplicationException;
import com.torana.hibernate.exception.DataException;

public interface ToranaCloudDAO{
	/**
	 * Returns List of Cloud  from the Neo4j Database.
	 * 
	 *
	 * @return clouds List Details 
	 **/
	ClientResponse getAllCloudsDAO() throws DataException, ApplicationException;
	/**
	 * Returns Cloud given Id  from the Neo4j Database.
	 * 
	 *
	 * @return clouds Details 
	 **/
	ClientResponse getCloudByIdDAO(String cloudId) throws DataException, ApplicationException;
	/**
	 * Returns Cloud given label and value  from the Neo4j Database.
	 * 
	 *
	 * @return clouds Details 
	 **/
	ClientResponse getAllCloudsByTypeDAO(String typelabel,String type) throws DataException, ApplicationException;
	/**
	 * Update Cloud in the Neo4j Database.
	 * 
	 *
	 * @return clouds Details 
	 **/
	ClientResponse updateCloudDAO(RequestMap node) throws DataException, ApplicationException;
	/**
	 * Returns Clouds List given type  from the Neo4j Database.
	 * 
	 *
	 * @return clouds List 
	 **/
	ClientResponse getAllCloudsByListDAO(List<String> types) throws DataException, ApplicationException;
}
