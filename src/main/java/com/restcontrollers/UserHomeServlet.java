package com.restcontrollers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserDatabase;
import com.model.Alert;
import com.model.User;

/**
 * Servlet implementation class userHome
 */

@WebServlet("/userhome")
public class UserHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserHomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = new User();
		HttpSession session = request.getSession();
		try {
			
			UserDatabase ud = new UserDatabase();
			String username = (String)session.getAttribute("username");
			if(username == null)
				response.sendRedirect("userLogin.html");
			user =  ud.getUser(username);
			request.setAttribute("user", user);
			System.out.print("came to user : "+user.getFirstName());
			ArrayList<Alert> listOfHealthRecords = ud.getListOfHealthRecordsOfUser(username);
			request.setAttribute("listOfHealthRecords", listOfHealthRecords);
			
			//response.sendRedirect("userHome.jsp");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			PrintWriter out = response.getWriter();
			String userlogin = "userlogin";
			response.setContentType("text/html");
			out.print("Exception Occured"
					+ "click here to go back <a href="+userlogin+">Back</a> else reload page ");
			
			e.printStackTrace();
		} catch (SQLException e) {
			PrintWriter out = response.getWriter();
			String userlogin = "userlogin";
			response.setContentType("text/html");
			out.print("Exception Occured"
					+ "click here to go back <a href="+userlogin+">Back</a> else reload page ");
			e.printStackTrace();
		}
		catch( Exception e)
		{
			System.out.print("excception");
		}
		RequestDispatcher rd = request.getRequestDispatcher("userHome.jsp");
    	rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
