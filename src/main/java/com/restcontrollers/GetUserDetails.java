package com.restcontrollers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DoctorDatabase;
import com.dao.UserDatabase;
import com.model.Alert;
import com.model.User;

/**
 * Servlet implementation class GetUserDetails
 */
public class GetUserDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUserDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		User user = new User();
		ArrayList<Alert> listOfHealthRecordsOfUser = new ArrayList<Alert>();
		UserDatabase ud;
		try {
			ud = new UserDatabase();
			user = ud.getUser(username);
			listOfHealthRecordsOfUser = ud.getListOfHealthRecordsOfUser(username);
			request.setAttribute("user", user);
			//System.out.print(user.getUsername());
			if(user.getUsername() == null)
			{
				DoctorDatabase dd = new DoctorDatabase();
				user = dd.getDoctor(username);
				request.setAttribute("user", user);
				request.setAttribute("isDoctor", false);
			}
			else {
				request.setAttribute("isDoctor", true);
				Boolean status = ud.getUserStatus(username);
				request.setAttribute("status", status);
			}
			
			request.setAttribute("listOfHealthRecordsOfUser", listOfHealthRecordsOfUser);
			if(user != null)
			{
				RequestDispatcher rd = request.getRequestDispatcher("userPage.jsp");
				rd.forward(request, response);
			}
			else
			{
				PrintWriter out = response.getWriter();
				response.setContentType("text/html");
				out.print("No user found type correct username </br> "
						+ "<a onclick=\"history.back()\" class="+"\"btn btn-primary bg-dark\" "+">Go Back</a>");
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
