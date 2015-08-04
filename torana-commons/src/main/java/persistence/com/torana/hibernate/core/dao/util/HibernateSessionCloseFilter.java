package com.torana.hibernate.core.dao.util;


/**
 * HibernateSessionCloseFilter to close the hibernate session after every request.
 * 
 * @author torana
 * 
 */

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class HibernateSessionCloseFilter implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		chain.doFilter(req, res);
		HibernateSessionManager.closeSession();
	}

	public void init(FilterConfig config) throws ServletException {

	}

}
