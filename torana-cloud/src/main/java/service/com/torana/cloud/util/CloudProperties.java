package com.torana.cloud.util;

import java.io.IOException;
import java.util.Properties;

import com.torana.generic.dao.impl.ToranaGenericDAOImpl;

public class CloudProperties {

	private static Properties properties = null;

	public static String getProperty (String property){
		System.out.println("one--");
		if (properties == null) {
			System.out.println("two-");
			loadProperties();
			if (properties == null) {
				return null;
			}
		}

		return properties.getProperty(property);
	}

	private static void loadProperties() {
		System.out.println("three--");
		try {
			properties = new Properties();
			properties.load(ToranaGenericDAOImpl.class.getClassLoader().getResourceAsStream("generic.properties"));

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
		}
	}
	
	public Boolean loadPropWS() {
		try {
			System.out.println("INSIDE loadPropWS---");
		
			properties = new Properties();
			properties.load(ToranaGenericDAOImpl.class.getClassLoader().getResourceAsStream("generic.properties"));
			System.out.println("INSIDE loadPropWS--Properties Loaded Succesfully --");
			return true;
		} catch (IOException io) {
			io.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(getProperty("cities") +"****");


	}
}
