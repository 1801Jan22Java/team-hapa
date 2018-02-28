package com.revature.dao;

import java.util.Date;
import java.util.List;

import com.revature.domain.City;
import com.revature.domain.EndUser;
import com.revature.domain.Flight;
import com.revature.exception.FullFlightException;

public interface FlightDao {
	public Flight getFlightById(int id);
	public int addFlight(Flight thisFlight);
	public List<Flight> searchFlight(Date date, City c);
	public void makeReservation(EndUser user, Flight f, boolean firstClass) throws FullFlightException;
	public void cancelReservation(EndUser user, Flight f);
	public List<Flight> getMostRecent10Departures();
	public List<Flight> getMostRecent10Arrivals();
}
