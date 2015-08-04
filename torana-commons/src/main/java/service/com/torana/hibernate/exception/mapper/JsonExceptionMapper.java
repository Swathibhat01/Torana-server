package com.torana.hibernate.exception.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import com.torana.hibernate.core.dao.util.HibernateSessionManager;
/**
 * @author bhanuchander
 * 
 * Handles all the json exception in Jersy.
 */
public abstract class JsonExceptionMapper<E extends Throwable> implements ExceptionMapper<E>{

	public abstract Response toResponse(E exception);

	protected Response createErrorResponse(String error){
		HibernateSessionManager.closeSession();

		// Build default response
		return  Response.status(400).entity("Unfortunately, the application cannot process your request at this time. There is a Json Exception . ").type("text/plain").build();

	}
}
