package com.bookwriter.fckeditor;

import javax.servlet.http.HttpServletRequest;

import net.fckeditor.FCKeditor;
import net.fckeditor.FCKeditorConfig;

public class FCKeditorWrapper {
	private HttpServletRequest request;

	public FCKeditorWrapper(final HttpServletRequest request) {
		this.request = request;
	}

	public String get(final String instanceName, final String value) {
		FCKeditor editor = new FCKeditor(request, instanceName);
		editor.setValue(value);
		editor.setConfig("CustomConfigurationsPath", request.getContextPath() + "/js/config.js");
		
		return editor.createHtml();
	}
}