package com.torana.hibernate.exception.mapper;

/**
 * @author bhanuchander
 * ApplicationExceptionMapper
 * 
 * This class is to handle all the application exceptions on the application and return
 * response with appropriate status code.
 */

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.torana.hibernate.core.dao.util.HibernateSessionManager;
import com.torana.hibernate.exception.DataException;

/**
 * <p>Provider to provide the following to Jersey framework:</p>
 * <ul>
 * <li>Provision of general runtime exception to response mapping</li>
 * </ul>
 */
@Provider
public class DataExceptionMapper implements ExceptionMapper<DataException> {
	
  public Response toResponse(DataException exception) {
	  HibernateSessionManager.closeSession();
	  
    // Build default response
	return  Response.status(500).entity("Unfortunately, the application cannot process your request at this time. There is a DataException. ").type("text/plain").build();
	
  }

}