package com.bookwriter.rss;

import java.io.IOException;

import javax.servlet.http.HttpServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bookwriter.rss.RSSCreator;


public class RSSServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RSSCreator rssCreator;

	public static final Log logger = LogFactory.getLog(RSSServlet.class
			.getName());

	public void doGet(javax.servlet.http.HttpServletRequest req,
			javax.servlet.http.HttpServletResponse resp) {
		WebApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(this.getServletContext());
		for (Object obj: ctx.getBeanDefinitionNames()) {
			System.out.println(obj);
		}
		rssCreator = (RSSCreator) ctx.getBean("rssCreator");
		Document doc = rssCreator.getRSS();
		logger.info("Generating RSS");
		try {
			rssCreator.writeRSS(resp.getWriter(), doc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
