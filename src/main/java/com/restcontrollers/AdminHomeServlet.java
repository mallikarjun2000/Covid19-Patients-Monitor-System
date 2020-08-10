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

import com.dao.AdminDatabase;
import com.model.Alert;

/**
 * Servlet implementation class adminHome
 */
@WebServlet("/adminhome")
public class AdminHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminHomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		AdminDatabase ad = new AdminDatabase();
		//ArrayList<Alert> listOfAlerts = ad.getAlerts();
		HttpSession session = request.getSession();
		//session.setAttribute("listOfAlerts", listOfAlerts);
		String username = (String)session.getAttribute("username");
		PrintWriter out = response.getWriter();
		try {
			int numberOfPatientsAdmitted = ad.getNumberOfPatientsAdmitted();
			int numberOfPatinetsRecovered = ad.getNumberOfPatientsRecovered();
			request.setAttribute("numberOfPatientsAdmitted", numberOfPatientsAdmitted);
			request.setAttribute("numberOfPatinetsRecovered", numberOfPatinetsRecovered);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.print("admin home reached!");
		RequestDispatcher rd  = request.getRequestDispatcher("adminHome.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
