package com.revature.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.revature.domain.EndUser;
import com.revature.util.HibernateUtil;

public class EndUserDaoImpl implements EndUserDao {

	@Override
	public EndUser getEndUserById(int id) {
		Session s = HibernateUtil.getSession();
		EndUser thisEndUser = (EndUser) s.get(EndUser.class, id);
		s.close();
		return thisEndUser;
	}
	
	@Override
	public EndUser getEndUserByEmail(String email) {
		
		Session s = HibernateUtil.getSession();
		EndUser thisEndUser = null;
		try {
			Criteria c = s.createCriteria(EndUser.class);
			c.add(Restrictions.eq("email", email));
			List<EndUser> endUsers = c.list();
			thisEndUser = (EndUser) endUsers.get(0);
		} catch (Exception e) {
			// This state could not be found
			thisEndUser = null;
		}
		s.close();
		return thisEndUser;
	}
	
	@Override
	public List<EndUser> getAllEndUsers(){
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		
		List<EndUser> endUserList = s.createQuery("from EndUser").list();
		
		tx.commit();
		s.close();
		
		return endUserList;
		
	}

	@Override
	public int addEndUser(EndUser thisEndUser) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		int result = (int) s.save(thisEndUser);
		try {
			tx.commit();
		} catch (Exception e) {
			result = 0;
			tx.rollback();
		}
		return result;
	}
	
	@Override
	public void updateEndUser(EndUser u) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
				
		s.merge(u);
		
		tx.commit();
		s.close();
	}

}
