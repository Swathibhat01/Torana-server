package com.torana.generic.resources;

import static com.torana.generic.util.GenericConstants.FAILED_RESPONSE;
import static com.torana.generic.util.GenericConstants.GENERIC_SERVICE;
import static com.torana.generic.util.GenericConstants.SUCCESS_RESPONSE;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.torana.generic.biz.ToranaGenericService;
import com.torana.generic.util.GenericProperties;
import com.torana.hibernate.exception.ApplicationException;
import com.torana.hibernate.exception.DataException;
import com.torana.resourses.BaseHibernateResource;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

/**
 * ToranaGenericResource
 */
@Path("/generic")
@Api(value = "/generic", description = "Generic Resources")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ToranaGenericResource extends BaseHibernateResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(ToranaGenericResource.class);
	
	/**
	 * ToranaGenericResource
	 */
	@POST
	@Path("/saveObject")
	@ApiOperation(value = "Insert Objet into DB", notes = "This service is to Create a object(row) in DB")// will see wt reponse we need to set
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Invalid Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") 
	})
	public Response saveObject(@ApiParam(value = "class Name", required = true) @QueryParam("className") String className,
			@ApiParam(value = "Object to create new record in db", required = true) String object ) throws WebApplicationException, DataException, ApplicationException,Exception{
		LOGGER.info("ToranaGenericResource: Method ToranaGenericResource.saveObject called :className: "+className);//will look into logs later

		try	{
			Object josn =  new ObjectMapper().readValue(object.getBytes(), Class.forName(className));

			ToranaGenericService  genericService = (ToranaGenericService) getServiceManager().getServicesMap().get(GENERIC_SERVICE);
			Object savedObject = genericService.saveObjectService(josn);

			return createdSuccessResponse(savedObject);
		}
		catch(DataException de){
			LOGGER.error("Data Exception: Error while saving the generic object :className: "+className,de);
			throw de;
		}catch(ApplicationException ae){
			LOGGER.error("Application Exception: Error while retriving the orders Based On userType :className: "+className,ae);
			throw ae;
		}catch(Exception e){
			LOGGER.error("Exception: Error while retriving the orders Based On userType:className: "+className,e);
			throw e;
		}
	}
	/**
	 * ToranaGenericResource
	 */
	@PUT
	@Path("/updateObject")
	@ApiOperation(value = "Update Objet into DB", notes = "This service is to Update a object(row) in DB")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Invalid Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") 
	})
	public Response updateObject(@ApiParam(value = "class Name", required = true) @QueryParam("className") String className,
			@ApiParam(value = "Object to update existing data", required = true) String object ) throws WebApplicationException, DataException, ApplicationException,Exception{
		LOGGER.info("ToranaGenericResource: Method ToranaGenericResource.saveObject called :::className: "+className );//will look into logs later

		try	{
			Object josn =  new ObjectMapper().readValue(object.getBytes(), Class.forName(className));

			ToranaGenericService  genericService = (ToranaGenericService) getServiceManager().getServicesMap().get(GENERIC_SERVICE);
			Object savedObject = genericService.updateObjectService(josn);

			return createdSuccessResponse(savedObject);
		}
		catch(DataException de){
			LOGGER.error("Data Exception: Error while updating the generic object :className: "+className,de);
			throw de;
		}catch(ApplicationException ae){
			LOGGER.error("Application Exception: Error while updating the generic object :className: "+className,ae);
			throw ae;
		}catch(Exception e){
			LOGGER.error("Exception: Error while updating the generic object :className: "+className,e);
			throw e;
		}

	}

	/**
	 * ToranaGenericResource
	 */
	@GET
	@Path("/getAllObjectByQueryId")
	@ApiOperation(value = "Getting Objets from DB", notes = "This service is used to Getting Objets from DB")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Invalid Request"),
			@ApiResponse(code = 404, message = "Data Not Found") 
	})
	public Response getAllObjectByQueryId(
			@ApiParam(value = "startFrom object to get Orders", required = true) @QueryParam(value = "queryId") String queryId,
			@ApiParam(value = "startFrom object to get Orders", required = false) @QueryParam(value = "whereParams") List<String> whereParams
			) throws WebApplicationException, DataException, ApplicationException,Exception{
		LOGGER.info("ToranaGenericResource: Method ToranaGenericResource.getAllObject called :: ");//will look into logs later

		try	{
			ToranaGenericService  genericService = (ToranaGenericService) getServiceManager().getServicesMap().get(GENERIC_SERVICE);
			List<Object> retrivedObjects 		= genericService.getAllObjectsService(queryId, whereParams);

			return retrivedObjects != null ? createSuccessResponse(retrivedObjects) : createDataNotFoundResponse(FAILED_RESPONSE);
		}
		catch(DataException de){
			LOGGER.error("Data Exception: Error while retriving the objects : ",de);
			throw de;
		}catch(ApplicationException ae){
			LOGGER.error("Application Exception: Error while retriving the objects : ",ae);
			throw ae;
		}catch(Exception e){
			LOGGER.error("Exception: Error while retriving the objects : ",e);
			throw e;
		}

	}

	/**
	 * ToranaGenericResource
	 */
	@GET
	@Path("/getAllObjectByClassName")
	@ApiOperation(value = "Getting Objets from DB", notes = "This service is used to Getting Objets from DB")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Invalid Request"),
			@ApiResponse(code = 404, message = "Data Not Found") 
	})
	public Response getAllObjectByClassName(
			@ApiParam(value = "startFrom object to get Orders", required = true) @QueryParam(value = "className") String className
			) throws WebApplicationException, DataException, ApplicationException,Exception{
		LOGGER.info("ToranaGenericResource: Method ToranaGenericResource.getAllObjectByClassName called :: ");//will look into logs later

		try	{
			ToranaGenericService  genericService = (ToranaGenericService) getServiceManager().getServicesMap().get(GENERIC_SERVICE);
			List<Object> retrivedObjects 		= genericService.getAllObjectByClassNameService(className);

			return retrivedObjects != null ? createSuccessResponse(retrivedObjects) : createDataNotFoundResponse(FAILED_RESPONSE);
		}
		catch(DataException de){
			LOGGER.error("Data Exception: Error while retriving all the objects by class name : ",de);
			throw de;
		}catch(ApplicationException ae){
			LOGGER.error("Application Exception: Error while retriving all the objects by class name :: ",ae);
			throw ae;
		}catch(Exception e){
			LOGGER.error("Exception: Error while retriving all the objects by class name :: ",e);
			throw e;
		}

	}

	/**
	 * ToranaGenericResource
	 */
	@DELETE
	@Path("/deleteObjectById")
	@ApiOperation(value = "Delete Object based on ID", notes = "This service is to delete Object by Id", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Invalid Request"),
			@ApiResponse(code = 404, message = "Object is not found") 
	})
	public Response deleteObjectById(@ApiParam(value = "className", required = true) @QueryParam("className") String className,
			@ApiParam(value = "Id to delete Object", required = true) @QueryParam(value = "objectId") Integer objectId
			) throws WebApplicationException, DataException, ApplicationException,Exception{
		LOGGER.info("ToranaGenericResource: Method ToranaGenericResource.deleteObjectById called");
		try	{

			Class<? extends Object> entityName = Class.forName(className);

			ToranaGenericService  genericService = (ToranaGenericService) getServiceManager().getServicesMap().get(GENERIC_SERVICE);
			boolean result=genericService.deleteObjectByIdService(entityName,objectId);
			return result ? createSuccessResponse(SUCCESS_RESPONSE) : createDataNotFoundResponse(FAILED_RESPONSE);
		}
		catch(DataException de){
			LOGGER.error("Data Exception: Error while deleting the orders Based On Id : ",de);
			throw de;
		}catch(ApplicationException ae){
			LOGGER.error("Application Exception: Error while deleting the orders Based On Id : ",ae);
			throw ae;
		}catch(Exception e){
			LOGGER.error("Exception: Error while deleting the orders Based On Id: ",e);
			throw e;
		}
	}

	/**
	 * ToranaGenericResource
	 */
	@GET
	@Path("/getObjectById")
	@ApiOperation(value = "Delete Object based on ID", notes = "This service is to delete Object by Id", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Invalid Request"),
			@ApiResponse(code = 404, message = "Object is not found") 
	})
	public Response getObjectById(@ApiParam(value = "class Name", required = true) @QueryParam("className") String className,
			@ApiParam(value = "Id to get Object", required = true) @QueryParam(value = "objectId") Integer objectId
			) throws WebApplicationException, DataException, ApplicationException,Exception{
		LOGGER.info("ToranaGenericResource: Method ToranaGenericResource.deleteOrdersById called");
		try	{

			Class<? extends Object> entityName = Class.forName(className);

			ToranaGenericService  genericService = (ToranaGenericService) getServiceManager().getServicesMap().get(GENERIC_SERVICE);
			Object result=genericService.getObjectsByIdsDAO(entityName,objectId);
			return result != null ? createSuccessResponse(result) : createDataNotFoundResponse(FAILED_RESPONSE);
		}
		catch(DataException de){
			LOGGER.error("Data Exception: Error while deleting the orders Based On Id : ",de);
			throw de;
		}catch(ApplicationException ae){
			LOGGER.error("Application Exception: Error while deleting the orders Based On Id : ",ae);
			throw ae;
		}catch(Exception e){
			LOGGER.error("Exception: Error while deleting the orders Based On Id: ",e);
			throw e;
		}
	}
	
	/**
	 * ToranaGenericResource
	 */
	@GET
	@Path("/loadProperties")
	@ApiOperation(value = "load genric properties to static", notes = "This service is load genric properties to static  ", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Invalid Request"),
			@ApiResponse(code = 404, message = "load genric properties to static failed") 
	})
	public Response loadProperties() throws WebApplicationException, DataException, ApplicationException,Exception{
		LOGGER.info("ToranaGenericResource: Method ToranaGenericResource.loadProperties called");
		try	{
			GenericProperties genericProperties = new GenericProperties(); 
			if(genericProperties.loadPropWS())
				return createSuccessResponse(SUCCESS_RESPONSE);
			return createSuccessResponse(FAILED_RESPONSE);

		}catch(Exception e){
			LOGGER.error("Exception: Error while loading the properties : ",e);
			throw e;
		}
	}

	/*	@DELETE
	@Path("/deleteObjectsByIds")
	@ApiOperation(value = "Delete Object based on ID", notes = "This service is to delete Object by Id", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Invalid Request"),
			@ApiResponse(code = 404, message = "Object is not found") 
	})
	public Response deleteObjectsByIds(@ApiParam(value = "class Name", required = true) @QueryParam("className") String className,
			@ApiParam(value = "Id to delete Object", required = true) @QueryParam(value = "objectId") Integer... objectId
			) throws WebApplicationException, DataException, ApplicationException,Exception{
		LOGGER.info("ToranaGenericResource: Method ToranaGenericResource.deleteObjectById called");
		try	{

			Class<? extends Object> entityName = Class.forName(className);

			ToranaGenericService  genericService = (ToranaGenericService) getServiceManager().getServicesMap().get(GENERIC_SERVICE);
			boolean result=genericService.deleteObjectsByIdsDAO(entityName,objectId);
			return result ? createSuccessResponse(SUCCESS_RESPONSE) : createDataNotFoundResponse(FAILED_RESPONSE);
		}
		catch(DataException de){
			LOGGER.error("Data Exception: Error while deleting the orders Based On Id : ",de);
			throw de;
		}catch(ApplicationException ae){
			LOGGER.error("Application Exception: Error while deleting the orders Based On Id : ",ae);
			throw ae;
		}catch(Exception e){
			LOGGER.error("Exception: Error while deleting the orders Based On Id: ",e);
			throw e;
		}
	}

	@GET
	@Path("/getObjectsBysIds")
	@ApiOperation(value = "Delete Object based on ID", notes = "This service is to delete Object by Id", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Invalid Request"),
			@ApiResponse(code = 404, message = "Object is not found") 
	})
	public Response getObjectsBysIds(@ApiParam(value = "class Name", required = true) @QueryParam("className") String className,
			@ApiParam(value = "Id to get Object", required = true) @QueryParam(value = "objectId") Integer... objectId
			) throws WebApplicationException, DataException, ApplicationException,Exception{
		LOGGER.info("ToranaGenericResource: Method ToranaGenericResource.deleteOrdersById called");
		try	{

			Class<? extends Object> entityName = Class.forName(className);

			ToranaGenericService  genericService = (ToranaGenericService) getServiceManager().getServicesMap().get(GENERIC_SERVICE);
			Object[] result=genericService.getObjectsByIdsDAO(entityName,objectId);
			return result != null ? createSuccessResponse(result) : createDataNotFoundResponse(FAILED_RESPONSE);
		}
		catch(DataException de){
			LOGGER.error("Data Exception: Error while deleting the orders Based On Id : ",de);
			throw de;
		}catch(ApplicationException ae){
			LOGGER.error("Application Exception: Error while deleting the orders Based On Id : ",ae);
			throw ae;
		}catch(Exception e){
			LOGGER.error("Exception: Error while deleting the orders Based On Id: ",e);
			throw e;
		}
	}*/

}
