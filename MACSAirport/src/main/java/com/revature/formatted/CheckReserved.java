package com.revature.formatted;

import org.springframework.stereotype.Component;

@Component("checkReserved")
public class CheckReserved {
	
	public CheckReserved() {
		super();
	}
	public CheckReserved(int userID, int flightID) {
		this();
		this.userID = userID;
		this.flightID = flightID;
	}
	
	
	private int userID;
	private int flightID;
	
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getFlightID() {
		return flightID;
	}
	public void setFlightID(int flightID) {
		this.flightID = flightID;
	}
}
