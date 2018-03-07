package com.revature.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.domain.CommonLookup;
import com.revature.domain.EndUser;
import com.revature.domain.Flight;
import com.revature.domain.Reservation;
import com.revature.util.HibernateUtil;

@Repository("reservationDaoImpl")
public class ReservationDaoImpl implements ReservationDao {

	@Autowired
	EndUserDao eudi;

	@Autowired
	FlightDao fdi;
	
	@Override
	public Reservation getReservationById(int id) {
		Session s = HibernateUtil.getSession();
		Reservation thisReservation = (Reservation) s.get(Reservation.class, id);
		s.close();
		return thisReservation;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Reservation getReservationByFlight(Flight flight) {
		Session s = HibernateUtil.getSession();
		Reservation thisReservation = null;
		try {
			Criteria c = s.createCriteria(Reservation.class);
			c.add(Restrictions.eq("flight", flight));
			List<Reservation> reservations = c.list();
			thisReservation = reservations.get(0);
		} catch (Exception e) {
			// This reservation could not be found
			thisReservation = null;
		}
		
		s.close();
		return thisReservation;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Reservation> getReservationsByUserID(int userID) {
		Session s = HibernateUtil.getSession();
		List<Reservation> reservations = null;
		try {
			Criteria c = s.createCriteria(Reservation.class);
			c.add(Restrictions.eqOrIsNull("id", userID));
			reservations = c.list();
			
		} catch (Exception e) {
			reservations = null;
		}
		
		s.close();
		return reservations;
	}
	
	@Override
	public Reservation getReservationByUserAndFlightID(int userID, int flightID) {
		Session s = HibernateUtil.getSession();
		Reservation reservation = null;
		List<Reservation> reservations = null;
		
		EndUser user = eudi.getEndUserById(userID);
		Flight flight = fdi.getFlightById(flightID);
		
		try {
			Criteria c = s.createCriteria(Reservation.class);
			c.add(Restrictions.eq("endUser", user));
			c.add(Restrictions.eq("flight", flight));
			reservations = c.list();
			reservation = reservations.get(0);
		} catch (Exception e) {
			reservation = null;
		}
		return reservation;
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
	@Deprecated
	public Reservation checkIn(int flightID) {
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
		
		return thisReservation;
	}
	

	@Override
	@Deprecated
	public Reservation cancel(int flightID) {
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
		return thisReservation;
	}
}
