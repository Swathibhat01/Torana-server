package com.torana.hibernate.exception.mapper;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.JsonProcessingException;

import com.torana.hibernate.core.dao.util.HibernateSessionManager;

/**
 * @author bhanuchander
 * 
 * Handles all the json processing exception in Jersy.
 */
@Provider
public class JsonProcessExceptionMapper extends JsonExceptionMapper<JsonProcessingException> {


	@Override
	public Response toResponse(JsonProcessingException exception) {

		HibernateSessionManager.closeSession();

		// Build default response
		return  Response.status(400).entity("Unfortunately, the application cannot process your request at this time. There is a Json Processing Error. ").type("text/plain").build();
	}

}