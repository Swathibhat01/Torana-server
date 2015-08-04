/**
 * @author bhanuchander
 * @version 1.0
 * @since 16th July 2014
 * */
package com.torana.util.mail;

import java.util.List;

import javax.mail.internet.MimeBodyPart;

public class MailDataBean {
	private String subject;
	private String body;
	private String to;
	private String from;
	private String cc;
	private String bcc;
	private boolean isHtml=false;
	private List<MimeBodyPart> attachmentList = null;
	public List<MimeBodyPart> getAttachmentList() {
        return attachmentList;
    }
    public void setAttachmentList(List<MimeBodyPart> attachmentList) {
        this.attachmentList = attachmentList;
    }
    public String getBcc() {
		return bcc;
	}
	public void setBcc(String bcc) {
		this.bcc = bcc;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public boolean isHtml() {
		return isHtml;
	}
	public void setHtml(boolean isHtml) {
		this.isHtml = isHtml;
	}
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("to:");
		sb.append(to);
		sb.append("\nfrom:");
		sb.append(from);
		sb.append("\nsubject:");
		sb.append(subject);
		sb.append("\nis HTML:");
		sb.append(isHtml);
		sb.append("\nbody:");
		sb.append(body);
		
		return sb.toString();
	}

}