package com.torana.util;

import java.io.IOException;
import java.util.Properties;

public class OrderProperties {

	private static Properties orderProperties = null;

	public static String getPropertyFromOrders(String property){
		//System.out.println("one--"+properties);
		if (orderProperties == null) {
			//System.out.println("two-"+properties);
			loadPropertiesFromOrders();
			if (orderProperties == null) {
				return null;
			}
		}

		return orderProperties.getProperty(property);
	}

	private static void loadPropertiesFromOrders() {
		//System.out.println("three--"+properties);
		try {
			orderProperties = new Properties();
			orderProperties.load(OrderProperties.class.getClassLoader().getResourceAsStream("OrderProperties.properties"));

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
		}
	}
	

	public static void main(String[] args) {
		System.out.println(getPropertyFromOrders("cities") +"****");


	}
}
