package com.torana.cloud.dao.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.sun.jersey.api.client.ClientResponse;
import com.torana.cloud.dao.ToranaCloudDAO;
import com.torana.cloudelements.RequestMap;
import com.torana.hibernate.core.dao.HibernateBaseDAO;
import com.torana.hibernate.exception.ApplicationException;
import com.torana.hibernate.exception.DataException;
import com.torana.utils.Util;

public class ToranaCloudDAOImpl extends HibernateBaseDAO implements ToranaCloudDAO{
	
	private final String SERVER_ROOT_URI = "http://localhost:7474/db/data/";
	Util util = new Util();
	/**
	 * Returns List of Cloud  from the Neo4j Database.
	 * 
	 *
	 * @return clouds List Details 
	 **/
	public ClientResponse getAllCloudsDAO() throws DataException, ApplicationException{
		//String query =  "MATCH (n)  RETURN n";
		//String query="START n=node(*) OPTIONAL MATCH n-[r]->() return ID(n),n,ID(r) as relId, type(r);";
		String query="START a=node(*) OPTIONAL MATCH (a)-[r]->(b) return ID(a),a,ID(b) as relId, type(r),r;";
		return util.executeQuery(query);
	}
	/**
	 * Returns Cloud given Id  from the Neo4j Database.
	 * 
	 *
	 * @return clouds Details 
	 **/
	public ClientResponse getAllCloudsByTypeDAO(String typelabel,String type) throws DataException, ApplicationException{
		//String query =  "MATCH (n)  RETURN n";
		String query="MATCH (n {"+typelabel+":'"+type+"'}) OPTIONAL MATCH n-[r]->(b) return ID(n),n,ID(b) as relId, type(r),r";
		return util.executeQuery(query);
	}
	/**
	 * Returns Cloud given label and value  from the Neo4j Database.
	 * 
	 *
	 * @return clouds Details 
	 **/
	public ClientResponse getCloudByIdDAO(String cloudId) throws DataException, ApplicationException{
		String query =  "START n=node("+cloudId+") RETURN n";
		return util.executeQuery(query);
	}
	/**
	 * Update Cloud in the Neo4j Database.
	 * 
	 *
	 * @return clouds Details 
	 **/
	public ClientResponse updateCloudDAO(RequestMap node) {
		
		System.out.println("IN DAO--->"+node.getId());
		
		//String query =  "MATCH (n)  RETURN n";
		Map<String, String> map = new HashMap<String, String>();
    	StringBuilder strBuilder=new StringBuilder("MATCH n");
    	Iterator iterator = node.getEntityMap().entrySet().iterator();
    	int count=0;
    	strBuilder.append(" WHERE id(n)= "+node.getId());
			strBuilder.append(" SET ");
    	while (iterator.hasNext()) {
    		Map.Entry mapEntry = (Map.Entry) iterator.next();
    		if(mapEntry.getKey() != "id" ){
    			strBuilder.append(" n."+mapEntry.getKey()+" = '"+mapEntry.getValue()+"',");
    		}
    	}
    	strBuilder.setLength(strBuilder.length() - 1);
    	strBuilder.append(" return n");
    	System.out.println(strBuilder);
		//String query="MATCH (n {"+node+"'}) OPTIONAL MATCH n-[r]->() return ID(n),n,ID(r) as relId, type(r)";
		return util.executeQuery(strBuilder.toString());
	}
	/**
	 * Returns Clouds List given type  from the Neo4j Database.
	 * 
	 *
	 * @return clouds List 
	 **/
	@Override
	public ClientResponse getAllCloudsByListDAO(List<String> types)
			throws DataException, ApplicationException {
		//String query =  "MATCH (n)  RETURN n";
//		StringBuilder strBuilder=new StringBuilder("MATCH n");
//		for(String type : types){
//			
//		}
		String query="MATCH (n) WHERE n.type IN "+types+" OPTIONAL MATCH n-[r]->(b) return ID(n),n,ID(b) as relId, type(r),r";
		return util.executeQuery(query);
	}
	
}
