package com.revature.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import com.revature.dao.CityDao;
import com.revature.dao.CityDaoImpl;
import com.revature.dao.CommonLookupDao;
import com.revature.dao.CommonLookupDaoImpl;
import com.revature.dao.CountryDao;
import com.revature.dao.CountryDaoImpl;
import com.revature.dao.FlightDao;
import com.revature.dao.FlightDaoImpl;
import com.revature.dao.StateDao;
import com.revature.dao.StateDaoImpl;
import com.revature.domain.City;
import com.revature.domain.CommonLookup;
import com.revature.domain.Flight;

public class FlightService {

	/*
	 *  It uses getAllPendingFlights() to pull all flights that haven't happened yet, or return null.
		If there are no pending flights it will generate new flights with arrival/departure times calculated from the current time.
		If there are pending flights it calculate arrival/departure times calculated from the latest pending flights.
		
		If there are no pending flights it will choose whether the flights are arrivals or departures randomly.
		If there are pending flights it will the flight type opposite of the latest flight to that gate.
		
		It will choose a city at random for the destination/arrival of each gate that will be different from the cities chosen for the other gates.
		
		The base cost is based on the total distance, but a STANDARD_FEE is added onto the cost, that can be changed with new airport policy.
		
		The duration is based on the total distance divided by AVG_MPH, which can be changed with new airport policy
		
		The new flight time is calculated by adding together three values:
		1.  If there are no pending flights, start with the current time
		    If there are pending flights, start with the time of the latest flight at that gate
		2.  a random number of minutes between 0 and MAX_ADDITIONAL_WAIT_TIME, which can be changed with new airport policy
		3.  a MANDATORY_WAIT_TIME, which can be changed with new airport policy

	 */
	public static ArrayList<Flight> addFlights() {
		
		CityDao cid = new CityDaoImpl();
		FlightDao fd = new FlightDaoImpl();
		CountryDao cod = new CountryDaoImpl();
		CityDao cd = new CityDaoImpl();
		StateDao sd = new StateDaoImpl();
		CommonLookupDao cld = new CommonLookupDaoImpl();
		Random randomMethod = new Random();
		
		City newCity = null;
		Date newTime = null;
		final double AVG_TICKET_2012 = 381.56;
		final int AVG_DISTANCE_2012 = 2356;
		final double AVG_COST_PER_MILE_2012 = AVG_TICKET_2012 / AVG_DISTANCE_2012;
		final long ONE_MINUTE_IN_MILLIS = 60000; // millisecs
		final String[] TYPES = {"Arrival", "Departure"};
		
		City homeCity = new City("Reston", 58404, 38.958631, -77.357003, sd.getStateByName( "Virginia"), cod.getCountryByName("United States") );
		
		// This class is not a servlet and cannot access Servlet Context Params for external reference
		final int TOTAL_GATES = 5;
		final double STANDARD_FEE = 89.15;
		final int MANDATORY_WAIT_TIME = 60; // gate must be occupied for at least 60 minutes
		final int MAX_ADDITIONAL_WAIT_TIME = 240; // 0-239 minutes of additional waiting until next plane at that gate
		final int AVG_MPH = 550;
		
		ArrayList<City> allCities = (ArrayList<City>) cid.getAllCities();
		ArrayList<Flight> allFlights = (ArrayList<Flight>) fd.getAllPendingFlights();
		Flight[] newFlights = new Flight[TOTAL_GATES+1];
		for (int i = 0; i < newFlights.length; i++) {
			newFlights[i] = null;
		}
		if (allFlights != null) {
			for (Flight f : allFlights) {
				if (f.getGate() < newFlights.length && newFlights[f.getGate()] == null) {
					int waitTime = MANDATORY_WAIT_TIME + randomMethod.nextInt(MAX_ADDITIONAL_WAIT_TIME);
					long curTimeInMs = f.getTime().getTime();
				    newTime = new Date(curTimeInMs + (waitTime * ONE_MINUTE_IN_MILLIS));
				    
				    String nextType = (f.getType().getRefValue().equals("Arrival")) ? "Departure" : "Arrival";
					CommonLookup cl1 = cld.getCommonLookupByName("FLIGHT_TYPE", nextType);
					
					boolean foundMatch = false;
					do {
						newCity = allCities.get(randomMethod.nextInt(allCities.size())); // returns number between 0 and the last index of the array
						foundMatch = false;
						for (Flight nf : newFlights) {
							if (nf != null && (nf.getCity().getId() == newCity.getId())) {
								foundMatch = true;
							}
						}
					} while (foundMatch);
					
					double distance = cd.distanceBetween(homeCity, newCity);
					int newDurationMin = (int) (distance / AVG_MPH * 60.0);
					
					double newCost = (AVG_COST_PER_MILE_2012 * distance) + STANDARD_FEE;
					
					Flight newFlight = new Flight(f.getGate(), newTime, newCost, newDurationMin, cl1, newCity);
					newFlights[f.getGate()] = newFlight;
					newFlight.setId(fd.addFlight(newFlight));
				}
			}
		}
		for (int i = 1; i < newFlights.length; i++) {
			if (newFlights[i] == null) {
				int waitTime = MANDATORY_WAIT_TIME + randomMethod.nextInt(MAX_ADDITIONAL_WAIT_TIME);
				long curTimeInMs = new Date().getTime();
			    newTime = new Date(curTimeInMs + (waitTime * ONE_MINUTE_IN_MILLIS));
			    
				String nextType = TYPES[randomMethod.nextInt(TYPES.length)];// returns number between 0 and the last index of the array
				CommonLookup cl1 = cld.getCommonLookupByName("FLIGHT_TYPE", nextType);
				
				boolean foundMatch = false;
				do {
					newCity = allCities.get(randomMethod.nextInt(allCities.size())); // returns number between 0 and the last index of the array
					foundMatch = false;
					for (Flight nf : newFlights) {
						if (nf != null && (nf.getCity().getId() == newCity.getId())) {
							foundMatch = true;
						}
					}
				} while (foundMatch);
				
				double distance = cd.distanceBetween(homeCity, newCity);
				int newDurationMin = (int) (distance / AVG_MPH * 60.0);
				
				double newCost = (AVG_COST_PER_MILE_2012 * distance) + STANDARD_FEE;
				
				Flight newFlight = new Flight(i, newTime, newCost, newDurationMin, cl1, newCity);
				newFlights[i] = newFlight;
				newFlight.setId(fd.addFlight(newFlight));
			}
		}
		
		ArrayList<Flight> returnNewFlights = new ArrayList<>();
		for (int i = 1; i < newFlights.length; i++) {
			returnNewFlights.add(newFlights[i]);
		}
		return returnNewFlights;
	}
}
