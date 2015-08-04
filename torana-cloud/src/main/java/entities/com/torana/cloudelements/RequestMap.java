package com.torana.cloudelements;

import java.util.Map;
/**
 * 
 * @author Torana
 *This is the Request Map Entity. It Holds the Neo4j Parameters.
 */
public class RequestMap {
	private Map<String,String> entityMap;
	private Integer id;
	private String name;

	/**
	 * Returns the Entity Map
	 * @return Entity Map
	 */
	public Map<String, String> getEntityMap() {
		return entityMap;
	}
	/**
	 * Sets the Entity Map Data
	 * @param entityMap
	 */
	public void setEntityMap(Map<String, String> entityMap) {
		this.entityMap = entityMap;
	}
	/**
	 * Return the Id of the Cloud
	 * @return Id of Cloud
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * Sets the Id of the Cloud
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * Returns the Name of the Cloud
	 * @return Cloud Name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Sets the Name of the Cloud
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
