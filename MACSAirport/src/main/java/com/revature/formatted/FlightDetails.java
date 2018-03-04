package com.revature.formatted;

import com.revature.domain.CommonLookup;
import com.revature.domain.Flight;

public class FlightDetails {

	
	public FlightDetails(Flight flight, CommonLookup status) {
		super();
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
