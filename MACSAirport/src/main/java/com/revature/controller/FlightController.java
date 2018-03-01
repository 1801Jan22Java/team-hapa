package com.revature.controller;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.revature.dao.*;
import com.revature.domain.*;
import com.revature.exception.FullFlightException;

@Controller
public class FlightController {

	@RequestMapping(value = "/util/flight-search", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String flightSearch(@RequestParam("earliestDate") Date earliestDate,
								@RequestParam("city") String ci,
								@RequestParam("state") String s,
								@RequestParam("country") String co) {
		FlightDao fd = new FlightDaoImpl();

		StateDao sd = new StateDaoImpl();
		CityDao cd = new CityDaoImpl();
		CountryDao cod = new CountryDaoImpl();
		List<Flight> flightList = null;


		if(co.equals("United States")) {
			State state = sd.getStateByName(s);
			Country country = cod.getCountryByName(co);
			City city = cd.getCityByName(ci, state, country);
			flightList = fd.searchFlight(earliestDate, city);
		} else {
			Country country = cod.getCountryByName(co);
			City city = cd.getIntlCityByName(ci, country);
			flightList = fd.searchFlight(earliestDate, city);
		}
		
		Gson gson = new Gson();
		return gson.toJson(flightList);
	}
	
	
	@RequestMapping(value = "/util/flight-details", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String flightDetails(@RequestParam("flightID") int flightID) {
		FlightDao fd = new FlightDaoImpl();
		Flight flight = fd.getFlightById(flightID);
		
		Gson gson = new Gson();
		return gson.toJson(flight);
	}
	
	
	/*
	 * Returns a JSON string containing a list of 10 soonest arrivals, followed by 10 soonest departures
	 */
	@RequestMapping(value = "/util/all-flights", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String allFlights() {
		FlightDao fd = new FlightDaoImpl();
		List<Flight> flightList = fd.getMostRecent10Arrivals();
		List<Flight> flightListDepartures = fd.getMostRecent10Departures();
		for(Flight f : flightListDepartures) {
			flightList.add(f);
		}
		
		Gson gson = new Gson();
		return gson.toJson(flightList);
	}
	
	
	@RequestMapping(value = "/util/reserve", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String reserveFlight(@RequestParam("flightID") int flightID,
								@RequestParam("userID") int userID,
								@RequestParam("firstClass") int firstClass) {
		boolean fClass = false;
		if(firstClass != 0) {
			fClass = true;
		}
		
		FlightDao fd = new FlightDaoImpl();
		EndUserDao eud = new EndUserDaoImpl();
		EndUser user = eud.getEndUserById(userID);
		Flight flight = fd.getFlightById(flightID);
		
		try {
			fd.makeReservation(user, flight, fClass);
		} catch (FullFlightException ffe) {
			return "";
		}
		
		Gson gson = new Gson();
		return gson.toJson(flight);
	}
	
	/*
	@RequestMapping(value = "/util/checkin", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String checkIn(@RequestParam("reservationID") String reservationID) {
		
	}
	*/
}
