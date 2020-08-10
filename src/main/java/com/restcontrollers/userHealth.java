package com.restcontrollers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserDatabase;

/**
 * Servlet implementation class userHealth
 */
@WebServlet("/usershealth")
public class userHealth extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userHealth() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath() + " : "+request.getParameter("username"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		response.setContentType("text/html");
		//out.print(" from "+session.getAttribute("username"));
		String file="userhome";
		out.print("please check your values and type again ( ;_; )"
				+"<br/><button><a class="+" \"btn btn-primary\" "+"href="+file+">Go Back</a></button> </br>");
		String timeStamp = new SimpleDateFormat("YYYY-MM-dd").format(new Date());
		String username = ""+session.getAttribute("username");
		Float weight = Float.valueOf(request.getParameter("weight"));
		Integer temperature = Integer.parseInt(request.getParameter("temperature"));
		Integer bloodPressure = Integer.parseInt(request.getParameter("bloodpressure"));
		try {
			UserDatabase ud = new UserDatabase();
			if(weight < 0 || bloodPressure < 0 || temperature < 0)
			{
				out.print("please check your values and type again"
						+"<br/><button><a class="+" \"btn btn-primary\" "+"href="+file+">Go Back</a></button>");
			}
			else
			if(weight < 0 || bloodPressure < 50 || temperature < 20)
			{
				out.print("values are not in correct range"
						+"<br/><button><a class="+" \"btn btn-primary\" "+"href="+file+">Go Back</a></button>");
			}
			else
			if(ud.insertHealthRecord(username, timeStamp, weight, temperature, bloodPressure))
			{
				out.print("Deatils not sent successfully!");
			}
			else
			{
				out.print("Details sent Succesfully!");
				response.sendRedirect("userhome");
				//rd.forward(request, response);
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
