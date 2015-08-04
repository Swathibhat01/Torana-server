package com.torana.hibernate.exception.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.map.JsonMappingException;

import com.torana.hibernate.core.dao.util.HibernateSessionManager;

/**
 * @author bhanuchander
 * 
 * Handles all the json mapping exception in Jersy.
 */
@Provider
public class JsonMappingExceptionMapper extends JsonExceptionMapper<JsonMappingException> {

	@Override
	public Response toResponse(JsonMappingException exception) {
		HibernateSessionManager.closeSession();
		// Build default response
		return  Response.status(400).entity("Unfortunately, the application cannot process your request at this time. There is a Json Mapping Error . ").type("text/plain").build();

	}	
}
