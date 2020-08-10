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
import javax.servlet.http.HttpSession;

import com.dao.UserDatabase;
import com.mysql.cj.Session;
import com.utils.BcryptUtil;
import com.utils.MailUtil;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet("/userlogin")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLoginServlet() {
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		try {
			UserDatabase ud = new UserDatabase();
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			if(BcryptUtil.checkPassword(password, ud.getUserPassword(username)))
			{
				HttpSession session = request.getSession();
				session.setAttribute("username", username);
				out.print("correct password");
				response.sendRedirect("userhome");
				MailUtil mailUtil = new MailUtil();
				mailUtil.sendMail("Hello this is a test mail","arjunmallik093@gmail.com");
				//RequestDispatcher rd = request.getRequestDispatcher("userhome");
		    	//rd.forward(request, response);
			}
			else
			{
				String file="userLogin.html";
				out.print("Username or Password Incorrect please check your password and type again"
						+"<br/><button><a href="+file+">Go Back</a></button>");
			}
		} catch(Exception e) {
			System.out.print("Exception Occured");
			e.printStackTrace();
		}
	}

}
