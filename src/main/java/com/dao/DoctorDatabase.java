package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.model.User;

public class DoctorDatabase {
	private String DB_NAME = "covid19monitor";
	private final String URL = "jdbc:mysql://localhost:3306/"+DB_NAME;
	private Connection con;
	private String PASS = "Root@123";
	public DoctorDatabase() {
		
	}
	
	public boolean insertRecord(String username, String password, String created_date, String first_name, String last_name, String phone_number, String email ) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(URL,"root",PASS);
		//Class.forName("oracle.jdbc.driver.OracleDriver");  (username, password, email, first_name, last_name, phone_number, created_date)
		PreparedStatement stmt=con.prepareStatement("insert into doctors_data values (?,?,?,?,?,?,?)");
		stmt.setString(1, username);
		stmt.setString(2, password);
		stmt.setString(3, created_date);
		stmt.setString(4, first_name);
		stmt.setString(5, last_name);
		stmt.setNString(6, phone_number);
		stmt.setString(7, email);
		
		return stmt.execute();
	}
	public String getPassword(String username) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(URL,"root",PASS);
		//Class.forName("oracle.jdbc.driver.OracleDriver");  (username, password, email, first_name, last_name, phone_number, created_date)
		PreparedStatement stmt=con.prepareStatement("select password from doctors_data where username=?");
		stmt.setString(1, username);
		ResultSet rs =  stmt.executeQuery();
		String password=null;
		if(rs.next())
			password = rs.getString("password");
		return password;
	}
	
	public User getDoctor(String username) throws ClassNotFoundException, SQLException
	{
		User user = new User();
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(URL,"root",PASS);
		//Class.forName("oracle.jdbc.driver.OracleDriver");  (username, password, email, first_name, last_name, phone_number, created_date)
		PreparedStatement stmt=con.prepareStatement("select * from doctors_data where username=?");
		stmt.setString(1, username);
		ResultSet rs = stmt.executeQuery();
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
	
	public void findAndUpdateByUsername(String username, User user) throws ClassNotFoundException, SQLException
	{
		User newUser = new User();
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(URL,"root",PASS);
		PreparedStatement preparedStatement = con.prepareStatement("update doctors_data set first_name=?, last_name=?,"
				+ " phone_number=?, email=? where username=?");
		preparedStatement.setString(1, user.getFirstName());
		preparedStatement.setString(2, user.getLastName());
		preparedStatement.setString(3, user.getPhoneNumber());
		preparedStatement.setString(4, user.getEmail());
		preparedStatement.setNString(5, username);
		
		boolean rs = preparedStatement.execute();
	}
	
	public ArrayList<User> getPatients(String username) throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(URL,"root",PASS);
		//Class.forName("oracle.jdbc.driver.OracleDriver");  (username, password, email, first_name, last_name, phone_number, created_date)
		PreparedStatement stmt=con.prepareStatement("select * from users_data where doctor_name=?");
		stmt.setString(1, username);
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
	
	public ArrayList<User> getDoctors() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(URL,"root",PASS);
		//Class.forName("oracle.jdbc.driver.OracleDriver");  (username, password, email, first_name, last_name, phone_number, created_date)
		PreparedStatement stmt=con.prepareStatement("select * from doctors_data");
		//stmt.setString(1, username);
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
	
	public void deleteDoctor(String username) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(URL,"root",PASS);
		//Class.forName("oracle.jdbc.driver.OracleDriver");  (username, password, email, first_name, last_name, phone_number, created_date)
		PreparedStatement stmt=con.prepareStatement("delete from doctors_data where username=?");
		stmt.setString(1, username);
		stmt.execute();
	}
	
	public String getDoctorEmail(String username) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(URL,"root",PASS);
		//Class.forName("oracle.jdbc.driver.OracleDriver");  (username, password, email, first_name, last_name, phone_number, created_date)
		PreparedStatement stmt=con.prepareStatement("select email from doctors_data where username=?");
		stmt.setString(1, username);
		ResultSet rs = stmt.executeQuery();
		if(rs.next())
			return rs.getString("email");
		
		return null;
	}
}
