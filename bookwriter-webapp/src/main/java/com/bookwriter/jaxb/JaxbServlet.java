package com.bookwriter.jaxb;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bookwriter.jaxb.parts.PartsType;
import com.bookwriter.jaxb.transform.PartsTransform;
import com.bookwriter.model.Part;
import com.bookwriter.persist.PartManager;

/**
 * This class is created just to test jaxb
 * 
 * @author root
 */
public class JaxbServlet extends HttpServlet {
	private static final String PACKAGE_NAME = "com.bookwriter.jaxb.parts";
	private JAXBContext jc;
	PartManager partManager;
	
	public PartManager getPartManager() {
		return partManager;
	}

	public void setPartManager(PartManager partManager) {
		this.partManager = partManager;
	}
	
	Logger log = Logger.getLogger(JaxbServlet.class);
	public void init(ServletConfig config) throws ServletException {
		log.debug("Init: partManager  = " + partManager);
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
		partManager = (PartManager) context.getBean("partManager");
		log.debug("init: partManager = " + partManager);
	}

	public void doGet(javax.servlet.http.HttpServletRequest req,
			javax.servlet.http.HttpServletResponse resp)
			throws javax.servlet.ServletException, java.io.IOException {
		log.debug("doGet");
		List<Part> parts = partManager.getLatestParts();
		try {
			marshal(PartsTransform.transform(parts), resp.getWriter());
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void marshal(PartsType parts, PrintWriter printWriter)
			throws Exception {
		jc = JAXBContext.newInstance(PACKAGE_NAME);
		Marshaller m = jc.createMarshaller();
		m.marshal(parts, printWriter);
	}

}
