package com.restcontrollers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserDatabase;
import com.utils.BcryptUtil;

/**
 * Servlet implementation class UserRegistrationServlet
 */
@WebServlet("/userregistration")
public class UserRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String HashedPassword = BcryptUtil.hashPassword(request.getParameter("password"));
		PrintWriter out = null;
		try {
			UserDatabase ud = new UserDatabase();
			String username = request.getParameter("username");
			String email = request.getParameter("email");
			String firstname = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
			String phone_number = (request.getParameter("phonenumber"));
			String timeStamp = new SimpleDateFormat("YYYY-MM-dd").format(new Date());
			HttpSession session = request.getSession();
			String doctorname = (String)session.getAttribute("username");
			System.out.println(timeStamp);
			out = response.getWriter();
			if(ud.insertRecord(username, HashedPassword, email, firstname, lastname, phone_number, timeStamp,doctorname))
			{
				out.println("User registered Succesfully!");
			}
			else
			{
				out.println("Registration failed :( ");
				response.sendRedirect("doctorhome");
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
