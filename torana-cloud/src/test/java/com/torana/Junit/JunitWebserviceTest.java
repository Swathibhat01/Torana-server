package com.torana.Junit;

import static org.junit.Assert.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import com.torana.Junit.PropertiesCache;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.JSONTokener;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JunitWebserviceTest extends HttpClient{
	
	HttpClient httpClient = new HttpClient();
	JSONObject cloudElement = new JSONObject();
	JSONParser parser=new JSONParser();
	StringBuffer response = new StringBuffer();
	BufferedReader in=null;
	String result=null,inputLine;
	String cloudElementUrl = "" , cloudElementID_forUpdate; 
	long start, elapsedTime;
	URL obj;
	HttpURLConnection con;
	JSONObject response_JSON;
	static Integer insertedCloudId;
	
	public void setInsertCloudId(Integer CloudId){
		insertedCloudId = CloudId;
	}
	
	public Integer getInsertedCloudId(){
		return insertedCloudId;
	}
	
	//Creates JSON format of cloud element which can be passed in post & put webservices testing
	public JSONObject cloudElementCreate(){
		try {
			cloudElement.put("userName", "testnew_9april");
			cloudElement.put("password", "654322654322");
			cloudElement.put("type", "demo-server");
			cloudElement.put("name", "demo-server");
			cloudElement.put("ipAddress", "12.12.12.12");
			cloudElement.put("active", "1");
			cloudElement.put("createdDttm", " ");
			cloudElement.put("modifiedDttm", " ");
			cloudElement.put("timeInterval", "2");
			cloudElement.put("discover", "1");
			cloudElement.put("events", "1");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return cloudElement;
	}

		@Test
	public void atestSaveCloudElement(){
		try{
		cloudElementUrl = PropertiesCache.getInstance().getProperty("POST_CLOUD_ELEMENT_URL");
		System.out.println("REQUEST POST URL:"+cloudElementUrl);
		PostMethod post = new PostMethod(cloudElementUrl);
		cloudElement = cloudElementCreate();
		post.setRequestEntity(new StringRequestEntity(cloudElement.toString(),"application/json", "utf-8"));
		System.out.println("Post cloudelement testing:::"+cloudElement.toString());
		long start = System.currentTimeMillis();
		httpClient.executeMethod(post);
		long elapsedTime = System.currentTimeMillis() - start;
		System.out.println("\nMethod execution time: " + elapsedTime + " milliseconds.");
		result = post.getResponseBodyAsString();
		Object obj = parser.parse(result);
		org.codehaus.jettison.json.JSONTokener x = new org.codehaus.jettison.json.JSONTokener(result);

		response_JSON = new JSONObject(x);
        System.out.println("CloudID:::"+response_JSON.get("cloudId"));
        setInsertCloudId((Integer)response_JSON.get("cloudId"));
		if(result.isEmpty()){
			assertTrue(false);
			System.out.println("testSaveCloudElement Method result is empty -- Set Junit Testcase as FALSE");
		}
		else if(result.contains("12.12.12.12"))
		{
			assertTrue(true);
			System.out.println("testSaveCloudElement Method response contains the inserted value");
		}
		else{
			System.out.println("testSaveCloudElement Method response is not empty and not contains the inserted value -- Set Junit Testcase as FALSE");
			assertTrue(false);
		}
		
		}
		catch(Exception e){
			System.out.println("There's an exception in testSaveCloudElement method:  "+e);
			assertTrue(false);
		}
		
	}
		
		@Test
		public void btestUpdateCloudElement(){
			try{
			cloudElementUrl = PropertiesCache.getInstance().getProperty("PUT_CLOUD_ELEMENT_URL");
			cloudElementID_forUpdate = PropertiesCache.getInstance().getProperty("CLOUD_ELEMENTID_UPDATE");
			System.out.println("REQUEST PUT URL to update cloud element :  "+cloudElementUrl);
			PutMethod post = new PutMethod(cloudElementUrl);
			//to get JSON format of cloud element to update
			cloudElement = cloudElement.put("cloudId", getInsertedCloudId());
			cloudElement = cloudElementCreate();
			post.setRequestEntity(new StringRequestEntity(cloudElement.toString(),"application/json", "utf-8"));
			start = System.currentTimeMillis();
			httpClient.executeMethod(post);
			elapsedTime = System.currentTimeMillis() - start;
			System.out.println("testUpdateCloudElement Method execution time : " + elapsedTime + " milliseconds.");
			result = post.getResponseBodyAsString();
			if(result.isEmpty()){
				System.out.println("testUpdateCloudElement Method result is empty -- Set Junit Testcase as FALSE");
				assertTrue(false);
			}
			else if(result.contains("12.12.12.12"))
			{
				System.out.println("testUpdateCloudElement Method response contains the updated value");
				assertTrue(true);
			}
			else{
				System.out.println("testUpdateCloudElement Method response not contains the updated value -- Set Junit Testcase as FALSE");
				assertTrue(false);
			}
			}
			catch(Exception e){
				System.out.println("There's an exception in testUpdateCloudElement method:  "+e);
				assertTrue(false);
			}

		}
		

	
	@Test
	public void ctestGetAllCloudElements(){
		try{
		final String USER_AGENT = "Mozilla/5.0";
		cloudElementUrl = PropertiesCache.getInstance().getProperty("GET_ALL_CLOUD_ELEMENT_URL");
		System.out.println("REQUEST GET URL in testGetAllCloudElements Method : "+cloudElementUrl);
		obj = new URL(cloudElementUrl);
		con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		if(responseCode != 200){
			System.out.println("testGetAllCloudElements methos response code is not 200 -- Set Junit Testcase as FALSE");
			assertTrue(false);
		}
		in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		result = response.toString();
		System.out.println("GetAllCloudElements RESULT:::"+result);
		if(result == null){
			assertTrue(true);
			System.out.println("Get All Cloud Elements response code is 200 & the result is null");
		}else{
			assertTrue(true);
			System.out.println("Get All Cloud Elements response code is 200 & the result is not null");
		}
		}
		catch(Exception e){
			System.out.println("There's an exception in testGetAllCloudElements method:  "+e);
			assertTrue(false);
		}
		
	}

	@Test
		public void dtestGetCloudElementById(){
			try{
			final String USER_AGENT = "Mozilla/5.0";
			cloudElementUrl = PropertiesCache.getInstance().getProperty("GET_CLOUD_ELEMENT_URL");
			System.out.println("REQUEST GET URL in dtestGetCloudElementById Method : "+cloudElementUrl);
			obj = new URL(cloudElementUrl+"?cloudId="+getInsertedCloudId());
			con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			//add request header
			con.setRequestProperty("User-Agent", USER_AGENT);

			int responseCode = con.getResponseCode();
			if(responseCode != 200){
				System.out.println("testGetCloudElementById methos response code is not 200 -- Set Junit Testcase as FALSE");
				assertTrue(false);
			}
			in = new BufferedReader(
					new InputStreamReader(con.getInputStream()));
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			result = response.toString();
			System.out.println("final res:::"+result);
			if(result == null){
				assertTrue(false);
				System.out.println("Get All Cloud Elements response code is 200 & the result is null-- -- Set Junit Testcase as FALSE");
			}else{
				assertTrue(true);
				System.out.println("Get All Cloud Elements response code is 200 & the result is not null");
			}
			}
			catch(Exception e){
				System.out.println("There's an exception in testGetCloudElementById method:  "+e);
				assertTrue(false);
			}
			
		}
	
		@Test
				public void etestDeleteCloudElementById(){
					try{
					final String USER_AGENT = "Mozilla/5.0";
					cloudElementUrl = PropertiesCache.getInstance().getProperty("DELETE_CLOUD_ELEMENT_URL");
					System.out.println("REQUEST GET URL in testDeleteCloudElementById Method : "+cloudElementUrl);
					
					DeleteMethod delete = new DeleteMethod(cloudElementUrl+"?cloudId="+getInsertedCloudId());
					long start = System.currentTimeMillis();
					httpClient.executeMethod(delete);
					long elapsedTime = System.currentTimeMillis() - start;
					System.out.println("\nMethod execution time: " + elapsedTime + " milliseconds.");
					result = delete.getResponseBodyAsString();
					
					
					
					if(result == null){
						assertTrue(false);
						System.out.println("Get All Cloud Elements response code is 200 & the result is null-- -- Set Junit Testcase as FALSE");
					}else{
						assertTrue(true);
						System.out.println("Get All Cloud Elements response code is 200 & the result is not null");
					}
					}
					catch(Exception e){
						System.out.println("There's an exception in testDeleteCloudElementById method:  "+e);
						assertTrue(false);
					}
					
				}
		
		@Test
		public void testGetAllClouds(){
			try{
			final String USER_AGENT = "Mozilla/5.0";
			cloudElementUrl = PropertiesCache.getInstance().getProperty("GET_ALL_CLOUDS");
			System.out.println("REQUEST GET URL in testGetAllClouds Method : "+cloudElementUrl);
			obj = new URL(cloudElementUrl);
			con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			//add request header
			con.setRequestProperty("User-Agent", USER_AGENT);

			int responseCode = con.getResponseCode();
			if(responseCode != 200){
				System.out.println("testGetAllClouds method response code is not 200 -- Set Junit Testcase as FALSE");
				assertTrue(false);
			}
			in = new BufferedReader(
					new InputStreamReader(con.getInputStream()));
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			result = response.toString();
			System.out.println("testGetAllClouds RESULT:::"+result);
			if(result == null){
				assertTrue(true);
				System.out.println("testGetAllClouds response code is 200 & the result is null");
			}else{
				assertTrue(true);
				System.out.println("testGetAllClouds response code is 200 & the result is not null");
			}
			}
			catch(Exception e){
				System.out.println("There's an exception in testGetAllClouds method:  "+e);
				assertTrue(false);
			}
			
		}
		
		@Test
		public void testGetCloudById(){
			try{
			final String USER_AGENT = "Mozilla/5.0";
			cloudElementUrl = PropertiesCache.getInstance().getProperty("GET_CLOUD_BY_ID");
			System.out.println("REQUEST GET URL in testGetCloudById Method : "+cloudElementUrl);
			obj = new URL(cloudElementUrl+"?cloudId=1");
			con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			//add request header
			con.setRequestProperty("User-Agent", USER_AGENT);

			int responseCode = con.getResponseCode();
			if(responseCode != 200){
				System.out.println("testGetCloudById method response code is not 200 -- Set Junit Testcase as FALSE");
				assertTrue(false);
			}
			in = new BufferedReader(
					new InputStreamReader(con.getInputStream()));
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			result = response.toString();
			System.out.println("testGetCloudById RESULT:::"+result);
			if(result == null){
				assertTrue(true);
				System.out.println("testGetCloudById response code is 200 & the result is null");
			}else{
				assertTrue(true);
				System.out.println("testGetCloudById response code is 200 & the result is not null");
			}
			}
			catch(Exception e){
				System.out.println("There's an exception in testGetCloudById method:  "+e);
				assertTrue(false);
			}
			
		}
		
		@Test
		public void testGetAllCloudsByList(){
			try{
			final String USER_AGENT = "Mozilla/5.0";
			cloudElementUrl = PropertiesCache.getInstance().getProperty("GET_ALL_CLOUDS_BY_LIST");
			System.out.println("REQUEST GET URL in testGetAllCloudsByList Method : "+cloudElementUrl);
			obj = new URL(cloudElementUrl+"?types='switch'");
			con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			//add request header
			con.setRequestProperty("User-Agent", USER_AGENT);

			int responseCode = con.getResponseCode();
			if(responseCode != 200){
				System.out.println("testGetAllCloudsByList method response code is not 200 -- Set Junit Testcase as FALSE");
				assertTrue(false);
			}
			in = new BufferedReader(
					new InputStreamReader(con.getInputStream()));
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			result = response.toString();
			System.out.println("testGetAllCloudsByList RESULT:::"+result);
			if(result == null){
				assertTrue(true);
				System.out.println("testGetAllCloudsByList response code is 200 & the result is null");
			}else{
				assertTrue(true);
				System.out.println("testGetAllCloudsByList response code is 200 & the result is not null");
			}
			}
			catch(Exception e){
				System.out.println("There's an exception in testGetAllCloudsByList method:  "+e);
				assertTrue(false);
			}
			
		}
		
		
		@Test
		public void testGetAllCloudsByType(){
			try{
			final String USER_AGENT = "Mozilla/5.0";
			cloudElementUrl = PropertiesCache.getInstance().getProperty("GET_ALL_CLOUDS_BY_TYPE");
			System.out.println("REQUEST GET URL in testGetAllCloudsByType Method : "+cloudElementUrl);
			obj = new URL(cloudElementUrl+"?typelabel=type&type=switch");
			con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			//add request header
			con.setRequestProperty("User-Agent", USER_AGENT);

			int responseCode = con.getResponseCode();
			if(responseCode != 200){
				System.out.println("testGetAllCloudsByType method response code is not 200 -- Set Junit Testcase as FALSE");
				assertTrue(false);
			}
			in = new BufferedReader(
					new InputStreamReader(con.getInputStream()));
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			result = response.toString();
			System.out.println("testGetAllCloudsByType RESULT:::"+result);
			if(result == null){
				assertTrue(true);
				System.out.println("testGetAllCloudsByType response code is 200 & the result is null");
			}else{
				assertTrue(true);
				System.out.println("testGetAllCloudsByType response code is 200 & the result is not null");
			}
			}
			catch(Exception e){
				System.out.println("There's an exception in testGetAllCloudsByType method:  "+e);
				assertTrue(false);
			}
			
		}



				
				
				}
