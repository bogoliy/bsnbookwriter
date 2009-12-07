package com.bookwriter.persist;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.bookwriter.model.HibernateUtil;
import com.bookwriter.model.Part;
import com.bookwriter.model.Story;

/**
 * This class is used to access data about the stories and their genres
 * 
 * @author abogoley
 * 
 */
public class StoryManager {
	public List<Story> getStories() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<Story> stories = null;
		try {
			Criteria crit = session.createCriteria(Story.class).addOrder(
					Order.asc("genreId"));
			// crit.setProjection(Projections.groupProperty("genreId"));
			stories = crit.list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}

		return stories;
	}

	/**
	 * Saves a Story object.
	 */
	public void saveStory(Story story) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		try {
			session.saveOrUpdate(story);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
	}


}
