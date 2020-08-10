package com.restcontrollers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.DoctorDatabase;
import com.utils.BcryptUtil;

/**
 * Servlet implementation class DoctorLoginServlet
 */
public class DoctorLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoctorLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("Doctor Login");
		DoctorDatabase dd = new DoctorDatabase();
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		try {
			if(BcryptUtil.checkPassword(password, dd.getPassword(username)))
			{
				HttpSession session = request.getSession();
				session.setAttribute("username", username);
				out.print("correct password");
				response.sendRedirect("doctorhome");
			}
			else
			{
				String file="userLogin.html";
				out.print("Username or Password Incorrect please check your password and type again"
						+"<br/><button><a href="+file+">Go Back</a></button>");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
