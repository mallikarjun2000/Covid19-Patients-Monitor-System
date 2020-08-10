package com.restcontrollers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class VerifiyOTPServlet
 */
public class VerifiyOTPServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerifiyOTPServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String otp = (String)session.getAttribute("otp");
		String enteredValue = request.getParameter("otp");
		System.out.println("\nlogged to be "+session.getAttribute("loginas")+"\n"+
		"Entered : "+enteredValue+" existing: "+otp);
		request.setAttribute("username", session.getAttribute("username"));
		//session.removeAttribute("username");
		RequestDispatcher rd = null;
		if(otp.equals(enteredValue)) {
			String loginAs = (String) session.getAttribute("loginas");
			if(loginAs.equals("doctor"))
			{
				System.out.println("Doctor detected");
				rd = request.getRequestDispatcher("verifiedPage.jsp");
				rd.forward(request, response);
			}
			else
			{
				if(loginAs.equals("patient"))
				{
					System.out.print("Patient detected");
					rd = request.getRequestDispatcher("verifiedPage.jsp");
					rd.forward(request, response);
				}
			}
			
		}
		else
		{
			PrintWriter out = response.getWriter();
			String userlogin = "userlogin";
			response.setContentType("text/html");
			out.print("Exception Occured"
					+ "click here to go back <button class=\" btn btn-primary\" onclick=\"history.back()\">Back</button> else reload page ");
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
