package com.restcontrollers;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

/**
 * Servlet Filter implementation class UserOrAdminFilter
 */
//@WebServlet("/useroradmin")
public class UserOrAdminFilter implements Filter {

    /**
     * Default constructor. 
     */
    public UserOrAdminFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String loginAs = request.getParameter("loginas");
		System.out.print("in FIlter");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if(loginAs.equals("doctor"))
		{
			System.out.println("Doctor detected");
			RequestDispatcher rd=request.getRequestDispatcher("doctorlogin");
			rd.forward(request, response);
		}
		if(loginAs == "patient")
		{
			System.out.print("Register as PArient");
			RequestDispatcher rd = request.getRequestDispatcher("userlogin");
			rd.forward(request, response);
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
