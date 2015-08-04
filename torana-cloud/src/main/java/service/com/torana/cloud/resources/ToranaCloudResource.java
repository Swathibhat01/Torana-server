package com.torana.cloud.resources;

import static com.torana.cloud.util.CloudConstants.CLOUD_SERVICE;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.torana.cloud.biz.ToranaCloudService;
import com.torana.cloudelements.RequestMap;
import com.torana.hibernate.exception.ApplicationException;
import com.torana.hibernate.exception.DataException;
import com.torana.resourses.BaseHibernateResource;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

/**
 * 
 * @author Torana
 *This is the Torana Cloud  Webservice.
 *
 **/
@Path("/cloud")
@Api(value = "/cloud", description = "Cloud Resource")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ToranaCloudResource extends BaseHibernateResource{

	private static final String SERVER_ROOT_URI = "http://localhost:7474/db/data/";
	private static final Logger LOGGER = LoggerFactory.getLogger(ToranaCloudResource.class);
	/**
	 * Returns List of Cloud  from the Neo4j Database.
	 * 
	 *
	 * @return clouds list
	 **/
	@GET
	@Path("/getAllClouds")
	@ApiOperation(value = "get All Clouds", notes = "This service is to get All Clouds", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Invalid Details"),
			@ApiResponse(code = 404, message = "Invalid Details"),
			@ApiResponse(code = 401, message = "Invalid Details") 
	})
	public Response getAllClouds() throws DataException, ApplicationException,Exception {

		try	{

			ToranaCloudService  genericService = (ToranaCloudService) getServiceManager().getServicesMap().get(CLOUD_SERVICE);
			ClientResponse response = genericService.getAllCloudsService();

			return createSuccessResponse(response.getEntity( String.class ));
		}
		catch(DataException de){
			LOGGER.error("Data Exception: Error while retrieving the clouds : ",de);
			throw de;
		}catch(ApplicationException ae){
			LOGGER.error("Application Exception: Error while retrieving the clouds : ",ae);
			throw ae;
		}catch(Exception e){
			LOGGER.error("Exception: Error while retrieving the clouds : ",e);
			throw e;
		}

	}
	/**
	 * Retrieve Cloud based on the parameter. Accepts the Cloud parameters
	 * 
	 *@param Cloud typelabel
	 *@param Cloud type
	 *@return Cloud Element Object 
	 **/
	@GET
	@Path("/getAllCloudsByType")
	@ApiOperation(value = "get All Clouds", notes = "This service is to get All Clouds", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Invalid Details"),
			@ApiResponse(code = 404, message = "Invalid Details"),
			@ApiResponse(code = 401, message = "Invalid Details") 
	})
	public Response getAllCloudsByType(@ApiParam(
			value = "typelabel", required = true) @QueryParam(value = "typelabel")
	String typelabel,@ApiParam(
			value = "type", required = true) @QueryParam(value = "type")
	String type) throws DataException, ApplicationException,Exception {

		try	{

			ToranaCloudService  genericService = (ToranaCloudService) getServiceManager().getServicesMap().get(CLOUD_SERVICE);
			ClientResponse response = genericService.getAllCloudsByTypeService(typelabel,type);

			return createSuccessResponse(response.getEntity( String.class ));
		}
		catch(DataException de){
			LOGGER.error("Data Exception: Error while retrieving the clouds : ",de);
			throw de;
		}catch(ApplicationException ae){
			LOGGER.error("Application Exception: Error while retrieving the clouds : ",ae);
			throw ae;
		}catch(Exception e){
			LOGGER.error("Exception: Error while retrieving the clouds : ",e);
			throw e;
		}

	}
	/**
	 * Retrieve Cloud based on the types. Accepts the Cloud type 
	 * 
	 *@param Cloud type List
	 *@return Cloud Elements List 
	 **/
	@GET
	@Path("/getAllCloudsByList")
	@ApiOperation(value = "get All Clouds", notes = "This service is to get All Clouds", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Invalid Details"),
			@ApiResponse(code = 404, message = "Invalid Details"),
			@ApiResponse(code = 401, message = "Invalid Details") 
	})
	public Response getAllCloudsByList(@ApiParam(
			value = "typelabel", required = true) @QueryParam(value = "types")
	List<String> types) throws DataException, ApplicationException,Exception {

		try	{

			ToranaCloudService  genericService = (ToranaCloudService) getServiceManager().getServicesMap().get(CLOUD_SERVICE);
			ClientResponse response = genericService.getAllCloudsByListService(types);

			return createSuccessResponse(response.getEntity( String.class ));
		}
		catch(DataException de){
			LOGGER.error("Data Exception: Error while retrieving the clouds : ",de);
			throw de;
		}catch(ApplicationException ae){
			LOGGER.error("Application Exception: Error while retrieving the clouds : ",ae);
			throw ae;
		}catch(Exception e){
			LOGGER.error("Exception: Error while retrieving the clouds : ",e);
			throw e;
		}

	}
	/**
	 * Retrieve Cloud based on the Id. Accepts the Cloud Id as parameter
	 * 
	 *@param Cloud Id
	 *@return Cloud Object 
	 **/
	@GET
	@Path("/getCloudById")
	@ApiOperation(value = "get Cloud By ID", notes = "This service is to get Cloud By Id", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Invalid Details"),
			@ApiResponse(code = 404, message = "Invalid Details"),
			@ApiResponse(code = 401, message = "Invalid Details") 
	})
	public Response getCloudById(
			@ApiParam(value = "Cloud Id", required = true) @QueryParam(value = "cloudId") String cloudId
			) throws DataException, ApplicationException,Exception {

		try	{

			ToranaCloudService  genericService = (ToranaCloudService) getServiceManager().getServicesMap().get(CLOUD_SERVICE);
			ClientResponse response = genericService.getCloudByIdService(cloudId);

			return createSuccessResponse(response.getEntity( String.class ));
		}
		catch(DataException de){
			LOGGER.error("Data Exception: Error while retrieving the clouds : ",de);
			throw de;
		}catch(ApplicationException ae){
			LOGGER.error("Application Exception: Error while retrieving the clouds : ",ae);
			throw ae;
		}catch(Exception e){
			LOGGER.error("Exception: Error while retrieving the clouds : ",e);
			throw e;
		}

	}

	@GET
	@Path("/getAllCloudsOld")
	@ApiOperation(value = "get Cloud By ID", notes = "This service is to get Cloud By Id", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Invalid Details"),
			@ApiResponse(code = 404, message = "Invalid Details"),
			@ApiResponse(code = 401, message = "Invalid Details") 
	})
	public Response getAllCloudsOld() {

		String query =  "MATCH (n)  RETURN n";

		// START SNIPPET: queryAllNodes
		String txUri = SERVER_ROOT_URI + "transaction/commit";
		WebResource resource = Client.create().resource( txUri );

		String payload = "{\"statements\" : [ {\"statement\" : \"" +query + "\"} ]}";
		ClientResponse response = resource
				.accept( MediaType.APPLICATION_JSON )
				.type( MediaType.APPLICATION_JSON )
				.entity( payload )
				.get( ClientResponse.class );
		return createSuccessResponse(response.getEntity( String.class ));

	}
	/**
	 * Updates Cloud Details in Neo4j Database. Accepts the cloud properties
	 * 
	 *@param Cloud Object to be Updated
	 *@return Success or Failure
	 **/
	@PUT
	@Path("/updateCloudProperties")
	@ApiOperation(value = "get Cloud By ID", notes = "This service is to get Cloud By Id", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Invalid Details"),
			@ApiResponse(code = 404, message = "Invalid Details"),
			@ApiResponse(code = 401, message = "Invalid Details") 
	})
	public Response updateCloudProperties(
			RequestMap node
			) throws DataException, ApplicationException {

		try	{
			ToranaCloudService  genericService = (ToranaCloudService) getServiceManager().getServicesMap().get(CLOUD_SERVICE);
			ClientResponse response = genericService.updateCloudService(node);

			return createSuccessResponse(response.getEntity( String.class ));
		}
		catch(DataException de){
			LOGGER.error("Data Exception: Error while retrieving the clouds : ",de);
			throw de;
		}catch(ApplicationException ae){
			LOGGER.error("Application Exception: Error while retrieving the clouds : ",ae);
			throw ae;
		}catch(Exception e){
			LOGGER.error("Exception: Error while retrieving the clouds : ",e);
			throw e;
		}

	}

}