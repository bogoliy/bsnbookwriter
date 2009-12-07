package com.bookwriter.security;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.ui.AbstractProcessingFilter;
import org.springframework.security.userdetails.UserDetails;

public class LoginBean {

	// properties
	private String userId;

	private String password;

	/**
	 * default empty constructor
	 */
	public LoginBean() {

		Exception ex = (Exception) FacesContext
				.getCurrentInstance()
				.getExternalContext()
				.getSessionMap()
				.get(
						AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY);

		if (ex != null)
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, ex
							.getMessage(), ex.getMessage()));

	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void login(ActionEvent e) throws java.io.IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect(
				"j_spring_security_check?j_username=" + userId + "&j_password="
						+ password);
	}

	public void logout(ActionEvent e) throws java.io.IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect(
				"j_spring_security_logout");
	}

	public boolean isUserLogined() {
		Object obj = SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();

		if (obj instanceof UserDetails) {
			return true;
		} else {
			return false;
		}
	}
}
