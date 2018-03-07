package com.revature.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.revature.domain.City;
import com.revature.domain.CommonLookup;
import com.revature.domain.EndUser;
import com.revature.domain.Flight;
import com.revature.domain.Reservation;
import com.revature.exception.FullFlightException;
import com.revature.util.HibernateUtil;

@Repository("flightDaoImpl")
public class FlightDaoImpl implements FlightDao {

	@Override
	public Flight getFlightById(int id) {
		Session s = HibernateUtil.getSession();
		Flight thisFlight = (Flight) s.get(Flight.class, id);
		s.close();
		return thisFlight;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Flight> getFlightsByUserId(int userId){
		Session s = HibernateUtil.getSession();
		List<Flight> flights = s.createCriteria(Flight.class)
				.add( Restrictions.lt("time", new Date()) )
				.addOrder( Order.desc("time") )
				.list();
		return flights;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Flight> searchFlight(Date date, City c) {
		Session s = HibernateUtil.getSession();
		CommonLookup cl = new CommonLookupDaoImpl().getCommonLookupByName("FLIGHT_TYPE", "Departure");
		List<Flight> flights = s.createCriteria(Flight.class)
				.add( Restrictions.eq("city", c) )
				.add( Restrictions.ge("time", date) )
				.add( Restrictions.eq("type", cl) )
				.list();
		s.close();
		return flights;
	}

	@Override
	public void makeReservation(EndUser user, Flight f, boolean firstClass) throws FullFlightException {
		Session s = HibernateUtil.getSession();
		CommonLookupDao cldi = new CommonLookupDaoImpl();
		CommonLookup reservedStatus = cldi.getCommonLookupByName("RESERVATION_STATUS", "Reserved");
		CommonLookup type = null;
		if (firstClass) {
			type = cldi.getCommonLookupByName("RESERVATION_TYPE", "First Class");
		} else {
			type = cldi.getCommonLookupByName("RESERVATION_TYPE", "Economy");
		}
		long count = (Long) s.createCriteria(Reservation.class)
				.add( Restrictions.eq("flight", f) )
				.add( Restrictions.eq("status", reservedStatus) )
				.add( Restrictions.eq("type", type) )
				.setProjection( Projections.rowCount() ).list().get(0);
		if (!firstClass && count < 10) {
			Reservation r = new Reservation(user, f, reservedStatus, type);
			new ReservationDaoImpl().addReservation(r);
		} else if (firstClass && count < 2) {
			Reservation r = new Reservation(user, f, reservedStatus, type);
			new ReservationDaoImpl().addReservation(r);
		} else {
			throw new FullFlightException();
		}
		s.close();
	}

	@Override
	public void cancelReservation(EndUser user, Flight f) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		try {
			Reservation r = (Reservation) s.createCriteria(Reservation.class)
					.add( Restrictions.eq("endUser", user) )
					.add( Restrictions.eq("flight", f)).list().get(0);
			r.setStatusId(new CommonLookupDaoImpl().getCommonLookupByName("RESERVATION_STATUS", "Cancelled"));
			s.merge(r);
		} catch (Exception e) {
			tx.rollback();
		}
		tx.commit();
		s.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Flight> getMostRecent10Departures() {
		Session s = HibernateUtil.getSession();
		List<Flight> flights = s.createCriteria(Flight.class)
				.add( Restrictions.eq("type", new CommonLookupDaoImpl().getCommonLookupByName("FLIGHT_TYPE", "Departure")) )
				.add( Restrictions.ge("time", new Date()) )
				.addOrder( Order.asc("time") )
				.setMaxResults(10)
				.list();
		s.close();
		return flights;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Flight> getMostRecent10Arrivals() {
		Session s = HibernateUtil.getSession();
		List<Flight> flights = s.createCriteria(Flight.class)
				.add( Restrictions.eq("type", new CommonLookupDaoImpl().getCommonLookupByName("FLIGHT_TYPE", "Arrival")) )
				.add( Restrictions.ge("time", new Date()) )
				.addOrder( Order.asc("time") )
				.setMaxResults(10)
				.list();
		s.close();
		return flights;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Flight> getAllPendingFlights() {
		Session s = HibernateUtil.getSession();
		Criteria c = s.createCriteria(Flight.class);
		c.add(Restrictions.ge("time", new Date()));
		c.addOrder( Order.desc("time") );
		List<Flight> allFlights = c.list();
		if (allFlights.size() < 1) {
			allFlights = null;
		}
		s.close();
		return allFlights;
	}
	
}
