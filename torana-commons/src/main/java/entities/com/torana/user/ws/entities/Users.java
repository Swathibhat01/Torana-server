package com.torana.user.ws.entities;

// Generated Jul 7, 2014 4:15:31 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AuthUsers generated by hbm2java
 */
public class Users implements java.io.Serializable {

	private Integer authUserId;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private Byte active;
	private Date createdDttm;
	private Date modifiedDttm;
	
	

	public Users() {
	}

	public Users(Integer authUserId, String userName, String password,
			String firstName, String lastName, String emailAddress,
			Byte active, Date createdDttm, Date modifiedDttm) {
		super();
		this.authUserId = authUserId;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.active = active;
		this.createdDttm = createdDttm;
		this.modifiedDttm = modifiedDttm;
	}

	public Integer getAuthUserId() {
		return authUserId;
	}

	public void setAuthUserId(Integer authUserId) {
		this.authUserId = authUserId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Byte getActive() {
		return active;
	}

	public void setActive(Byte active) {
		this.active = active;
	}

	public Date getCreatedDttm() {
		return createdDttm;
	}

	public void setCreatedDttm(Date createdDttm) {
		this.createdDttm = createdDttm;
	}

	public Date getModifiedDttm() {
		return modifiedDttm;
	}

	public void setModifiedDttm(Date modifiedDttm) {
		this.modifiedDttm = modifiedDttm;
	}
	
	
	
	
}