package com.bookwriter.persist;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import com.bookwriter.model.Genre;
import com.bookwriter.model.HibernateUtil;

public class GenreManager {
	public List<Genre> getGenres() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<Genre> genres  = null; 
		try {
			Criteria crit = session.createCriteria(Genre.class).addOrder(Order.asc("id"));
			//crit.setProjection(Projections.groupProperty("title"));
			genres = crit.list();
			//System.out.println(genres);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}

		return genres;
	}
}
