package com.torana.quartz.jobs;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
/**
 * @author Torana
 * */
public class HibernateUtils {

	private static final SessionFactory sessionFactory;

	static {
		System.out.println("static block!............");
		try {
			//sessionFactory = new Configuration().configure("com/mybkr/quartz/jobs/hibernate.cfg.xml").buildSessionFactory();
			sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void main(String[] args) {

	}
}
