package ua.kiev.kpi.comsys.hp.persist;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;

import ua.kiev.kpi.comsys.hp.model.Article;
import ua.kiev.kpi.comsys.hp.model.HibernateUtil;

public class ArticleManager {
	public static final String TITLE_ARTICLE = "TITLE";
	public Article getArticle(int articleId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Article article = (Article) session.load(Article.class, articleId);
		return article;
	}

	@SuppressWarnings("unchecked")
	public List<Article> getArticles(String title) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<Article> articles = null;
		try {
			Criteria crit = session.createCriteria(Article.class);
			crit.add(Expression.like("title", title, MatchMode.ANYWHERE));
			articles = crit.list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}

		return articles;
	}
	
	@SuppressWarnings("unchecked")
	public List<Article> getArticlesByType(String type) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<Article> articles = null;
		try {
			Criteria crit = session.createCriteria(Article.class);
			crit.add(Expression.like("type", type, MatchMode.EXACT));
			articles = crit.list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}

		return articles;
	}
}
