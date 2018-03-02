package com.revature.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.domain.Feedback;
import com.revature.util.HibernateUtil;

public class FeedbackDaoImpl implements FeedbackDao {

	@Override
	public Feedback getFeedbackById(int id) {
		Session s = HibernateUtil.getSession();
		Feedback thisFeedback = (Feedback) s.get(Feedback.class, id);
		s.close();
		return thisFeedback;
	}

	@Override
	public int addFeedback(Feedback thisFeedback) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		int result = (int) s.save(thisFeedback);
		try {
			tx.commit();
		} catch (Exception e) {
			result = 0;
			tx.rollback();
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Feedback> getAllFeedback() {
		Session s = HibernateUtil.getSession();

		List<Feedback> feedbackList = s.createQuery("from Feedback").list();
		
		s.close();
		
		return feedbackList;
	}

	@Override
	@Transactional
	public void deleteFeedback(Feedback thisFeedback) {
		Session s = HibernateUtil.getSession();

		s.delete(thisFeedback);
		
		s.close();
	}
}
