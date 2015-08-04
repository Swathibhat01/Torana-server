package com.torana.quartz.jobs;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ToranaCronJobs {
			public static void dumpOpenStackToNeo4jCron(){
				System.out.println("we are in ToranaCronJobs class dumpOpenStackToNeo4jCron method");
				new ClassPathXmlApplicationContext("/dumpOpenStackToNeo4jJobs.xml");
			}
}
