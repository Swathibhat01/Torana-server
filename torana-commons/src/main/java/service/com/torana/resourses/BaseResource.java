package com.torana.resourses;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.torana.model.mapper.DataHelper;

/**
 * @author bhanuchander
 * @version 1.0
 * @since 25th Jun 2014
 * 
 * */
public class BaseResource {

	/** This {@value DataHelper} field injected from  resource-bean.xml */
	private DataHelper dataHelper;

	/** 
	 * This method is used for getting DataHelper object 
	 * @return {@link DataHelper}
	 */
	public DataHelper getDataHelper() {
		System.out.println("getDataHelper-->"+dataHelper);
		return dataHelper;
	}

	/** 
	 * This method is used for set the DataHelper object 
	 * @param {@link DataHelper}
	 * This method used for setting DataHelper object 
	 */
	public void setDataHelper(DataHelper dataHelper) {
		System.out.println("setDataHelper-->"+dataHelper);
		this.dataHelper = dataHelper;
	}

	/**
	 * This method is used to map given source object to destination class type
	 * @param source
	 * @param destinationType
	 * @return D
	 * */
	public <D> D map(Object source, Class<D> destinationType) {
		System.out.println("dataHelper.map(source, destinationType)******--->"+dataHelper.map(source, destinationType));
		return dataHelper.map(source, destinationType);
	}

	/**
	 * This method is used to create success response
	 * @param entity 
	 * @return {@link Response}
	 * */
	public Response createSuccessResponse(Object entity){
		return createResponse(Response.Status.OK,entity);
	}

	/**
	 * This method is used to create response
	 * @param status
	 * @param entity
	 * @return {@link Response} 
	 * */
	public Response createResponse(Response.Status status,Object entity){
		return Response.status(status).entity(entity).build();
	}

	/**
	 * This method is used to "create bad request" response
	 * @param message
	 * @return {@link Response}
	 * */
	public Response createBadRequestResponse(String message){
		return Response.status(Status.BAD_REQUEST).entity(message).type(MediaType.TEXT_HTML).build();
	}

	/**
	 * This method is used to create "data not found" response
	 * @param message
	 * @return {@link Response}
	 * */
	public Response createDataNotFoundResponse(String message){
		return Response.status(Status.NOT_FOUND).entity(message).type(MediaType.TEXT_HTML).build();
	}

	/**
	 * This method is used to create success response with given string
	 * @param message
	 * @return {@link Response}
	 * */
	public Response createSuccessResponse(String message){
		return Response.status(Status.OK).entity(message).type(MediaType.TEXT_HTML).build();
	}

	/**
	 * This method is used to create success response with given object
	 * @param entity
	 * @return {@link Response}
	 * */
	public Response createdSuccessResponse(Object entity){
		return Response.status(Response.Status.CREATED).entity(entity).build();
	}
	/**
	 * This method is used to create Conflict response with given object
	 * @param entity
	 * @return {@link Response}
	 * */

	public Response createConflictRequestResponse(String message){
		return Response.status(Status.CONFLICT).entity(message).type(MediaType.TEXT_HTML).build();
	}

	/**
	 * This method is used to "create unauthorized request" response
	 * @param message
	 * @return {@link Response}
	 * */
	public Response createUnauthorizedRequestResponse(String message){
		return Response.status(Status.UNAUTHORIZED).entity(message).type(MediaType.TEXT_HTML).build();
	}
}
