package com.revature.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.revature.domain.Flight;
import com.revature.util.HibernateUtil;

public class FlightDaoImpl implements FlightDao {

	@Override
	public Flight getFlightById(int id) {
		Session s = HibernateUtil.getSession();
		Flight thisFlight = (Flight) s.get(Flight.class, id);
		s.close();
		return thisFlight;
	}

	@Override
	public int addFlight(Flight thisFlight) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		int result = (int) s.save(thisFlight);
		try {
			tx.commit();
		} catch (Exception e) {
			result = 0;
			tx.rollback();
		}
		return result;
	}

}
