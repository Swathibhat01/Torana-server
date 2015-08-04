/**
 * @author bhanuchander
 * @version 1.0
 * @since 16th July 2014
 * */
package com.torana.util.mail;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SendEmailRunner implements Runnable {
	
	private static final Logger logger = LoggerFactory.getLogger(SendEmailRunner.class);

	private Map<String, String> variables;
	private String template;
	private String subject;
	private String toEmailAddress;
	private String fromEmail;
	private boolean isHTML = false;

	public SendEmailRunner(Map<String, String> variables, String template,
			String subject, String toEmailAddress, String fromEmail, boolean isHTML) {
		this.variables = variables;
		this.template = template;
		this.subject = subject;
		this.toEmailAddress = toEmailAddress;
		this.fromEmail = fromEmail;
		this.isHTML = isHTML;
	}

	public void run() {
		try {
			if(isHTML){
				SimpleSMTPMailer.sendHTMLEmail(variables, template, subject, toEmailAddress, fromEmail);
			}else{
				SimpleSMTPMailer.sendTextEmail(variables, template, subject, toEmailAddress, fromEmail,false);
			}
			
		} catch (Exception e) {
			logger.info("Error sending email from SendEmailRunner: ",e);
		}
	}
}
