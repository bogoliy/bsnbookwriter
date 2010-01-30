package ua.kiev.kpi.comsys.hp.webapp.controllers;

import java.net.BindException;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import ua.kiev.kpi.comsys.hp.jcr.JCRArticleManager;
import ua.kiev.kpi.comsys.hp.jcr.model.JCRArticle;
import ua.kiev.kpi.comsys.hp.model.Article;
import ua.kiev.kpi.comsys.hp.persist.ArticleManager;

public class AddArticleController extends
		org.springframework.web.servlet.mvc.SimpleFormController {

	private JCRArticleManager jcrArticleManager;

	public JCRArticleManager getJcrArticleManager() {
		return jcrArticleManager;
	}

	public void setJcrArticleManager(JCRArticleManager jcrArticleManager) {
		this.jcrArticleManager = jcrArticleManager;
	}

	/**
	 * Always returns a new Part object
	 * 
	 * @see Part
	 */
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		return new JCRArticle();
	}

	/** Validates user/password against database */
	public void onBindAndValidate(HttpServletRequest request, Object command,
			BindException errors) throws Exception {
		log.info(errors.getMessage());
	}

	public static Logger log = Logger.getLogger(AddArticleController.class);

	/** returns ModelAndView(getSuccessView()) */
	@Override
	protected ModelAndView onSubmit(
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response,
			java.lang.Object command,
			org.springframework.validation.BindException errors)
			throws java.lang.Exception {
		jcrArticleManager.saveArticle("main",(new RequestContext((HttpServletRequest) request))
				.getLocale().toString(), ((JCRArticle) command).getData());
		return new ModelAndView(getSuccessView());
	}

}