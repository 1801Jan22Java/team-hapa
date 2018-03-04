package com.revature.formatted;

public class FlightSearchInfo {

	public FlightSearchInfo(String earliestDate, String destination) {
		super();
		this.earliestDate = earliestDate;
		this.destination = destination;
	}
	
	private String earliestDate;
	private String destination;
	
	
	public String getEarliestDate() {
		return earliestDate;
	}
	public void setEarliestDate(String earliestDate) {
		this.earliestDate = earliestDate;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	
}
