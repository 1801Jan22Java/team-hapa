package com.revature.dao;

import com.revature.domain.Flight;

public interface FlightDao {
	public Flight getFlightById(int id);
	public int addFlight(Flight thisFlight);
}
