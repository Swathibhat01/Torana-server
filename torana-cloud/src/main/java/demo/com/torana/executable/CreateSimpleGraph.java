package com.torana.executable;

/**
 * Licensed to Neo Technology under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Neo Technology licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
/**
 * This is the test class
 * 
 * **/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class CreateSimpleGraph
{
    private static final String SERVER_ROOT_URI = "http://localhost:7474/db/data/";
    /**
     * This is the test main
     * 
     * **/
    public static void main( String[] args ) throws URISyntaxException
    {
        //checkDatabaseIsRunning();

        // START SNIPPET: nodesAndProps
        URI firstNode = createNode();
        addProperty( firstNode, "name", "Joe Strummer2" );
        URI secondNode = createNode();
        addProperty( secondNode, "band", "The Clash2" );
//        // END SNIPPET: nodesAndProps
//
//        // START SNIPPET: addRel
        URI relationshipUri = addRelationship( firstNode, secondNode, "singer",
                "{ \"from\" : \"1977\", \"until\" : \"1986\" }" );
//        // END SNIPPET: addRel
//
//        // START SNIPPET: addMetaToRel
        addMetadataToProperty( relationshipUri, "stars", "5" );
//        // END SNIPPET: addMetaToRel
//
//        // START SNIPPET: queryForSingers
        findSingersInBands( firstNode );
        // END SNIPPET: queryForSingers
        //Integer id=60;
        String query ="MATCH (a)-[r]->(b) return ID(a),a,ID(b) as relId, type(r);";
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        //Map<String, String> map = new HashMap<String, String>();
    	StringBuilder strBuilder=new StringBuilder("MATCH (n { id: ");
    	Iterator iterator = map.entrySet().iterator();
    	int count=0;
			strBuilder.append(" '"+map.get("id")+"'} SET ");
    	while (iterator.hasNext()) {
    		Map.Entry mapEntry = (Map.Entry) iterator.next();
    		if(mapEntry.getKey() != "id" ){
    			strBuilder.append(" n."+mapEntry.getKey()+" = '"+mapEntry.getValue()+"',");
    		}
    	}
    	strBuilder.setLength(strBuilder.length() - 1);
    	System.out.println("After append = " + strBuilder);
//        StringBuilder strBuilder=new StringBuilder();
//        for (Map.Entry<String, String> entry : map.entrySet())
//        {
//            System.out.println(entry.getKey() + "/" + entry.getValue());
//        }
//       String query="MATCH (n { name:"+"SW_IF-2"+" }) SET n.admin_state = '0' Return n";
//        String query ="START a=node(*) OPTIONAL MATCH (a)-[r]-(b) return ID(a),a,ID(b) as relId, type(r),r;";
//        String query=" MATCH (a {type:'switch'}) MATCH a-[r]-(b) return ID(a),a,ID(b) as relId, type(r),r";
//        List<String> types = new ArrayList<String>();
//        types.add("'vswitch'");
//        types.add("'vm'");
//        String query="MATCH (n) WHERE n.type IN "+types+" OPTIONAL MATCH n-[r]->(b) return ID(n),n,ID(b) as relId, type(r),r";
        //String query = "Match n where id(n)=230 return n";
       // getData(query);
        //sendTransactionalCypherQuery( "MATCH (n)  RETURN n" );
        //CreateSimpleGraph cs=new CreateSimpleGraph();
       // cs.test();
    }
    /**
     * This is the test test
     * 
     * **/
    public void test(){
    	try {
    		//    	InputStream in = this.getClass().getClassLoader()
    		//                .getResourceAsStream("SomeTextFile.txt");

    		InputStream inputStream = this.getClass().getClassLoader()
    				.getResourceAsStream("testing.txt");

    		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    		StringBuilder builder = new StringBuilder();
    		String line;
    		while ((line = bufferedReader.readLine()) != null)
    		{
    				System.out.println(line);
    				builder.append(line+" ");
    				//getData(line);
    		}
    		System.out.println(builder+"************");
    	} catch (IOException e) {
    		e.printStackTrace();
    	} 
    }
    /**
     * This is the test getdata
     * 
     * **/
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
				System.out.println(response.getEntity( String.class ));
	}
    /**
     * This is the test senedTransaction
     * 
     * **/
    private static void sendTransactionalCypherQuery(String query) {
        // START SNIPPET: queryAllNodes
        final String txUri = SERVER_ROOT_URI + "transaction/commit";
        WebResource resource = Client.create().resource( txUri );

        String payload = "{\"statements\" : [ {\"statement\" : \"" +query + "\"} ]}";
        ClientResponse response = resource
                .accept( MediaType.APPLICATION_JSON )
                .type( MediaType.APPLICATION_JSON )
                .entity( payload )
                .post( ClientResponse.class );
        
//        System.out.println( String.format(
//                "POST [%s] to [%s], status code [%d], returned data: "
//                        + System.getProperty( "line.separator" ) + "%s",
//                payload, txUri, response.getStatus(),
//                response.getEntity( String.class ) ) );
        System.out.println(response.getEntity( String.class ));
        response.close();
        // END SNIPPET: queryAllNodes
    }
    /**
     * This is the test find
     * 
     * **/
    private static void findSingersInBands( URI startNode )
            throws URISyntaxException
    {
        // START SNIPPET: traversalDesc
        // TraversalDefinition turns into JSON to send to the Server
        TraversalDefinition t = new TraversalDefinition();
        t.setOrder( TraversalDefinition.DEPTH_FIRST );
        t.setUniqueness( TraversalDefinition.NODE );
        t.setMaxDepth( 10 );
        t.setReturnFilter( TraversalDefinition.ALL );
        t.setRelationships( new Relation( "singer", Relation.OUT) );
        // END SNIPPET: traversalDesc

        // START SNIPPET: traverse
        URI traverserUri = new URI( startNode.toString() + "/traverse/node" );
        WebResource resource = Client.create()
                .resource( traverserUri );
        String jsonTraverserPayload = t.toJson();
        ClientResponse response = resource.accept( MediaType.APPLICATION_JSON )
                .type( MediaType.APPLICATION_JSON )
                .entity( jsonTraverserPayload )
                .post( ClientResponse.class );

//        System.out.println( String.format(
//                "POST [%s] to [%s], status code [%d], returned data: "
//                        + System.getProperty( "line.separator" ) + "%s",
//                jsonTraverserPayload, traverserUri, response.getStatus(),
//                response.getEntity( String.class ) ) );
        System.out.println("-----"+response.getEntity( String.class ));
        response.close();
        // END SNIPPET: traverse
    }

    // START SNIPPET: insideAddMetaToProp
    private static void addMetadataToProperty( URI relationshipUri,
            String name, String value ) throws URISyntaxException
    {
        URI propertyUri = new URI( relationshipUri.toString() + "/properties" );
        String entity = toJsonNameValuePairCollection( name, value );
        WebResource resource = Client.create()
                .resource( propertyUri );
        ClientResponse response = resource.accept( MediaType.APPLICATION_JSON )
                .type( MediaType.APPLICATION_JSON )
                .entity( entity )
                .put( ClientResponse.class );

//        System.out.println( String.format(
//                "PUT [%s] to [%s], status code [%d]", entity, propertyUri,
//                response.getStatus() ) );
        response.close();
    }

    // END SNIPPET: insideAddMetaToProp

    private static String toJsonNameValuePairCollection( String name,
            String value )
    {
        return String.format( "{ \"%s\" : \"%s\" }", name, value );
    }

    private static URI createNode()
    {
        // START SNIPPET: createNode
        final String nodeEntryPointUri = SERVER_ROOT_URI + "node";
        // http://localhost:7474/db/data/node

        WebResource resource = Client.create()
                .resource( nodeEntryPointUri );
        // POST {} to the node entry point URI
        ClientResponse response = resource.accept( MediaType.APPLICATION_JSON )
                .type( MediaType.APPLICATION_JSON )
                .entity( "{}" )
                .post( ClientResponse.class );

        final URI location = response.getLocation();
//        System.out.println( String.format(
//                "POST to [%s], status code [%d], location header [%s]",
//                nodeEntryPointUri, response.getStatus(), location.toString() ) );
        response.close();

        return location;
        // END SNIPPET: createNode
    }

    // START SNIPPET: insideAddRel
    private static URI addRelationship( URI startNode, URI endNode,
            String relationshipType, String jsonAttributes )
            throws URISyntaxException
    {
        URI fromUri = new URI( startNode.toString() + "/relationships" );
        String relationshipJson = generateJsonRelationship( endNode,
                relationshipType, jsonAttributes );

        WebResource resource = Client.create()
                .resource( fromUri );
        // POST JSON to the relationships URI
        ClientResponse response = resource.accept( MediaType.APPLICATION_JSON )
                .type( MediaType.APPLICATION_JSON )
                .entity( relationshipJson )
                .post( ClientResponse.class );

        final URI location = response.getLocation();
        System.out.println( String.format(
                "POST to [%s], status code [%d], location header [%s]",
                fromUri, response.getStatus(), location.toString() ) );

        response.close();
        return location;
    }
    // END SNIPPET: insideAddRel

    private static String generateJsonRelationship( URI endNode,
            String relationshipType, String... jsonAttributes )
    {
        StringBuilder sb = new StringBuilder();
        sb.append( "{ \"to\" : \"" );
        sb.append( endNode.toString() );
        sb.append( "\", " );

        sb.append( "\"type\" : \"" );
        sb.append( relationshipType );
        if ( jsonAttributes == null || jsonAttributes.length < 1 )
        {
            sb.append( "\"" );
        }
        else
        {
            sb.append( "\", \"data\" : " );
            for ( int i = 0; i < jsonAttributes.length; i++ )
            {
                sb.append( jsonAttributes[i] );
                if ( i < jsonAttributes.length - 1 )
                { // Miss off the final comma
                    sb.append( ", " );
                }
            }
        }

        sb.append( " }" );
        return sb.toString();
    }

    private static void addProperty( URI nodeUri, String propertyName,
            String propertyValue )
    {
        // START SNIPPET: addProp
        String propertyUri = nodeUri.toString() + "/properties/" + propertyName;
        // http://localhost:7474/db/data/node/{node_id}/properties/{property_name}

        WebResource resource = Client.create()
                .resource( propertyUri );
        ClientResponse response = resource.accept( MediaType.APPLICATION_JSON )
                .type( MediaType.APPLICATION_JSON )
                .entity( "\"" + propertyValue + "\"" )
                .put( ClientResponse.class );

        System.out.println( String.format( "PUT to [%s], status code [%d]",
                propertyUri, response.getStatus() ) );
        response.close();
        // END SNIPPET: addProp
    }

    private static void checkDatabaseIsRunning()
    {
        // START SNIPPET: checkServer
        WebResource resource = Client.create()
                .resource( SERVER_ROOT_URI );
        ClientResponse response = resource.get( ClientResponse.class );

        System.out.println( String.format( "GET on [%s], status code [%d]",
                SERVER_ROOT_URI, response.getStatus() ) );
        response.close();
        // END SNIPPET: checkServer
    }
}
