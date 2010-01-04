package ua.kiev.kpi.comsys.hp.jcr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.Workspace;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;
import javax.jcr.query.RowIterator;

import org.springmodules.jcr.JcrCallback;
import org.springmodules.jcr.JcrConstants;
import org.springmodules.jcr.JcrTemplate;

import ua.kiev.kpi.comsys.hp.model.Article;

public class JCRArticleManager {
	JcrTemplate jcrTemplate = null;

	public JcrTemplate getJcrTemplate() {
		return jcrTemplate;
	}

	public void setJcrTemplate(JcrTemplate jcrTemplate) {
		this.jcrTemplate = jcrTemplate;
	}

	/**
	 * Used to get Articles for the main page
	 * 
	 * @return articles
	 */
	public ArrayList<Article> getMainPageArticles(final String locale) {
		Node res = (Node) jcrTemplate.execute(new JcrCallback() {

			/**
			 * @see org.springmodules.jcr.JcrCallback#doInJcr(javax.jcr.Session)
			 */
			public Object doInJcr(Session session) throws RepositoryException,
					IOException {
				Workspace workspace = session.getWorkspace();
				QueryManager qManager = workspace.getQueryManager();
				javax.jcr.query.Query query = qManager
						.createQuery(
								"//element(*,nt:folder)/"
										+ locale
										+ "/main/element(*,nt:file)/jcr:content/(@jcr:data)",
								Query.XPATH);
				System.out.println(query.getStatement());

				QueryResult result = query.execute();
				RowIterator i = result.getRows();
				for (; i.hasNext();) {
					System.out.println(">" + i.nextRow().getValue("jcr:data").getString());
				}
				return null;
			}
		});
		return null;
	}
}
