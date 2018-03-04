package com.revature.formatted;

import org.springframework.stereotype.Component;

@Component("flightID")
public class FlightID {

	public FlightID() {
		super();
	}

	public FlightID(int id) {
		this();
		this.id = id;
	}

	
	private int id;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
