package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.model.Alert;
import com.model.User;
import com.utils.SideUtils;

public class UserDatabase {
	//Class.forName("com.mysql.jdbc.Driver");
	/*
	 * static { try { //Class.forName("oracle.jdbc.driver.OracleDriver"); } catch
	 * (ClassNotFoundException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } }
	 */
	
	private final String DB_NAME = "covid19monitor";
	private final String URL = "jdbc:mysql://localhost:3306/"+DB_NAME;
	private Connection con;
	private String PASS="Root@123";
	
	public UserDatabase() throws SQLException, ClassNotFoundException{
		
	}
	
	public ArrayList<User> getUsers() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(URL,"root",PASS);
		//Class.forName("oracle.jdbc.driver.OracleDriver");  (username, password, email, first_name, last_name, phone_number, created_date)
		PreparedStatement stmt=con.prepareStatement("select * from users_data");
		ResultSet result = stmt.executeQuery();
		ArrayList<User> listOfUsers = new ArrayList<User>();
		while(result.next())
		{
			User user = new User(result.getString("username"), result.getString("email"),
					result.getString("created_date"), result.getString("first_name"),
					result.getString("last_name"), result.getString("phone_number"));
			listOfUsers.add(user);
		}
		return listOfUsers;
	}
	
	public Boolean insertRecord(String username, String password, String email, String firstname, String lastname, String phone_number, String
			date, String doctorname) throws SQLException, ClassNotFoundException
	{  
		System.out.println("reached database insert");
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(URL,"root",PASS);
		//Class.forName("oracle.jdbc.driver.OracleDriver");  (username, password, email, first_name, last_name, phone_number, created_date)
		PreparedStatement stmt=con.prepareStatement("insert into users_data values (?,?,?,?,?,?,?,?)");
		stmt.setString(1, username);
		stmt.setString(2, email);
		stmt.setString(3, password);
		stmt.setString(4, phone_number);
		stmt.setString(5, date);
		stmt.setString(6, firstname);
		stmt.setString(7, lastname);
		stmt.setNString(8, doctorname);
		
		PreparedStatement stmt2 = con.prepareStatement("insert into user_status values (?, false)");
		stmt2.setString(1, username);
		
		stmt2.execute();
		
		return stmt.execute();
	}
	
	public Boolean getRecord(String username) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(URL,"root",PASS);
		PreparedStatement stmt = con.prepareStatement("select * from users_data where username=?");
		stmt.setString(1, username);
		ResultSet re = stmt.executeQuery();
		User user = new User();
		if(re.next())
		{
			user.setUsername(re.getString("username"));
			user.setEmail(re.getString("email"));
			user.setFirstName(re.getString("first_name"));
			user.setLastName(re.getString("last_name"));
			user.setPhoneNumber(re.getString("phone_number"));
		}
		return null;
	}
	
	public String getUserPassword(String username) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(URL,"root",PASS);
		PreparedStatement stmt = con.prepareStatement("select password from users_data where username=?");
		stmt.setString(1, username);
		ResultSet rs = stmt.executeQuery();
		if(rs.next())
			return rs.getString("password");
		return null;
	}
	
	public Boolean insertHealthRecord(String username, String date, Float weight, int temperature, int bloodPressure) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(URL,"root",PASS);
		PreparedStatement stmt = con.prepareStatement("insert into users_health values (?, ?, ?, ?, ?, ?, ? )");
		String reportId=SideUtils.getAlphaNumericString();
		stmt.setString(1,username);
		stmt.setString(2, date);
		stmt.setFloat(3, weight);
		stmt.setInt(4, temperature);
		stmt.setInt(5, bloodPressure);
		stmt.setString(6,reportId);
		
		if((temperature > 37 || bloodPressure > 150))
		{
			stmt.setBoolean(7, true);
		}
		else
		{
			stmt.setBoolean(7, false);
		}
		
		return stmt.execute();
	}
	
	public User getUser(String username) throws ClassNotFoundException, SQLException {
		User user = new User();
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(URL,"root",PASS);
		PreparedStatement preparedStatement = con.prepareStatement("select * from users_data where username = ?");
		preparedStatement.setString(1, username);
		ResultSet rs = preparedStatement.executeQuery();
		if(rs.next())
		{
			user.setUsername(username);
			user.setEmail(rs.getString("email"));
			user.setFirstName(rs.getString("first_name"));
			user.setPhoneNumber(rs.getString("phone_number"));
			user.setLastName(rs.getString("last_name"));
		}
		return user;
	}
	
	
	
	public ArrayList<Alert> getListOfHealthRecordsOfUser(String username) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(URL,"root",PASS);
		PreparedStatement preparedStatement = con.prepareStatement("select * from users_health where username = ?");
		preparedStatement.setString(1, username);
		ResultSet rs = preparedStatement.executeQuery();
		ArrayList<Alert> listOfHealthRecords = new ArrayList<Alert>();
		while(rs.next())
		{
			listOfHealthRecords.add(new Alert(rs.getString("report_id"),
					rs.getInt("weight"),
					rs.getInt("temperature"),
					rs.getInt("blood_pressure"),
					rs.getDate("date")));
		}
		
		return listOfHealthRecords;
	}
	
	public void findAndUpdateUserByUsername(String username, User user) throws ClassNotFoundException, SQLException
	{
		User newUser = new User();
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(URL,"root",PASS);
		PreparedStatement preparedStatement = con.prepareStatement("update users_data set first_name=?, last_name=?,"
				+ " phone_number=?, email=? where username=?");
		preparedStatement.setString(1, user.getFirstName());
		preparedStatement.setString(2, user.getLastName());
		preparedStatement.setString(3, user.getPhoneNumber());
		preparedStatement.setString(4, user.getEmail());
		preparedStatement.setNString(5, username);
		
		boolean rs = preparedStatement.execute();
	}
	
	public ArrayList<User> getListOfUsers() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(URL,"root",PASS);
		PreparedStatement preparedStatement = con.prepareStatement("select * from users_data");
		ResultSet rs = preparedStatement.executeQuery();
		ArrayList<User> listOfUseres = new ArrayList<User>();
		while(rs.next())
		{
			listOfUseres.add(new User(rs.getString("first_name"), rs.getString("last_name"),
					rs.getString("username"), rs.getString("phone_number"),rs.getString("email")));
		}
		return listOfUseres;
	}
	
	public void deleteUser(String username) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(URL,"root",PASS);
		//Class.forName("oracle.jdbc.driver.OracleDriver");  (username, password, email, first_name, last_name, phone_number, created_date)
		PreparedStatement stmt=con.prepareStatement("delete from users_data where username=?");
		stmt.setString(1, username);
		
		deleteUserStatus(username);
		
		stmt.execute();
	}
	
	public void deleteUserStatus(String username) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(URL,"root",PASS);
		//Class.forName("oracle.jdbc.driver.OracleDriver");  (username, password, email, first_name, last_name, phone_number, created_date)
		PreparedStatement stmt=con.prepareStatement("delete from user_status where username=?");
		stmt.setString(1, username);
		stmt.execute();
	}
	
	public Boolean getUserStatus(String username) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(URL,"root",PASS);
		//Class.forName("oracle.jdbc.driver.OracleDriver");  (username, password, email, first_name, last_name, phone_number, created_date)
		PreparedStatement stmt=con.prepareStatement("select status from user_status where username=?");
		stmt.setString(1, username);
		ResultSet rs = stmt.executeQuery();
		if(rs.next())
			return rs.getBoolean("status");
		
		return false;
	}
	
	public String getUserEmail(String username) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(URL,"root",PASS);
		//Class.forName("oracle.jdbc.driver.OracleDriver");  (username, password, email, first_name, last_name, phone_number, created_date)
		PreparedStatement stmt=con.prepareStatement("select email from users_data where username=?");
		stmt.setString(1, username);
		ResultSet rs = stmt.executeQuery();
		if(rs.next())
			return rs.getString("email");
		
		return null;
	}
}
