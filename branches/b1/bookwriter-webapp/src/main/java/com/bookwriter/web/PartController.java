package com.bookwriter.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.bookwriter.model.Part;
import com.bookwriter.persist.PartManager;

public class PartController implements Controller {
	private PartManager partManager;

	public PartManager getPartManager() {
		return partManager;
	}

	public void setPartManager(PartManager partManager) {
		this.partManager = partManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) {
		int bookId = request.getParameter("bookId") == null ? 1 : Integer
				.parseInt(request.getParameter("bookId"));
		List<Part> parts = partManager.getParts(bookId);
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("parts", parts);
		props.put("editor", new com.bookwriter.fckeditor.FCKeditorWrapper(
				request));

		return new ModelAndView("blog", props);
	}
}
