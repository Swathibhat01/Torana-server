package com.torana.hibernate.exception.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.JsonParseException;

import com.torana.hibernate.core.dao.util.HibernateSessionManager;

/**
 * @author bhanuchander
 * 
 * Handles all the json parsing exception in Jersy.
 */
@Provider
public class JsonParseExceptionMapper extends JsonExceptionMapper<JsonParseException>{



	@Override
	public Response toResponse(JsonParseException exception) {
		HibernateSessionManager.closeSession();

		// Build default response
		return  Response.status(400).entity("Unfortunately, the application cannot process your request at this time. There is a Json Parsing Error. ").type("text/plain").build();
	}
}