package com.bookwriter.persist;

import java.text.StringCharacterIterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.bookwriter.model.HibernateUtil;
import com.bookwriter.model.Part;
import com.bookwriter.model.Story;

/**
 * This class is used to access data about parts of the book
 * 
 * @author abogoley
 * 
 */
public class PartManager {
	public List<Part> getParts(int bookId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<Part> parts = null;
		try {
			Criteria crit = session.createCriteria(Part.class);
			crit.add(Expression.eq("bookId", bookId));
			crit.addOrder(Order.asc("datetime"));
			parts = crit.list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}

		return parts;
	}

	public Part getPart(int partId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Part part = null;
		try {
			Criteria crit = session.createCriteria(Part.class);
			crit.add(Expression.eq("partId", partId));
			part = (Part) crit.uniqueResult();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}

		return part;
	}

	static final long MILLIS_IN_A_DAY = 1000 * 60 * 60 * 24;

	public List<Part> getLatestParts() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<Part> parts = null;
		try {
			Criteria crit = session.createCriteria(Part.class);
			crit.add(Expression.ge("datetime", new java.sql.Date(
					new java.util.Date().getTime() - MILLIS_IN_A_DAY)));
			crit.addOrder(Order.asc("datetime"));
			parts = crit.list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}

		return parts;
	}

	/**
	 * Saves a Part object.
	 */
	public void savePart(Part part) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		try {
			part.setText(addSlashes(part.getText()));
			session.saveOrUpdate(part);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
	}

	public static String addSlashes(String text) {
		final StringBuffer sb = new StringBuffer(text.length() * 2);
		final StringCharacterIterator iterator = new StringCharacterIterator(
				text);
		System.out.println("Adding slashes");
		char character = iterator.current();

		while (character != StringCharacterIterator.DONE) {
			if (character == '\'')
				sb.append("\\\'");
			else if (character == '"')
				sb.append("\\\"");
			else if (character == '\'')
				sb.append("\\\'");
			else if (character == '\\')
				sb.append("\\\\");
			else if (character == '\n')
				sb.append("\\n");
			else if (character == '{')
				sb.append("\\{");
			else if (character == '}')
				sb.append("\\}");
			else if (character == '¶')
				sb.append("");
			else
				sb.append(character);

			character = iterator.next();
		}

		return sb.toString();
	}

	public List<Part> searchParts(String keyword) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<Part> parts = null;
		try {
			Criteria crit = session.createCriteria(Part.class);
			crit.add(Restrictions.like("keywords", "%" + keyword + "%"));

			// crit.setProjection(Projections.groupProperty("genreId"));
			parts = crit.list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}

		return parts;
	}
}
