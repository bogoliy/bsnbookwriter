package ua.kiev.kpi.comsys.hp.webapp.filters;
import java.io.*;

import javax.jcr.Repository;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.support.RequestContext;

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
			//response.setCharacterEncoding(encoding);
			next.doFilter(request, response);

		} catch (ServletException e) {
			e.printStackTrace();
		}
	}

	public void destroy() {
	}

}