package com.torana.executable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.neo4j.cypher.ExecutionEngine;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.kernel.EmbeddedGraphDatabase;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;


public class Test {
	static Map myMap = new HashMap<String, String>();
	 private static final String SERVER_ROOT_URI = "http://localhost:7474/db/data/";
 public static void main(String[] args) {
	 addMap();
	 addToDb();
       
        //System.out.println(myMap);
       
 }
 public static void addMap(){
	 myMap.put("userId", "b48e33f579d04cb08e9db6274bb95a9f");
     myMap.put("powerstate", "0");
     myMap.put("created", "Wed Mar 11 10:50:26 IST 201");
     myMap.put("name", "test_instance");
     myMap.put("hostId", "ca4ebdde3389d93d7ec55d6365ab35850aabe73a931ce12f8954c175");
     myMap.put("diskconfig", "AUTO");
     myMap.put("status", "ERROR");
     myMap.put("id", "fde5c3e1-bddb-4167-a4da-bf92cb390caf");
 }
 public static void addToDb(){
	 //Map<String, Object> params = new HashMap<String, Object>();
	 //params.put( "props", myMap );
	 //String query = "CREATE (n:Person {"+myMap+"}) RETURN n";
	 Iterator iterator = myMap.entrySet().iterator();
	 
	 StringBuilder strBuilder=new StringBuilder("CREATE (n {");
 	int count=0;
 	while (iterator.hasNext()) {
 		Map.Entry mapEntry = (Map.Entry) iterator.next();
 		if(mapEntry.getKey() != "id" ){
 			strBuilder.append(""+mapEntry.getKey()+":'"+mapEntry.getValue()+"',");
 		}
 	}
 	strBuilder.setLength(strBuilder.length() - 1);
	strBuilder.append("}) return n");
	 getData(strBuilder.toString());
	// 
	 //GraphDatabaseService db=new EmbeddedGraphDatabase("/Users/dhanraj/Downloads/neo4j-community-2.1.6/data/graph.db", null, null);
	 //ExecutionEngine engine = new ExecutionEngine(db, null);
	// engine.execute( query, params );
 }
 public static void getData(String query){
		// START SNIPPET: queryAllNodes
				String txUri = SERVER_ROOT_URI + "transaction/commit";
				WebResource resource = Client.create().resource( txUri );

				String payload = "{\"statements\" : [ {\"statement\" : \"" +query + "\"} ]}";
				ClientResponse response = resource
						.accept( MediaType.APPLICATION_JSON )
						.type( MediaType.APPLICATION_JSON )
						.entity( payload )
						.get( ClientResponse.class );
				System.out.println(response.getEntity( String.class )+"***********");
	}
}
