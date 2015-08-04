package com.torana.cloud.util;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
/**
 * Util Class to perform generic functionalities
 * */
public class Util {
	private final String SERVER_ROOT_URI = "http://localhost:7474/db/data/";
	/**
	 * Executes the neo4j query and retrieves the data
	 * @param query
	 * @return Client Response 
	 */
	public ClientResponse executeQuery(String query){
		// START SNIPPET: queryAllNodes
		System.out.println(query+"************************");
				String txUri = SERVER_ROOT_URI + "transaction/commit";
				WebResource resource = Client.create().resource( txUri );

				String payload = "{\"statements\" : [ {\"statement\" : \"" +query + "\"} ]}";
				ClientResponse response = resource
						.accept( MediaType.APPLICATION_JSON )
						.type( MediaType.APPLICATION_JSON )
						.entity( payload )
						.get( ClientResponse.class );
				return response;
	}

}
