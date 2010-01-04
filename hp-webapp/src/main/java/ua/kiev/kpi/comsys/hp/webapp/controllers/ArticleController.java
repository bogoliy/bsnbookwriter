package ua.kiev.kpi.comsys.hp.webapp.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.support.RequestContext;

import ua.kiev.kpi.comsys.hp.jcr.JCRArticleManager;
import ua.kiev.kpi.comsys.hp.model.Article;
import ua.kiev.kpi.comsys.hp.persist.ArticleManager;
import ua.kiev.kpi.comsys.hp.webapp.fckeditor.FCKeditorWrapper;

public class ArticleController extends MultiActionController {
	private ArticleManager articleManager;
	private JCRArticleManager jcrArticleManager;

	public ArticleController() {
	}

	public JCRArticleManager getJcrArticleManager() {
		return jcrArticleManager;
	}

	public void setJcrArticleManager(JCRArticleManager jcrArticleManager) {
		this.jcrArticleManager = jcrArticleManager;
	}

	public ArticleManager getArticleManager() {
		return articleManager;
	}

	public void setArticleManager(ArticleManager articleManager) {
		this.articleManager = articleManager;
	}

	public Map index(HttpServletRequest request, HttpServletResponse response) {
		List<Article> articles = articleManager
				.getArticlesByType(ArticleManager.TITLE_ARTICLE);
		jcrArticleManager.getMainPageArticles("ru");
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("articles", articles);
		props.put("editor", new FCKeditorWrapper(request));
		Logger.getLogger(ArticleController.class).info(
				"Retieved " + articles.size() + " articles");
		System.out.println((new RequestContext((HttpServletRequest) request))
				.getLocale());
		return props;
	}

}
