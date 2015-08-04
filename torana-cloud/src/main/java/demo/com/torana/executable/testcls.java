package com.torana.executable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient;
import org.openstack4j.api.compute.ext.HypervisorService;
import org.openstack4j.api.networking.NetworkService;
import org.openstack4j.api.networking.RouterService;
import org.openstack4j.api.storage.BlockVolumeService;
import org.openstack4j.model.compute.Server;
import org.openstack4j.openstack.OSFactory;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
public class testcls{
	private static final String SERVER_ROOT_URI = "http://localhost:7474/db/data/";
	public static void main(String[] args) {
		getRouters();
	}
	public static void getNetworks(){

		Builders b = new Builders();
		String s="",s1="";
		StringBuilder builder = new StringBuilder();
		boolean inQuotes = false;
		char currentChar_req_close = ' ';
		ArrayList a = new ArrayList();
		List<String> result = new ArrayList();
		System.out.println("\nSizetest::::"+result.size());
		Map m = new HashMap<String, Map>();
		
		OSClient os = OSFactory.builder().endpoint("http://8.21.28.222:5000/v2.0")
				.credentials("facebook100000823628974","pyGb1620umskhzrU")
				.tenantName("facebook100000823628974")
				.authenticate();
		
		// Find all running Servers
		NetworkService networks = os.networking().network();
		System.out.println("networks:\n"+networks.list());
		//start
		
	}
	public static void getVolumes(){
		Builders b = new Builders();
		String s="",s1="";
		StringBuilder builder = new StringBuilder();
		boolean inQuotes = false;
		char currentChar_req_close = ' ';
		ArrayList a = new ArrayList();
		List<String> result = new ArrayList();
		System.out.println("\nSizetest::::"+result.size());
		Map m = new HashMap<String, Map>();
		
		OSClient os = OSFactory.builder().endpoint("http://8.21.28.222:5000/v2.0")
				.credentials("facebook100000823628974","pyGb1620umskhzrU")
				.tenantName("facebook100000823628974")
				.authenticate();
		
		// Find all running Servers
		//HypervisorService networks = os.compute().hypervisors();
		BlockVolumeService networks = os.blockStorage().volumes();
		System.out.println("Volumes:\n"+networks.list());
		//start
		
	}
	public static void getRouters(){
		Builders b = new Builders();
		String s="",s1="";
		StringBuilder builder = new StringBuilder();
		boolean inQuotes = false;
		char currentChar_req_close = ' ';
		ArrayList a = new ArrayList();
		List<String> result = new ArrayList();
		System.out.println("\nSizetest::::"+result.size());
		Map m = new HashMap<String, Map>();
		
		OSClient os = OSFactory.builder().endpoint("http://8.21.28.222:5000/v2.0")
				.credentials("facebook100000823628974","pyGb1620umskhzrU")
				.tenantName("facebook100000823628974")
				.authenticate();
		
		// Find all running Servers
		RouterService networks = os.networking().router();
		System.out.println("Routers:\n"+networks.list());
		//start
		
	}
	public static void getServers(){

		Builders b = new Builders();
		String s="",s1="";
		StringBuilder builder = new StringBuilder();
		boolean inQuotes = false;
		char currentChar_req_close = ' ';
		ArrayList a = new ArrayList();
		List<String> result = new ArrayList();
		System.out.println("\nSizetest::::"+result.size());
		Map m = new HashMap<String, Map>();
		
		OSClient os = OSFactory.builder().endpoint("http://8.21.28.222:5000/v2.0")
				.credentials("facebook100000823628974","pyGb1620umskhzrU")
				.tenantName("facebook100000823628974")
				.authenticate();
		
		// Find all running Servers
		List<? extends Server> servers = os.compute().servers().list();
		System.out.println("Servers:\n"+servers);
		
		if(servers.size() > 0)
		{
		s = servers.get(0).toString();
		s1 = s.substring(s.indexOf('{')+1, s.length()-1);
		System.out.println("\nNew String:::"+builder);
		}
		
		//start
		
		
		   builder = new StringBuilder(s1);
		   //Start For Loop
		   for (int currentIndex = 0; currentIndex < builder.length(); currentIndex++) {
		       char currentChar = builder.charAt(currentIndex);
		       if (currentChar == '{') 
		       {
		    	   a.add(currentChar);
		    	   //System.out.println("currentChar:::"+a);
		    	   inQuotes = true; // toggle state
		       }
		       else if(currentChar == '}')
		       {
		    	   if(a.size() > 0)
		    	   a.remove(0);
		    	   if(a.size() == 0)
		    	   inQuotes = false; // toggle state
		       }
		      /*if(inQuotes){
		    	System.out.println("inQuotes is ttttttttrue:::"+inQuotes);  
		      }else
		      {
		    	  System.out.println("inQuotes is fffffffffffalse:::"+inQuotes);
		      }*/
		       if (currentChar == ',' && inQuotes) {
		    	   builder.setCharAt(currentIndex, ';'); 
			       }
		   }
		   //End For Loop
		   result = Arrays.asList(builder.toString().split(","));
		   
		   /*for (Object temp : result) {
			   System.out.println("\ntemp::::"+temp);
		   }*/
		   
		//end
		if(result.size() > 1){
		for (Object temp : result) {
			String temp1 = temp.toString();
			temp1 = temp1.replaceFirst("=", "==");
			System.out.println("\nTemp1:::"+temp1);
			List<String> al = new ArrayList<String>(Arrays.asList(temp1.split("==")));
			System.out.println("\nchecking::::::::"+al+"\tlength::::"+al.size());
			String sa = al.get(1);
			System.out.println("sa:::::::::"+sa);
			if(sa.contains(";"))
			{System.out.println("sa:::::::::ttttttttttttttttttttt");
				Map m1 = new HashMap();
				String value = al.get(0);
				if(value.equals("id")){
					value = "ServerID";
				}
				m.put(value.replace(" ", ""), m1.put(al.get(0), al.get(1)));
				System.out.println("m1::::::::"+m1);
				System.out.println("m::::::::"+m);
			}
			else
			{
				String value = al.get(0);
				if(value.equals("id")){
					value = "ServerID";
				}
				m.put(value.replace(" ", ""), al.get(1));
			}
		}}
			System.out.println("\nm:::::::::"+m);
			System.out.println("\nm:::::::::"+m.entrySet());
		Iterator iterator = m.entrySet().iterator();
		if(m.size()>0){
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
		}
	
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