package com.bookwriter.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.bookwriter.model.Part;
import com.bookwriter.persist.MessageManager;
import com.bookwriter.persist.PartManager;

public class HomeController implements Controller{
	private static final String TITLE_HOME = "title.home";
	private MessageManager messageManager;
	

	public MessageManager getMessageManager() {
		return messageManager;
	}


	public void setMessageManager(MessageManager messageManager) {
		this.messageManager = messageManager;
	}


	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		String message = messageManager.getMessage(TITLE_HOME).getText();
		return new ModelAndView("home", "mes", message);
	}
}
