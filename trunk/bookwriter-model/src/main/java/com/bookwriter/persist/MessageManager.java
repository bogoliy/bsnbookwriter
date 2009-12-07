package com.bookwriter.persist;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import com.bookwriter.model.HibernateUtil;
import com.bookwriter.model.Message;
import com.bookwriter.model.Part;

public class MessageManager {
	public Message getMessage(String code) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Message mess = null;
		try {
			Criteria crit = session.createCriteria(Message.class);
			crit.add(Expression.eq("code",code));
			mess = (Message)crit.uniqueResult();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}

		return mess;
	}
	
}
