package com.bookwriter.web;

import java.net.BindException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.web.servlet.ModelAndView;

import com.bookwriter.model.Part;
import com.bookwriter.persist.PartManager;
import com.bookwriter.security.User;

public class AddPartController extends org.springframework.web.servlet.mvc.SimpleFormController {
	
	PartManager partManager;

	public PartManager getPartManager() {
		return partManager;
	}

	public void setPartManager(PartManager partManager) {
		this.partManager = partManager;
	}

	/**
	 * Always returns a new Part object
	 * 
	 * @see Part
	 */
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		return new com.bookwriter.model.Part();
	}

	/** Validates user/password against database */
	public void onBindAndValidate(HttpServletRequest request, Object command,
			BindException errors) throws Exception {
		log.info(errors.getMessage());
	}
	
	public static Logger log = Logger.getLogger(AddPartController.class);
	/** returns ModelAndView(getSuccessView()) */
	@Override
	protected  ModelAndView onSubmit(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, java.lang.Object command, org.springframework.validation.BindException errors) throws java.lang.Exception
			{
		Object obj = SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		log.info(obj);
		if (obj instanceof UserDetails) {
			User user = ((com.bookwriter.security.User) obj);
			log.info(user.getUserId());
			Part part = (Part) command;
			log.error(part.getText());
			part.setBookId(1);
			part.setDatetime(new Date());
			part.setUserId(user.getUserId());
			partManager.savePart(part);
		}
		return new ModelAndView(getSuccessView());
	}

}
