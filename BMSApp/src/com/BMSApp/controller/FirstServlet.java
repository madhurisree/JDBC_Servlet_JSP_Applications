package com.BMSApp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet("/test1")
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//if differtent project to communicate with RD then should get RD from context object
		//it is called ForeignrequestDispatcher
		//By default FprteignRequestDispatcher is not available in eclipse
		//so you should update in context.xml file in tomact as well as app like <Context crossContext="true">..</Context>
		ServletContext context = getServletContext();
		ServletContext fc = context.getContext("/PhonePayApp");
		RequestDispatcher foreignReuestdispatcher = fc.getRequestDispatcher("/test2"); //if we use like this search in same project
		foreignReuestdispatcher.forward(request, response);

	}

}
