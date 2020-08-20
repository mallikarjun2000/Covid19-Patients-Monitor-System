package com.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.model.Alert;

public class AdminDatabase {
	private final String DB_NAME = "covid19monitor";
	private final String URL = "jdbc:mysql://localhost:3306/"+DB_NAME;
	private Connection con;
	private String PASS="Root@123";
	
	public AdminDatabase() {
		
	}
	
	public Boolean insertRecord(String username, String password, String email, long phone_number)
			throws SQLException, ClassNotFoundException
	{  
		System.out.println("reached database insert");
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(URL,"root",PASS);
		//Class.forName("oracle.jdbc.driver.OracleDriver");  (username, password, email, first_name, last_name, phone_number, created_date)
		PreparedStatement stmt=con.prepareStatement("insert into admins_data values (?,?,?,?)");
		stmt.setString(1, username);
		stmt.setString(2, email);
		stmt.setString(3, password);
		stmt.setLong(4, phone_number);

		return stmt.execute();
	}
	
	public String getAdminPassword(String username) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(URL,"root",PASS);
		PreparedStatement stmt = con.prepareStatement("select password from admins_data where username=?");
		stmt.setString(1, username);
		ResultSet rs = stmt.executeQuery();
		if(rs.next())
			return rs.getString("password");
		return null;
	}
	
	public ArrayList<Alert> getAlerts(String doctor) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(URL,"root",PASS);
		PreparedStatement stmt = con.prepareStatement("select  t1.username, t1.temperature, t1.report_id, t1.weight, t1.date, t1.blood_pressure from users_health t1 INNER JOIN users_data t2 ON t1.username = t2.username and t1.alert=? and t2.doctor_name=?");
		stmt.setBoolean(1, true);
		stmt.setString(2, doctor);
		ResultSet rs = stmt.executeQuery();
		ArrayList<Alert> listOfAlerts = new ArrayList<Alert>();
		while(rs.next())
		{
			listOfAlerts.add(new Alert(rs.getString("username"),
					rs.getString("report_id"),
					rs.getInt("weight"), rs.getInt("temperature"),rs.getInt("blood_pressure")
					,rs.getDate("date")));
		}
		return listOfAlerts;
	}
	
	public Alert getAlert(String reportId) throws ClassNotFoundException, SQLException
	{
		Alert alert = new Alert();
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(URL,"root",PASS);
		PreparedStatement stmt = con.prepareStatement("select * from users_health where report_id = ?");
		stmt.setString(1, reportId);
		ResultSet rs = stmt.executeQuery();
		if(rs.next())
		{
			alert.setUsername(rs.getString("username"));
			alert.setBloodPressure(rs.getInt("blood_pressure"));
			alert.setTemperature(rs.getInt("temperature"));
			alert.setWeight(rs.getInt("weight"));
			alert.setCreatedDate(rs.getDate("date"));
		}
		
		
		return alert;
	}
	
	public int getNumberOfPatientsAdmitted() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(URL,"root",PASS);
		Boolean stat = false;
		PreparedStatement stmt = con.prepareStatement("select count(*) from user_status where status=false");
		ResultSet rs = stmt.executeQuery();
		if(rs.next())
			return rs.getInt(1);
		
		return 0;
	}
	
	public int getNumberOfPatientsRecovered() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(URL,"root",PASS);
		Boolean stat = false;
		PreparedStatement stmt = con.prepareStatement("select count(*) from user_status where status=true");
		ResultSet rs = stmt.executeQuery();
		if(rs.next())
			return rs.getInt(1);
		
		return 0;
	}
	
	public void recoverUser(String username) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		UserDatabase ud = new UserDatabase();
		con = DriverManager.getConnection(URL,"root",PASS);
		PreparedStatement stmt = con.prepareStatement("update user_status set status=? where username=?");
		stmt.setBoolean(1, !ud.getUserStatus(username));
		stmt.setString(2, username);
		
		stmt.execute();
		
	}
}
