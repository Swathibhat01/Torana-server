package com.torana.hibernate.exception;

import com.torana.hibernate.exception.BaseException;

/**
 * Allication Exception
 * @author sudamurali
 *
 */
public class ApplicationException extends BaseException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ApplicationException(String message) {
		super(message);
		System.out.println("ApplicationException inside****");
	}
	
	

}
