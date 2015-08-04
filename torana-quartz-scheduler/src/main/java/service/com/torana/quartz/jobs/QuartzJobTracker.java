package com.torana.quartz.jobs;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
/**
 * @author Torana
 * */
public class QuartzJobTracker extends Thread{

	private SessionFactory sessionFactory = null;

	private Session session = null;

	public QuartzJobTracker() {

		System.out.println("--- QuartzJobTracker Object Created ---");
		sessionFactory = HibernateUtils.getSessionFactory();
		
		if(sessionFactory == null){
			System.out.println("Creating the session factory");
			sessionFactory = HibernateUtils.getSessionFactory();
		}
		//session = sessionFactory.openSession();
	}

	public void processOpenStackCalls(String group) {
		OpenStackJobsMain jobsMain = new OpenStackJobsMain(sessionFactory);
		
		if(group.equalsIgnoreCase("torana")){
			System.out.println("********** Job Started ************" + new Date());
			jobsMain.openStackToNeo4jData();
			System.out.println("********** Job Ended ************"+ new Date());
		}else{
			System.out.println("********** Job Started ************");
			jobsMain.getOrderStatus();
		}
	}

	/**
	 * Testing Purpose
	 * */
	public static void main(String[] args) {
		QuartzJobTracker jobTracker = new QuartzJobTracker();
		jobTracker.processOpenStackCalls("torana");
	}

}
