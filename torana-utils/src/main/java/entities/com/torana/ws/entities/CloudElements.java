package com.torana.ws.entities;

import java.util.Date;

public class CloudElements {
	private Integer cloudId;
	private String userName;
	private String password;
	private String type;
	private String name;
	private String ipAddress;
	private Byte active;
	private Date createdDttm;
	private Date modifiedDttm;
	private Integer timeInterval;
	private Integer discover;
	private Integer events;
	
	public CloudElements() {
	}
	
	public CloudElements(Integer cloudId, String userName, String password,
			String type, String name, String ipAddress,
			Byte active, Date createdDttm, Date modifiedDttm,Integer timeInterval,Integer discover,Integer events) {
		super();
		this.cloudId = cloudId;
		this.userName = userName;
		this.password = password;
		this.type = type;
		this.name = name;
		this.ipAddress = ipAddress;
		this.active = active;
		this.createdDttm = createdDttm;
		this.modifiedDttm = modifiedDttm;
		this.timeInterval = timeInterval;
		this.discover = discover;
		this.events = events;
	}
	public Integer getCloudId() {
		return cloudId;
	}
	public void setCloudId(Integer cloudId) {
		this.cloudId = cloudId;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
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
	public Integer getTimeInterval() {
		return timeInterval;
	}
	public void setTimeInterval(Integer timeInterval) {
		this.timeInterval = timeInterval;
	}

	public Integer getDiscover() {
		return discover;
	}

	public void setDiscover(Integer discover) {
		this.discover = discover;
	}

	public Integer getEvents() {
		return events;
	}

	public void setEvents(Integer events) {
		this.events = events;
	}
	
}

