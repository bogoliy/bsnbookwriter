package com.bookwriter.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.bookwriter.persist.MessageManager;

public class VideoController implements Controller{
	private static final String VIDEO_TITLE = "video.title";
	private MessageManager messageManager;
	

	public MessageManager getMessageManager() {
		return messageManager;
	}


	public void setMessageManager(MessageManager messageManager) {
		this.messageManager = messageManager;
	}


	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		String message = messageManager.getMessage(VIDEO_TITLE).getText();
		return new ModelAndView("video", "mes", message);
	}
}
