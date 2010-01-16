package ua.kiev.kpi.comsys.hp.jcr;

import java.io.ByteArrayInputStream;
import java.io.IOException;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.Workspace;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;
import javax.jcr.query.Row;
import javax.jcr.query.RowIterator;

import org.springmodules.jcr.JcrCallback;
import org.springmodules.jcr.JcrConstants;
import org.springmodules.jcr.JcrTemplate;


import ua.kiev.kpi.comsys.hp.jcr.model.JCRArticle;


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
	@SuppressWarnings("unchecked")
	public ArrayList<JCRArticle> getMainPageArticles(final String locale) {
		ArrayList<JCRArticle> res = (ArrayList<JCRArticle>) jcrTemplate.execute(new JcrCallback() {

			/**
			 * @see org.springmodules.jcr.JcrCallback#doInJcr(javax.jcr.Session)
			 */
			public Object doInJcr(Session session) throws RepositoryException,
					IOException {
				ArrayList<JCRArticle> articles = new ArrayList<JCRArticle>();
				Workspace workspace = session.getWorkspace();
				QueryManager qManager = workspace.getQueryManager();
				javax.jcr.query.Query query = qManager
						.createQuery(
								"//element(*,nt:folder)/"
										+ locale
										+ "/main/element(*,nt:file)/jcr:content/(@jcr:data | @jcr:lastModified)",
								Query.XPATH);
				System.out.println(query.getStatement());

				QueryResult result = query.execute();
				RowIterator i = result.getRows();
				for (; i.hasNext();) {
					JCRArticle article = new JCRArticle ();
					Row row = i.nextRow();
					System.out.println(">" + row.getValue("jcr:data").getString());
					article.setData(row.getValue("jcr:data").getString());
					article.setLastModified(row.getValue("jcr:lastModified").getDate().getTime());
					articles.add(article);
				}
				return articles;
			}
		});
		return res;
	}
	
	

	/**
	 * Used to get Articles for the main page
	 * 
	 * @return articles
	 */
	@SuppressWarnings("unchecked")
	public Node saveArticle(final String page,final String locale, final String data) {
		  return (Node) jcrTemplate.execute(new JcrCallback() {
	           
				/**
				* @see org.springmodules.jcr.JcrCallback#doInJcr(javax.jcr.Session)
				*/
				public Object doInJcr(Session session) throws 
				RepositoryException, IOException {
			  
			       
				JcrConstants jcrConstants = new JcrConstants(session);
			        
				//create the file node - see section 6.7.22.6 of the spec
				
				Node fileNode = session.getRootNode().addNode("articles/" + locale + "/" + page + "/" + System.currentTimeMillis(), 
									jcrConstants.getNT_FILE());
			  
				//create the mandatory child node - jcr:content
				Node resNode = fileNode.addNode(jcrConstants.getJCR_CONTENT(), 
									jcrConstants.getNT_RESOURCE());
			   
			    resNode.setProperty(jcrConstants.getJCR_MIMETYPE(), "text/html");
			    resNode.setProperty(jcrConstants.getJCR_ENCODING(), "utf-8");
				resNode.setProperty(jcrConstants.getJCR_DATA(), data);
				System.out.println("--------------" + data);
				Calendar lastModified = Calendar.getInstance();
				lastModified.setTimeInMillis (System.currentTimeMillis());
				resNode.setProperty(jcrConstants.getJCR_LASTMODIFIED(), lastModified);
			           
				session.save();
			             
				return resNode;
				}
			   });
	}
}
