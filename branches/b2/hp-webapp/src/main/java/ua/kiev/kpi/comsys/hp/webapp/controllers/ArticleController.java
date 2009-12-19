package ua.kiev.kpi.comsys.hp.webapp.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import ua.kiev.kpi.comsys.hp.model.Article;
import ua.kiev.kpi.comsys.hp.persist.ArticleManager;
import ua.kiev.kpi.comsys.hp.webapp.fckeditor.FCKeditorWrapper;

public class ArticleController extends MultiActionController {
	private ArticleManager articleManager;

	public ArticleController() {
	}

	public ArticleManager getArticleManager() {
		return articleManager;
	}


	public void setArticleManager(ArticleManager articleManager) {
		this.articleManager = articleManager;
	}

	public Map index(HttpServletRequest request,
			HttpServletResponse response) {
		List<Article> articles = articleManager
				.getArticlesByType(ArticleManager.TITLE_ARTICLE);
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("articles", articles);
		props.put("editor", new FCKeditorWrapper(request));
		Logger.getLogger(ArticleController.class).info("Retieved " + articles.size() + " articles");
		return props;
	}
	

}
