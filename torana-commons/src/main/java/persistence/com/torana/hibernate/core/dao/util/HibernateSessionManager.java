
package com.torana.hibernate.core.dao.util;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 
 * @author torana
 *	
 *	HibernateSessionManager is responsible of creating new session and colsing a session.
 *	This calss uses the ThreadLocal variable to store the session, ThreadLocal variable 
 *	ensures that for each thread there will be one session available, hence for each
 *	request there will be one session available. Since the session is for per request
 *	session is clossed in HibernateSessionCloseFilter class, which is called for each request.
 *
 */ 
public class HibernateSessionManager {

	//public	static Logger logger = LogManager.getLogger(HibernateSessionManager.class);
	private static final Logger logger = LoggerFactory.getLogger(HibernateSessionManager.class);
    public static final ThreadLocal session = new ThreadLocal();

    public static Session currentSession(SessionFactory sessionFactory) throws HibernateException {
    	
        Session s = (Session) session.get();
        // Open a new Session, if this Thread has none yet
        if (s == null )
        {
        	//logger.info("Get new session for current thread, thread name: "+Thread.currentThread().getName()+", thread id: "+Thread.currentThread().getId());
            s = sessionFactory.openSession();
            session.set(s);
            //logger.debug("Opened new session");
        }
        return s;
    }

    public static void closeSession() throws HibernateException {
        Session s = (Session) session.get();
        session.set(null);
        if (s != null){
        	//logger.info("Close current session for current thread, thread name: "+Thread.currentThread().getName()+",thread id: "+Thread.currentThread().getId());
            s.close();
        }
        //logger.debug("Closed session");
    }
}
