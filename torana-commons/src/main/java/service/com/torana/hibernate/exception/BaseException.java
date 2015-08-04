package com.torana.hibernate.exception;
import java.util.Stack;

/**
 * Base Exception
 * @author sudamurali
 *
 */
public class BaseException extends Exception {

	private static final long serialVersionUID = 1L;
	private Stack messages = null;
	private Exception sourceException = null;
	private Object additionalData = null;
	public static String NEWLINE = System.getProperty("line.separator");
	
	public BaseException(String msg, Exception source) {
		this(msg);
		this.sourceException = source;
	}
	
	public Exception getSourceException() {
		return sourceException;
	}

	public void setSourceException(Exception sourceException) {
		this.sourceException = sourceException;
	}

	public BaseException(String msg) {
		super(msg);
		messages = new Stack();
		messages.push(msg);
		System.out.println("INSIDE****BaseException****"+messages);
	}
	
	/**
	 * Adds additional details to the stack of messages.
	 * As the application is being processed, whenever there is an exception
	 * we stack the exception messages, so the caller knows precisely what
	 * happened and where
	 * @param msg
	 */
	public BaseException addDetails(String msg) {
		this.messages.push(msg);
		return this;
	}
	
	public String toString() {
		if (this.messages != null) {
			return (String)this.messages.firstElement();
		} 
		return "";
	}
	
	public String getLastMessage() {
		if (this.messages != null) {
			return (String)this.messages.lastElement();
		} 
		return "";
	}
	
	/**
	 * Returns a detailed message.. with all the messages chained
	 * @return
	 */
	public String toDetailedString() {
		StringBuffer sb = new StringBuffer();
		for (int ii=0; ii < messages.size(); ii++) {
			String tab  = (ii==0 ? "" : "\t");
			sb.append(tab + messages.get(ii) + NEWLINE);			
		}
		return sb.toString();
	}

	
	/**
	 * Returns a detailed message.. with all the messages chained
	 * @return
	 */
	public String dbLogString() {
		StringBuffer sb = new StringBuffer();
		for (int ii=0; ii < messages.size(); ii++) {
			sb.append(messages.get(ii) + "  |  ");			
		}
		
		//
		String logStr = sb.toString();
		//if (logStr.length() > EDPConstants.MAX_DB_LOG_MESSAGE_LENGTH) {
		//	return logStr.substring(0, EDPConstants.MAX_DB_LOG_MESSAGE_LENGTH);
		//}
		return logStr;
	}

	public Object getAdditionalData() {
		return additionalData;
	}

	public void setAdditionalData(Object additionalData) {
		this.additionalData = additionalData;
	}

}
   