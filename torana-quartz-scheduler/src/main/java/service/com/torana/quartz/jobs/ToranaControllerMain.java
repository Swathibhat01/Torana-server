package com.torana.quartz.jobs;

import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * @author Torana
 * */
public class ToranaControllerMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("we are in ToranaControllerMain class main method");
		//new ClassPathXmlApplicationContext("/ToranaJobs.xml");
		ToranaCronJobs.dumpOpenStackToNeo4jCron();
	}
}
