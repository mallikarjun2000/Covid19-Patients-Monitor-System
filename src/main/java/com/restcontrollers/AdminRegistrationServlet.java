package com.restcontrollers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.AdminDatabase;
import com.utils.BcryptUtil;

/**
 * Servlet implementation class AdminRegistrationServlet
 */
@WebServlet("/adminregistration")
public class AdminRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminRegistrationServlet() {
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
		String password = BcryptUtil.hashPassword(request.getParameter("password"));
		int phoneNumber = Integer.parseInt(request.getParameter("phone_number"));
		String email = request.getParameter("email");
		PrintWriter out = response.getWriter();
		AdminDatabase ad = new AdminDatabase();
		try {
			if(ad.insertRecord(username, password, email, phoneNumber))
			{
				out.println("Registered Successfully !");
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			}
			else
			{
				out.println("Failed to register try with a different username");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			out.print("Username already taken\n trace of error : "+e.getMessage());
			e.printStackTrace();
		}
	}

}
