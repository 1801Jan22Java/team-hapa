package com.revature.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.revature.domain.Reservation;
import com.revature.util.HibernateUtil;

public class ReservationDaoImpl implements ReservationDao {

	@Override
	public Reservation getReservationById(int id) {
		Session s = HibernateUtil.getSession();
		Reservation thisReservation = (Reservation) s.get(Reservation.class, id);
		s.close();
		return thisReservation;
	}
	

	@Override
	public int addReservation(Reservation thisReservation) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		int result = (int) s.save(thisReservation);
		try {
			tx.commit();
		} catch (Exception e) {
			result = 0;
			tx.rollback();
		}
		return result;
	}

}
