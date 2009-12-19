package ua.kiev.kpi.comsys.hp.model;

import javax.persistence.EntityManager;
import junit.framework.TestCase;
public class ArticleTest extends TestCase{
	public void testInsertData() {
		EntityManager em = ua.kiev.kpi.comsys.hp.model.HibernateUtil.getEjb3Configuration()
				.buildEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		Article article = em.find(Article.class, new Long(1));
		assertEquals("Test Article", article.getTitle());
		em.getTransaction().commit();
		em.close();
	
	}
	
}

