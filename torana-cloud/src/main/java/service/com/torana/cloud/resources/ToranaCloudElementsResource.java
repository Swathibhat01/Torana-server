package com.torana.cloud.resources;

import static com.torana.cloud.util.CloudConstants.CLOUD_ELEMENTS_SERVICE;
import static com.torana.cloud.util.CloudConstants.FAILED_RESPONSE;
import static com.torana.cloud.util.CloudConstants.SUCCESS_RESPONSE;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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

import com.torana.cloud.snmp.DeviceInfoCollector;

import com.torana.cloud.snmp.service.SystemService;
import com.torana.quartz.jobs.SnmpCallsJobs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.torana.cloud.biz.ToranaCloudElementsService;
import com.torana.cloudelements.CloudElements;
import com.torana.hibernate.exception.ApplicationException;
import com.torana.hibernate.exception.DataException;
import com.torana.quartz.jobs.ToranaCronJobs;
import com.torana.resourses.BaseHibernateResource;
import com.torana.utils.Util;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
		/**
 * 
 * @author Torana
 *This is the Torana Cloud Elements Webservice.
 *
 **/
@Path("/cloudElements")
@Api(value = "/cloudElements", description = "Cloud Elements Resource")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ToranaCloudElementsResource extends BaseHibernateResource{
	private static final Logger LOGGER = LoggerFactory.getLogger(ToranaCloudElementsResource.class);
	Util util = new Util();
			SystemService systemService = new SystemService();

	/**
	 * Returns List of Cloud Elements from the Database.
	 *
	 *
	 * @return cloud elements list
	 **/
	@GET
	@Path("/getAllCloudElements")
	@ApiOperation(value = "Getting Objets from DB", notes = "This service is used to Getting Objets from DB")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Invalid Request"),
			@ApiResponse(code = 404, message = "Data Not Found")
	})
	public Response getAllCloudElements() throws WebApplicationException, DataException, ApplicationException,Exception{
		LOGGER.info("ToranaGenericResource: Method ToranaGenericResource.getAllObjectByClassName called :: ");//will look into logs later

		try	{
			ToranaCloudElementsService  cloudElementsService = (ToranaCloudElementsService) getServiceManager().getServicesMap().get(CLOUD_ELEMENTS_SERVICE);
			List<CloudElements> retrivedCloudElements = cloudElementsService.getAllCloudElementsService();

			return retrivedCloudElements != null ? createSuccessResponse(retrivedCloudElements) : createDataNotFoundResponse(FAILED_RESPONSE);
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
	 * Saves Cloud Element in Database. Accepts the cloud element object
	 *
	 *@param Cloud Element Object to be Saved
	 *@return Cloud Element Object Saved
	 **/
	@POST
	@Path("/saveCloudElement")
	@ApiOperation(value = "Insert CloudElement into DB", notes = "This service is to Create a CloudElement in DB")// will see wt reponse we need to set
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Invalid Request"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public Response saveCloudElement(CloudElements cloud) throws WebApplicationException, DataException, ApplicationException,Exception{
		LOGGER.info("ToranaCloudElementResource: Method ToranaCloudElementResource.saveCloudElement");//will look into logs later

		try {
			ToranaCloudElementsService cloudElementsService = (ToranaCloudElementsService) getServiceManager().getServicesMap().get(CLOUD_ELEMENTS_SERVICE);
			CloudElements savedCloudElement = cloudElementsService.saveCloudElementService(cloud);

			LOGGER.info("------------------------" + cloud.getType());//will look into logs later
			if (cloud.getType().equals("demo-server")) {
				InputStream inputStream = this.getClass().getClassLoader()
						.getResourceAsStream("testing.txt");

				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				StringBuilder builder = new StringBuilder();
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					builder.append(line + " ");
				}
				util.executeQuery(builder.toString());
			} else if (cloud.getType().equals("Openstack") && cloud.getDiscover() == 1) {
				ToranaCronJobs.dumpOpenStackToNeo4jCron();
			} else {
				if (cloud.getType().equals("SNMP-Switch")) {
					DeviceInfoCollector deviceInfoCollector = new DeviceInfoCollector(cloud.getIpAddress(), cloud.getSnmpPort(), cloud.getSnmpCommunity(), cloud.getSnmpVersion());
					systemService.createOrUpdate(deviceInfoCollector.setValuesToSystem(cloud.getName()));
					SnmpCallsJobs snmpCallsJobs = new SnmpCallsJobs(cloud.getIpAddress(), cloud.getSnmpPort(), cloud.getSnmpCommunity(), cloud.getSnmpVersion() , cloud.getName());
					ToranaCronJobs.dumpSNMPtoNeo4jCron();
				}
			}
//				Builders b = new Builders();
//				String s="",s1="";
//				StringBuilder builder = new StringBuilder();
//				boolean inQuotes = false;
//				char currentChar_req_close = ' ';
//				ArrayList a = new ArrayList();
//				List<String> result = new ArrayList();
//				System.out.println("\nSizetest::::"+result.size());
//				Map m = new HashMap<String, Map>();
//
//				OSClient os = OSFactory.builder().endpoint("http://8.21.28.222:5000/v2.0")
//						.credentials("facebook100000823628974","pyGb1620umskhzrU")
//						.tenantName("facebook100000823628974")
//						.authenticate();
//
//				// Find all running Servers
//				List<? extends Server> servers = os.compute().servers().list();
//				System.out.println("Servers:\n"+servers);
//
//				if(servers.size() > 0)
//				{
//				s = servers.get(0).toString();
//				s1 = s.substring(s.indexOf('{')+1, s.length()-1);
//				System.out.println("\nNew String:::"+builder);
//				}
//
//				//start
//
//
//				   builder = new StringBuilder(s1);
//				   //Start For Loop
//				   for (int currentIndex = 0; currentIndex < builder.length(); currentIndex++) {
//				       char currentChar = builder.charAt(currentIndex);
//				       if (currentChar == '{')
//				       {
//				    	   a.add(currentChar);
//				    	   //System.out.println("currentChar:::"+a);
//				    	   inQuotes = true; // toggle state
//				       }
//				       else if(currentChar == '}')
//				       {
//				    	   if(a.size() > 0)
//				    	   a.remove(0);
//				    	   if(a.size() == 0)
//				    	   inQuotes = false; // toggle state
//				       }
//				      /*if(inQuotes){
//				    	System.out.println("inQuotes is ttttttttrue:::"+inQuotes);
//				      }else
//				      {
//				    	  System.out.println("inQuotes is fffffffffffalse:::"+inQuotes);
//				      }*/
//				       if (currentChar == ',' && inQuotes) {
//				    	   builder.setCharAt(currentIndex, ';');
//					       }
//				   }
//				   //End For Loop
//				   result = Arrays.asList(builder.toString().split(","));
//
//				   /*for (Object temp : result) {
//					   System.out.println("\ntemp::::"+temp);
//				   }*/
//
//				//end
//				if(result.size() > 1){
//				for (Object temp : result) {
//					String temp1 = temp.toString();
//					temp1 = temp1.replaceFirst("=", "==");
//					System.out.println("\nTemp1:::"+temp1);
//					List<String> al = new ArrayList<String>(Arrays.asList(temp1.split("==")));
//					System.out.println("\nchecking::::::::"+al+"\tlength::::"+al.size());
//					String sa = al.get(1);
//					System.out.println("sa:::::::::"+sa);
//					if(sa.contains(";"))
//					{System.out.println("sa:::::::::ttttttttttttttttttttt");
//						Map m1 = new HashMap();
//						String value = al.get(0);
//						if(value.equals("id")){
//							value = "ServerID";
//						}
//						m.put(value.replace(" ", ""), m1.put(al.get(0), al.get(1)));
//						System.out.println("m1::::::::"+m1);
//						System.out.println("m::::::::"+m);
//					}
//					else
//					{
//						String value = al.get(0);
//						if(value.equals("id")){
//							value = "ServerID";
//						}
//						m.put(value.replace(" ", ""), al.get(1));
//					}
//				}}
//					System.out.println("\nm:::::::::"+m);
//					System.out.println("\nm:::::::::"+m.entrySet());
//				Iterator iterator = m.entrySet().iterator();
//				if(m.size()>0){
//					StringBuilder strBuilder=new StringBuilder("CREATE (n {");
//					int count=0;
//					while (iterator.hasNext()) {
//						Map.Entry mapEntry = (Map.Entry) iterator.next();
//						if(mapEntry.getKey() != "id" ){
//							strBuilder.append(""+mapEntry.getKey()+":'"+mapEntry.getValue()+"',");
//						}
//					}
//					strBuilder.setLength(strBuilder.length() - 1);
//					strBuilder.append("}) return n");
//					util.executeQuery(strBuilder.toString());
//				}

				return createdSuccessResponse(savedCloudElement);
			}

		catch(DataException de){
			LOGGER.error("Data Exception: Error while saving the Cloud Element ",de);
			throw de;
		}catch(ApplicationException ae){
			LOGGER.error("Application Exception: Error while saving Cloud Element",ae);
			throw ae;
		}catch(Exception e){
			LOGGER.error("Exception: Error while Cloud Element",e);
			throw e;
		}
	}
	/**
	 * Updates Cloud Element in Database. Accepts the cloud element object
	 * 
	 *@param Cloud Element Object to be Updated
	 *@return Cloud Element Object Updated
	 **/
	@PUT
	@Path("/updateCloudElement")
	@ApiOperation(value = "Update Cloud Element into DB", notes = "This service is to Update a Cloud Element(row) in DB")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Invalid Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") 
	})
	public Response updateCloudElement(CloudElements cloud ) throws WebApplicationException, DataException, ApplicationException,Exception{
		LOGGER.info("ToranaCloudElementsResource: Method ToranaCloudElementsResource.updateCloudElement called " );//will look into logs later

		try	{
			ToranaCloudElementsService  cloudElementsService = (ToranaCloudElementsService) getServiceManager().getServicesMap().get(CLOUD_ELEMENTS_SERVICE);
			CloudElements updatedCloudElement = cloudElementsService.updateCloudElementService(cloud);

			return createdSuccessResponse(updatedCloudElement);
		}
		catch(DataException de){
			LOGGER.error("Data Exception: Error while updating the Cloud Element",de);
			throw de;
		}catch(ApplicationException ae){
			LOGGER.error("Application Exception: Error while updating the Cloud Element ",ae);
			throw ae;
		}catch(Exception e){
			LOGGER.error("Exception: Error while updating the Cloud Element ",e);
			throw e;
		}

	}
	/**
	 * Retrieve Cloud Element based on the Id. Accepts the Cloud element id
	 * 
	 *@param Cloud Element id
	 *@return Cloud Element Object 
	 **/
	@GET
	@Path("/getCloudElementById")
	@ApiOperation(value = "Getting Objets from DB", notes = "This service is used to Getting Objets from DB")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Invalid Request"),
			@ApiResponse(code = 404, message = "Data Not Found") 
	})
	public Response getCloudElementById(
			@ApiParam(value = "Id to get CloudElement", required = true) @QueryParam(value = "cloudId") Integer cloudId
			) throws WebApplicationException, DataException, ApplicationException,Exception{
		LOGGER.info("ToranaGenericResource: Method ToranaGenericResource.getAllObjectByClassName called :: ");//will look into logs later

		try	{
			ToranaCloudElementsService  cloudElementsService = (ToranaCloudElementsService) getServiceManager().getServicesMap().get(CLOUD_ELEMENTS_SERVICE);
			CloudElements retrivedCloudElements = cloudElementsService.getCloudElementByIdService(cloudId);

			return retrivedCloudElements != null ? createSuccessResponse(retrivedCloudElements) : createDataNotFoundResponse(FAILED_RESPONSE);
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
	 * Deletes Cloud Element from Database. Accepts the cloud element id
	 * 
	 *@param Cloud Element id
	 *@return Success or Failure
	 **/
	@DELETE
	@Path("/deleteCloudElementById")
	@ApiOperation(value = "Delete CloudElement based on ID", notes = "This service is to delete CloudElement by Id", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Invalid Request"),
			@ApiResponse(code = 404, message = "Object is not found") 
	})
	public Response deleteCloudElementById(
			@ApiParam(value = "Id to delete CloudElement", required = true) @QueryParam(value = "cloudId") Integer cloudId
			) throws WebApplicationException, DataException, ApplicationException,Exception{
		LOGGER.info("ToranaCloudElementsResource: Method ToranaCloudElementsResource.deleteCloudElementById called");
		try	{

			ToranaCloudElementsService  cloudElementsService = (ToranaCloudElementsService) getServiceManager().getServicesMap().get(CLOUD_ELEMENTS_SERVICE);
			CloudElements cloud = cloudElementsService.getCloudElementByIdService(cloudId);
			boolean result=cloudElementsService.deleteCloudElementByIdService(cloudId);
			if(cloud.getType().equals("demo-server")){
			String query="MATCH (n) OPTIONAL MATCH (n)-[r]-() DELETE n,r";
			util.executeQuery(query);
			}
			return result ? createSuccessResponse(SUCCESS_RESPONSE) : createDataNotFoundResponse(FAILED_RESPONSE);
		}
		catch(DataException de){
			LOGGER.error("Data Exception: Error while deleting the CloudElement Based On Id : ",de);
			throw de;
		}catch(ApplicationException ae){
			LOGGER.error("Application Exception: Error while deleting the CloudElement Based On Id : ",ae);
			throw ae;
		}catch(Exception e){
			LOGGER.error("Exception: Error while deleting the CloudElement Based On Id: ",e);
			throw e;
		}
	}
}
