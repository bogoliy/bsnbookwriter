package com.bookwriter.web;

import javax.servlet.http.HttpServlet;

public class TestServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) 
	throws javax.servlet.ServletException, java.io.IOException {
		resp.setContentType("text/xml");
		resp.getWriter().print("<variants><variant>first proposal</variant><variant>forth proposal</variant><variant>second proposal</variant></variants>");
		resp.getWriter().flush();
	  }
}
