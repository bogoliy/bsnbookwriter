package com.bookwriter.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.bookwriter.model.Part;
import com.bookwriter.persist.PartManager;

public class SearchController implements Controller{
	private PartManager partManager;

	public PartManager getPartManager() {
		return partManager;
	}

	public void setPartManager(PartManager partManager) {
		this.partManager = partManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		String searchKeywords = request.getParameter("str");
		System.out.println("Search " + searchKeywords);
		List<Part> parts = partManager.searchParts(searchKeywords);
		//Here must be retrieving of data from db
		return new ModelAndView("search", "parts", parts);
	}
}
