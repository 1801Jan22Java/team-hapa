package com.revature.formatted;

import com.revature.domain.CommonLookup;
import com.revature.domain.Flight;

public class ReservationDetails {
	
	public ReservationDetails(Flight flight, CommonLookup status, CommonLookup type) {
		super();
		this.flight = flight;
		this.status = status;
		this.type = type;
	}
	
	
	private Flight flight;
	private CommonLookup status;	//refValue = Reserved or Cancelled
									//null = default (not reserved)
	private CommonLookup type;		//refValue = "First Class" or "Economy"
	
	
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
	public CommonLookup getType() {
		return type;
	}
	public void setType(CommonLookup type) {
		this.type = type;
	}
}
