package com.model;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class User {
	private String username;
	private String password;
	
	public User( String firstName, String lastName,String username,
			String phoneNumber, String email) {
		super();
		this.username = username;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
	}
	
	public User(String username, String email, String createdDate, String firstName, String lastName,
			String phoneNumber) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.createdDate = createdDate;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
	}
	private String email;
	private String createdDate;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	
	public User() {
		
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
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
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getNumberOfDaysAdmitted()
	{
		//String timeStamp = new SimpleDateFormat("YYYY-MM-dd").format(new Date());
		//System.out.println(timeStamp);
		//String  todayTime = this.getCreatedDate();
		return 0;
	}
}
