package com.revature.dao;

import com.revature.domain.Reservation;

public interface ReservationDao {
	public Reservation getReservationById(int id);
	public int addReservation(Reservation thisReservation);
	
}
