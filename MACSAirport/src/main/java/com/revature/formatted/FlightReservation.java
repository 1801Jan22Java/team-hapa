package com.revature.formatted;

import org.springframework.stereotype.Component;

@Component("flightReservation")
public class FlightReservation {
	public FlightReservation() {
		super();
	}
	public FlightReservation(int flightID, int userID, String type) {
		this();
		this.flightID = flightID;
		this.userID = userID;
		this.type = type;
	}
	
	
	
	private int flightID;
	private int userID;
	private String type;
	
	
	
	public int getFlightID() {
		return flightID;
	}
	public void setFlightID(int flightID) {
		this.flightID = flightID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}