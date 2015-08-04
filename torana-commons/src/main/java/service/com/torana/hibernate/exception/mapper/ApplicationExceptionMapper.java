package com.torana.hibernate.exception.mapper;

/**
 * @author sudamurali
 * ApplicationExceptionMapper
 * 
 * This class is to handle all the application exceptions on the application and return
 * response with appropriate status code.
 */

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.torana.hibernate.core.dao.util.HibernateSessionManager;
import com.torana.hibernate.exception.ApplicationException;

/**
 * <p>Provider to provide the following to Jersey framework:</p>
 * <ul>
 * <li>Provision of general runtime exception to response mapping</li>
 * </ul>
 */
@Provider
public class ApplicationExceptionMapper implements ExceptionMapper<ApplicationException> {


  public Response toResponse(ApplicationException exception) {
System.out.println("INNNNN 8888ApplicationExceptionMapper*****");
	  HibernateSessionManager.closeSession();
	  
    // Build default response
	return  Response.status(500).entity("Unfortunately, the application cannot process your request at this time. There is an ApplicationException").type("text/plain").build();
	
  }

}