package com.torana.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

public class ToranaAuthenticationFilter implements Filter {
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(ToranaAuthenticationFilter.class);

	public void init(FilterConfig config) throws ServletException {

	}


	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try {
			HttpServletRequest httpRequest = ((HttpServletRequest) request);
			HttpServletResponse httpResponse = ((HttpServletResponse) response);

			httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
			httpResponse.setHeader("Access-Control-Allow-Origin", "*");
			httpResponse.setHeader("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept,PUT,GET,POST,OPTIONS");
			httpResponse.setHeader("Access-Control-Request-Headers", "x-requested-with, accept, origin,Content-Type,PUT,GET,POST,OPTIONS,DELETE");
			chain.doFilter(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void destroy() {

	}
}
