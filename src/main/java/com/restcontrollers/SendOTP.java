package com.restcontrollers;

import java.io.IOException;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.AdminDatabase;
import com.dao.DoctorDatabase;
import com.dao.UserDatabase;
import com.utils.MailUtil;
import com.utils.SideUtils;

/**
 * Servlet implementation class SendOTP
 */
public class SendOTP extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendOTP() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String loginAs = request.getParameter("loginas");
		String username = request.getParameter("username");
		System.out.println("tried to login as "+ loginAs+" with username "+username);
		DoctorDatabase dd = new DoctorDatabase();
		HttpSession session = request.getSession();
		session.setAttribute("username", username);
		session.setAttribute("loginas", loginAs);
		try {
			UserDatabase ud = new UserDatabase();
			if(loginAs.equals("doctor"))
			{
				String email = dd.getDoctorEmail(username);
				SideUtils sideUtils = new SideUtils();
				String OTP = sideUtils.generateOTP();
				MailUtil mailUtil = new MailUtil();
				mailUtil.sendMail("The OTP for is "+OTP, email);
				session.setAttribute("otp",OTP);
				request.setAttribute("email", email);
				System.out.print("Request "+" has been sent to "+ email);
			}
			if(loginAs.equals("patient"))
			{
					String email = ud.getUserEmail(username);
					SideUtils sideUtils = new SideUtils();
					String OTP = sideUtils.generateOTP();
					MailUtil mailUtil = new MailUtil();
					mailUtil.sendMail("The OTP for is "+OTP, email);
					session.setAttribute("otp",OTP);
					request.setAttribute("email", email);
					System.out.print("Request "+" has been sent to "+ email);
			}
	
			RequestDispatcher rd = request.getRequestDispatcher("OTP.jsp");
			rd.forward(request, response);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
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
