package com.restcontrollers;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.AdminDatabase;
import com.dao.UserDatabase;
import com.model.Alert;
import com.model.User;

/**
 * Servlet implementation class getReport
 */
@WebServlet("/getreport")
public class getReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getReport() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = new User();
		Alert alert = new Alert();
		try {
			UserDatabase ud = new UserDatabase();
			AdminDatabase ad = new AdminDatabase();
			String reportId = (String)(request.getParameter("reportId"));
			System.out.println("Report Id: "+reportId);
			alert = ad.getAlert(reportId);
			System.out.println(alert.getUsername());
			user = ud.getUser(alert.getUsername());
			request.setAttribute("user", user);
			request.setAttribute("alert", alert);
			RequestDispatcher rd = request.getRequestDispatcher("reportPage.jsp");
	//		response.sendRedirect("reportPage.jsp");
			rd.forward(request, response);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
