package com.revature.formatted;

import org.springframework.stereotype.Component;

import com.revature.domain.CommonLookup;
import com.revature.domain.Flight;

@Component("flightDetails")
public class FlightDetails {

	
	public FlightDetails() {
		super();
	}
	public FlightDetails(Flight flight, CommonLookup status) {
		this();
		this.flight = flight;
		this.status = status;
	}
	
	
	private Flight flight;
	private CommonLookup status;	//refValue = Reserved or Cancelled
									//null = default (not reserved)
	
	
	public Flight getFlight() {
		return flight;
	}
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	public CommonLookup getStatus() {
		return status;
	}
	public void setStatus(CommonLookup status) {
		this.status = status;
	}
	
}
