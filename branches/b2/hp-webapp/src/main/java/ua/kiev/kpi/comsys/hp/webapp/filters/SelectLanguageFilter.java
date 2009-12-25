package ua.kiev.kpi.comsys.hp.webapp.filters;

import java.io.*;

import javax.jcr.Repository;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;



public class SelectLanguageFilter implements Filter {
	private String encoding;

	public void init(FilterConfig config) throws ServletException {
		encoding = config.getInitParameter("requestEncoding");

		if (encoding == null)
			encoding = "utf-8";
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain next) throws IOException, ServletException {
		try {
			System.out.println("filter");
			request.setCharacterEncoding(encoding);
			// response.setCharacterEncoding(encoding);
			next.doFilter(request, response);
			InitialContext context;
			try {
				context = new InitialContext();
				Context environment = (Context) context.lookup("java:comp/env");
				System.out.println("JNDI----" + environment
						.lookup("jcr/repository"));
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (ServletException e) {
			e.printStackTrace();
		}
	}

	public void destroy() {
	}
}