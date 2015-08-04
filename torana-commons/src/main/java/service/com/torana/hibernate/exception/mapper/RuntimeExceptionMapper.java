package com.torana.hibernate.exception.mapper;

/**
 * @author sudamurali
 * RuntimeExceptionMapper
 * 
 * This class is to handle all the runtime exception on the application and return
 * response with appropriate status code.
 */

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.torana.hibernate.core.dao.util.HibernateSessionManager;

/**
 * <p>Provider to provide the following to Jersey framework:</p>
 * <ul>
 * <li>Provision of general runtime exception to response mapping</li>
 * </ul>
 */
@Provider
public class RuntimeExceptionMapper implements ExceptionMapper<RuntimeException> {


  public Response toResponse(RuntimeException runtime) {
System.out.println("in RuntimeExceptionMapper***");
    // Build default response
    Response defaultResponse = Response
      .serverError()
      .entity(new Object())
      .build();

    // Check for any specific handling
    if (runtime instanceof WebApplicationException) {

      return handleWebApplicationException(runtime, defaultResponse);
    }
    
    HibernateSessionManager.closeSession();
    // Use the default
    return defaultResponse;

  }

  private Response handleWebApplicationException(RuntimeException exception, Response defaultResponse) {
    WebApplicationException webAppException = (WebApplicationException) exception;

    // No logging
    if (webAppException.getResponse().getStatus() == 401) {
      return Response
        .status(Response.Status.UNAUTHORIZED)
        .entity("Unfortunately, the application cannot process your request at this time.").type("text/plain")
        .build();
    }
    if (webAppException.getResponse().getStatus() == 404) {
      return Response
        .status(Response.Status.NOT_FOUND)
        .entity("Unfortunately, the application cannot process your request at this time.").type("text/plain")
        .build();
    }
    HibernateSessionManager.closeSession();
    return defaultResponse;
  }

}