package com.revature.dao;

import java.util.List;

import org.hibernate.*;

import com.revature.domain.EndUser;
import com.revature.util.HibernateUtil;

public class EndUserDaoImpl implements EndUserDao{

	@Override
	public void createEndUser(EndUser u) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		
		s.persist(u);
		
		tx.commit();
		s.close();
	}
	
	
	//Passing in a User object with the modified details.
	
	//Can also use with password reset; be sure to hash
	//the password and add it to this User object before
	//calling this method.
	
	//No fly can simply change the noFly member variable.
	@Override
	public void updateEndUser(EndUser u) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
				
		s.merge(u);
		
		tx.commit();
		s.close();
	}
	
	
	@Override
	public EndUser readUserById(int endUserID) {
		Session s = HibernateUtil.getSession();
		EndUser user = (EndUser) s.get(EndUser.class,  endUserID);
		
		s.close();
		return user;
	}
	
	
	
	@Override
	public List<EndUser> viewAllEndUsers(){
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		
		List<EndUser> endUserList = s.createQuery("from EndUser").list();
		
		tx.commit();
		s.close();
		
		return endUserList;
		
	}
	
	
	
}
