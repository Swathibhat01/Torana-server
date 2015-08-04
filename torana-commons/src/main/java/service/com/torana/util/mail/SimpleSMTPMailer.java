/**
 * @author bhanuchander
 * @version 1.0
 * @since 16th July 2014
 * */
package com.torana.util.mail;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.torana.util.constants.EmailConstants;
import com.torana.util.constants.Utils;


public class SimpleSMTPMailer{

	private static final Logger logger = LoggerFactory.getLogger(SimpleSMTPMailer.class);	
	private static Properties properties;

	private static javax.mail.Authenticator passwordAuthenticator = new javax.mail.Authenticator() {
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(EmailConstants.MAIL_USER_NAME,EmailConstants.MAIL_PASSWORD);
		}
	};

	public static Properties getProperties(){
		if(properties!=null){
			return properties;
		}else{

			properties = new Properties();
			InputStream is = null;

			try{
				//URL url = ClassLoader.getSystemResource("messages.properties");
				//is = url.openStream();
				is = SimpleSMTPMailer.class.getClassLoader().getResourceAsStream("mail.properties");	
				properties.load(is);			
			}catch(Exception e){
				logger.error("Error loading properties file", e);
			}finally{
				try{
					if(is!=null){
						is.close();
					}
				}catch(Exception ex){
					logger.error("Error closing stream while loading properties file", ex);
				}
			}

			return properties;   
		}
	}


	public static void sendEmail(MailDataBean bean) throws Exception{

		Properties properties = getProperties();

		Session session = Session.getDefaultInstance(properties,passwordAuthenticator);

		try {
			Message message = new MimeMessage(session);

			if(bean.getTo()==null){
				logger.error("Please Specify Atleast one to Address");
				return;
			}
			else{
				if(bean.getFrom().length() > 0){
					message.setFrom(new InternetAddress((bean.getFrom())));
				}
				if(bean.getTo().indexOf(",") > 0){
					message.addRecipients(Message.RecipientType.TO,  InternetAddress.parse(bean.getTo()));
				}
				if(bean.getBcc() != null && bean.getBcc().indexOf(",") > 0){
					message.addRecipients(Message.RecipientType.BCC,  InternetAddress.parse(bean.getBcc()));
				}
				else{
					message.setRecipient(Message.RecipientType.TO, new InternetAddress(bean.getTo()));
					if(bean.getBcc() != null && bean.getBcc().length() > 0 ){
						message.setRecipient(Message.RecipientType.BCC, new InternetAddress(bean.getBcc()));
					}
				}
			}
			message.setSubject(bean.getSubject());

			// Set the email message text.
			//
			Multipart multipart = new MimeMultipart();

			if(bean.isHtml()){
				BodyPart templateContentBP = new javax.mail.internet.MimeBodyPart();
				templateContentBP.setContent(bean.getBody(),"text/html");
				multipart.addBodyPart(templateContentBP);
			}else{
				MimeBodyPart messagePart = new MimeBodyPart();
				messagePart.setContent(bean.getBody(), "text/html; charset=ISO-8859-1");
				multipart.addBodyPart(messagePart);
			}

			if (bean.getAttachmentList() != null) {
				message.setContent(multipart);
				for (int iCount = 0; iCount < bean.getAttachmentList().size(); iCount++) {
					multipart.addBodyPart(bean.getAttachmentList().get(iCount));
				}
			}

			message.setContent(multipart);
			message.setSentDate(new Date());

			Transport.send(message);
			System.out.println("Mail sent successfully");
			logger.info("Mail sent successfully");
		} catch (MessagingException e) {
			logger.error("Error Sending Mail ", e);
			throw e;
		}
	}


	public static void sendHTMLEmailAsyc(Map<String, String> variables, String template, String subject, String toEmailAddress, String fromEmail) {
		Thread thread = new Thread(new SendEmailRunner(variables, template, subject, toEmailAddress, fromEmail, true));
		thread.start();
	}

	public static void sendHTMLEmail(Map<String, String> variables, String template, String subject, String toEmailAddress, String fromEmailId) throws Exception{
		System.out.println(template);
		String message = "";
		try {
			InputStream fis = SimpleSMTPMailer.class.getResourceAsStream("/" + template);
			message = Utils.convertStreamToString(fis);      

			Matcher m;
			Iterator<String> keyItr = variables.keySet().iterator();
			String key;
			while (keyItr.hasNext()) {
				key = keyItr.next();
				m = Pattern.compile(key).matcher(message);
				
				String value = "";
				if(variables.get(key) != null)
					value = Matcher.quoteReplacement(variables.get(key));
				
				message = m.replaceAll(value);
			}
			MailDataBean bean = new MailDataBean();
			bean.setTo(toEmailAddress);
			bean.setFrom(fromEmailId);
			bean.setSubject(subject);
			bean.setBody(message); 
			bean.setHtml(true);

			SimpleSMTPMailer.sendEmail(bean);
		} catch (Exception e) {
			logger.error("Error Sending HTML Email ", e);
			throw e;
		}
	}

	public static void sendTextEmail(Map<String, String> variables, String message, String subject, String toEmailAddress, String fromEmailId, boolean isHtml) throws Exception{
		try {
			logger.info("sendTextEmail method called");
			Matcher m;
			Iterator<String> keyItr = variables.keySet().iterator();
			String key;
			while (keyItr.hasNext()) {
				key = keyItr.next();
				m = Pattern.compile(key).matcher(message);
				
				String value = "";
				
				if(variables.get(key) != null)
					value = Matcher.quoteReplacement(variables.get(key));
				
				message = m.replaceAll(value);
			}

			MailDataBean bean = new MailDataBean();
			bean.setTo(toEmailAddress);
			bean.setFrom(fromEmailId);
			bean.setSubject(subject);
			bean.setBody(message); 
			bean.setHtml(isHtml);
			
			//System.out.println("message :: "+message);
			

			SimpleSMTPMailer.sendEmail(bean);
		} catch (Exception e) {
			logger.error("Error Sending Text Email ", e);
			throw e;
		}
	}
	
	public static void sendTextEmailWithBcc(Map<String, String> variables, String message, String subject, String toEmailAddress, String bccEmailAddress, String fromEmailId, boolean isHtml) throws Exception{
		try {
			logger.info("sendTextEmail method called");
			Matcher m;
			Iterator<String> keyItr = variables.keySet().iterator();
			String key;
			while (keyItr.hasNext()) {
				key = keyItr.next();
				m = Pattern.compile(key).matcher(message);
				
				String value = "";
				if(variables.get(key) != null)
					value = Matcher.quoteReplacement(variables.get(key));
				
				message = m.replaceAll(value);
			}

			MailDataBean bean = new MailDataBean();
			bean.setTo(toEmailAddress);
			bean.setBcc(bccEmailAddress);
			bean.setFrom(fromEmailId);
			bean.setSubject(subject);
			bean.setBody(message); 
			bean.setHtml(isHtml);

			SimpleSMTPMailer.sendEmail(bean);
		} catch (Exception e) {
			logger.error("Error Sending Text Email ", e);
			throw e;
		}
	}

	public static void main(String args[]){

		MailDataBean bean = new MailDataBean();
		bean.setTo("bhanuchander33@enlume.com");
		bean.setFrom("bhanuchander@enlume.com");
		bean.setSubject("test Email");
		bean.setBody("test Email"); 
		bean.setHtml(false);
		try {
			//SimpleSMTPMailer.sendEmail(bean);


			/*String entireFileText = new Scanner(new File("resources/emailMesssageBody/changePassword.txt").getAbsolutePath()).useDelimiter("\\A").next();
	        	System.out.println(entireFileText);

	        	StringBuffer fileData = new StringBuffer(1000);
	        	BufferedReader reader = new BufferedReader(new FileReader(entireFileText));
	        	char[] buf = new char[1024];
	        	int numRead=0;

	        	while((numRead=reader.read(buf)) != -1){
	        		String readData = String.valueOf(buf, 0, numRead);
	        		fileData.append(readData);
	        		buf = new char[1024];
	        	}
	        	reader.close();*/
			String filePath = new File("").getAbsolutePath();
			//String registrationMailPath="\\src\\main\\resources\\emailMesssageBody\\registrationMail.html";
			String registrationMailPath="/src/main/resources/emailMesssageBody/registrationMail.html";
			filePath=filePath+registrationMailPath;
			String headerImageParem="\\src\\main\\resources\\images\\email-quatation-banner.png";
			headerImageParem=filePath+headerImageParem;
			System.out.println("sasas::: "+filePath);
			FileReader in = new FileReader(new File(filePath));
			//FileReader in = new FileReader(new File("G:\\java 22 nov workspace\\edvie-wsv3\\edvie-utils\\src\\main\\resources\\emailMesssageBody\\changePassword.html"));
			StringBuilder contents = new StringBuilder();
			char[] buffer = new char[4096];
			int read = 0;
			do {
				contents.append(buffer, 0, read);
				read = in.read(buffer);
			} while (read >= 0);
			String mailMessage=contents.toString();

			Map map = new HashMap<String, String>();
			map.put("firstNameParam", "test");
			map.put("userNameParam", "test");
			map.put("refNumberParam", "testREF");
			map.put("emailIdParam", "test@gmail.com");
			map.put("mobileNumberParam", "1234567890");
			map.put("classNameParam", "Standard 6");
			map.put("headerImageParam", headerImageParem);
			SimpleSMTPMailer.sendTextEmail(map, mailMessage, "Subject", "test@gmail.com", "test@toranasystems.com", true);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
