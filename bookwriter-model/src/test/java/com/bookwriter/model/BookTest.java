package com.bookwriter.model;

import javax.persistence.EntityManager;

import junit.framework.TestCase;


public class BookTest extends TestCase {
	public void testInsertData() {
		EntityManager em = com.bookwriter.model.HibernateUtil.getEjb3Configuration()
				.buildEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		Book testBook = em.find(Book.class, 1);
		System.out.println(testBook.getTitle());
		this.assertEquals("First Book", testBook.getTitle());
		em.getTransaction().commit();
		em.close();

	}

}
