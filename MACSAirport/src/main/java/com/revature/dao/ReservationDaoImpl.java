package com.revature.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.revature.domain.CommonLookup;
import com.revature.domain.Reservation;
import com.revature.exception.FullFlightException;
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

	@Override
	public void checkIn(int flightID) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		CommonLookupDao cldi = new CommonLookupDaoImpl();
		CommonLookup reservedStatus = cldi.getCommonLookupByName("RESERVATION_STATUS", "Checked In");
		ReservationDao rd = new ReservationDaoImpl();
		Reservation thisReservation = rd.getReservationById(flightID);
		
		thisReservation.setStatusId(reservedStatus);
		
		s.merge(thisReservation);
		
		tx.commit();
		s.close();
	}
	

	@Override
	public void cancel(int flightID) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		CommonLookupDao cldi = new CommonLookupDaoImpl();
		
		CommonLookup reservedStatus = cldi.getCommonLookupByName("RESERVATION_STATUS", "Cancelled");
		
		ReservationDao rd = new ReservationDaoImpl();
		Reservation thisReservation = rd.getReservationById(flightID);
		
		thisReservation.setStatusId(reservedStatus);
		
		s.merge(thisReservation);
		
		tx.commit();
		s.close();
	}
}
