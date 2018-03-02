package com.revature.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.revature.domain.State;
import com.revature.util.HibernateUtil;

public class StateDaoImpl implements StateDao {

	@Override
	public State getStateById(int id) {
		Session s = HibernateUtil.getSession();
		State thisState = (State) s.get(State.class, id);
		s.close();
		return thisState;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public State getStateByName(String name) {
		
		Session s = HibernateUtil.getSession();
		State thisState = null;
		try {
			Criteria c = s.createCriteria(State.class);
			c.add(Restrictions.eq("name", name));
			List<State> states = c.list();
			thisState = states.get(0);
			System.out.println(thisState.toString());
		} catch (Exception e) {
			// This state could not be found
			thisState = null;
		}
		s.close();
		return thisState;
	}

	@Override
	public int addState(State thisState) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		int result = (int) s.save(thisState);
		try {
			tx.commit();
		} catch (Exception e) {
			result = 0;
			tx.rollback();
		}
		return result;
	}

}
