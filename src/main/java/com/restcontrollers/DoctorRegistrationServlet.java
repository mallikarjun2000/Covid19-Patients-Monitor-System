package com.restcontrollers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DoctorDatabase;
import com.dao.UserDatabase;
import com.utils.BcryptUtil;

/**
 * Servlet implementation class DoctorRegistrationServlet
 */
public class DoctorRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoctorRegistrationServlet() {
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
		String HashedPassword = BcryptUtil.hashPassword(request.getParameter("password"));
		PrintWriter out = null;
		try {
			DoctorDatabase ud = new DoctorDatabase();
			String username = request.getParameter("username");
			String email = request.getParameter("email");
			String firstname = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
			String phone_number = (request.getParameter("phonenumber"));
			String timeStamp = new SimpleDateFormat("YYYY-MM-dd").format(new Date());
			System.out.println(timeStamp);
			out = response.getWriter();
			if(ud.insertRecord(username, HashedPassword, timeStamp, firstname, lastname, phone_number, email))
			{
				out.println("User registered Succesfully!");
			}
			else
			{
				out.println("Registration failed :( ");
				response.sendRedirect("adminhome");
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			out.print("Failed Registration due to : "+e.getMessage());
			try {
				Thread.sleep(10000);
				RequestDispatcher rd = request.getRequestDispatcher("userRegistration.html");
				rd.forward(request, response);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

}
