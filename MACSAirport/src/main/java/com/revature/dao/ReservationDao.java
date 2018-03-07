package com.revature.dao;

import java.util.List;

import com.revature.domain.Flight;
import com.revature.domain.Reservation;

public interface ReservationDao {
	public Reservation getReservationById(int id);
	public int addReservation(Reservation thisReservation);
	public Reservation checkIn(int flightID);
	public Reservation cancel(int flightID);
	Reservation getReservationByFlight(Flight flight);
	Reservation getReservationByUserAndFlightID(int userID, int flightID);
	List<Reservation> getReservationsByUserID(int userID);
	
}
