package com.revature.dao;

import java.util.List;


import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.revature.domain.EndUser;
import com.revature.domain.Reservation;
import com.revature.util.HibernateUtil;

@Repository("endUserDaoImpl")
public class EndUserDaoImpl implements EndUserDao {

	@Override
	public EndUser getEndUserById(int id) {
		Session s = HibernateUtil.getSession();
		EndUser thisEndUser = (EndUser) s.get(EndUser.class, id);
		s.close();
		return thisEndUser;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public EndUser getEndUserByEmail(String email) {
		
		Session s = HibernateUtil.getSession();
		EndUser thisEndUser = null;
		try {
			Criteria c = s.createCriteria(EndUser.class);
			c.add(Restrictions.eq("email", email));
			List<EndUser> endUsers = c.list();
			thisEndUser = endUsers.get(0);
		} catch (Exception e) {
			// This state could not be found
			thisEndUser = null;
		}
		s.close();
		return thisEndUser;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<EndUser> getAllEndUsers(){
		Session s = HibernateUtil.getSession();
		
		List<EndUser> endUserList = s.createQuery("from EndUser").list();
		
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
	@Transactional
	public void updateEndUser(EndUser u) {
		Session s = HibernateUtil.getSession();
				
		s.merge(u);
		
		s.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reservation> getReservationHistory(EndUser user) {
		Session s = HibernateUtil.getSession();
		
		List<Reservation> reservations = s.createCriteria(Reservation.class)
				.add( Restrictions.eq("endUser", user) )
				.addOrder( Order.desc("creationDate") ).list();
		
		s.close();
		return reservations;
	}
}
