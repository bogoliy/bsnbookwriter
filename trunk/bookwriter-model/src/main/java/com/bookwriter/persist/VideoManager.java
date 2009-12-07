package com.bookwriter.persist;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.bookwriter.model.HibernateUtil;
import com.bookwriter.model.Part;
import com.bookwriter.model.Video;

public class VideoManager {
	public List<Video> getVideos() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<Video> videos  = null; 
		try {
			Criteria crit = session.createCriteria(Video.class);
			videos = crit.list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}

		return videos;
	}
	
}
