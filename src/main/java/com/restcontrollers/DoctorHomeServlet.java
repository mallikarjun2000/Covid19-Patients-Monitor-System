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
import javax.servlet.http.HttpSession;

import com.dao.AdminDatabase;
import com.dao.DoctorDatabase;
import com.dao.UserDatabase;
import com.model.Alert;
import com.model.User;
import com.mysql.cj.Session;

/**
 * Servlet implementation class DoctorHomeServlet
 */
public class DoctorHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoctorHomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User doctor = new User();
		PrintWriter out = response.getWriter();
		try {
			
			DoctorDatabase dd = new DoctorDatabase();
			AdminDatabase ad = new AdminDatabase();
			HttpSession session = request.getSession();
			
			UserDatabase ud = new UserDatabase();
			doctor = dd.getDoctor((String)session.getAttribute("username"));
			ArrayList<Alert> listOfPatientsUnderRisk = new ArrayList<Alert>();
			listOfPatientsUnderRisk = ad.getAlerts((String)session.getAttribute("username"));
			request.setAttribute("doctor", doctor);
			request.setAttribute("listOfPatientsUnderRisk", listOfPatientsUnderRisk);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println("doctor home reached!");
		RequestDispatcher rd = request.getRequestDispatcher("doctorHomePage.jsp");
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
