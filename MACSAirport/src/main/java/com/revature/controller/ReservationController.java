package com.revature.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.google.gson.Gson;
import com.revature.dao.*;
import com.revature.domain.*;

@Controller
public class ReservationController {
	
	@RequestMapping(value = "/util/checkin", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String checkIn(@RequestParam("flightID") int flightID) {
		ReservationDao rd = new ReservationDaoImpl();
		FlightDao fd = new FlightDaoImpl();
		rd.checkIn(flightID);
		
		Flight flight = fd.getFlightById(flightID);
		Gson gson = new Gson();
		return gson.toJson(flight);
	}
	
	
	@RequestMapping(value = "/util/cancel", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String cancel(@RequestParam("flightID") int flightID) {
		ReservationDao rd = new ReservationDaoImpl();
		FlightDao fd = new FlightDaoImpl();
		rd.cancel(flightID);
		
		Flight flight = fd.getFlightById(flightID);
		Gson gson = new Gson();
		return gson.toJson(flight);
	}
}
