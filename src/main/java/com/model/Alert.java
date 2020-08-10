package com.model;

import java.util.Date;

public class Alert {
	private String username;
	private String reportId;
	private int weight;
	private int temperature;
	private int bloodPressure;
	private Date createdDate;
	
	public Alert() {
		
	}
	
	public Alert(String reportId, int weight, int temperature, int bloodPressure, Date createdDate) {
		super();
		this.reportId = reportId;
		this.weight = weight;
		this.temperature = temperature;
		this.bloodPressure = bloodPressure;
		this.createdDate = createdDate;
	}

	public Alert(String username, String reportId, int weight, int temperature, int bloodPressure, Date createdDate) {
		super();
		this.username = username;
		this.reportId = reportId;
		this.weight = weight;
		this.temperature = temperature;
		this.bloodPressure = bloodPressure;
		this.createdDate = createdDate;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getReportId() {
		return reportId;
	}


	public void setReportId(String reportId) {
		this.reportId = reportId;
	}


	public int getWeight() {
		return weight;
	}


	public void setWeight(int weight) {
		this.weight = weight;
	}


	public int getTemperature() {
		return temperature;
	}


	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}


	public int getBloodPressure() {
		return bloodPressure;
	}


	public void setBloodPressure(int bloodPressure) {
		this.bloodPressure = bloodPressure;
	}


	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
}
