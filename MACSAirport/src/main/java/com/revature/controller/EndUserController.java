package com.revature.controller;


import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.revature.dao.*;
import com.revature.domain.*;
import com.revature.exception.FullFlightException;
import com.revature.formatted.LoginInfo;

@Controller("endUserController")
@RequestMapping("/util")
public class EndUserController {
	
	
	/*
	 * EndUser Controller Methods
	 */
	
	//Returns the EndUser who just registered their account, parsed into JSON
	@PostMapping("/register")
	@ResponseBody
	public ResponseEntity<EndUser> registerAccount(@RequestParam("firstName") String firstName,
									@RequestParam("lastName") String lastName,
									@RequestParam("email") String email,
									@RequestParam("password") String password,
									@RequestParam("type") String type,
									@RequestParam("answer1") String answer1,
									@RequestParam("answer2") String answer2,
									@RequestParam("answer3") String answer3
									) {

		CommonLookupDao cldi = new CommonLookupDaoImpl();
		EndUserDao eudi = new EndUserDaoImpl();
		CommonLookup cl1;
		if(type.equals("Passenger")) {
			cl1 = cldi.getCommonLookupByName("END_USER_TYPE", "Passenger");
		}
		else {
			cl1 = cldi.getCommonLookupByName("END_USER_TYPE", "Employee");
		}
		EndUser newUser = new EndUser(firstName, lastName, email, password, cl1,
				answer1, answer2, answer3);
		
		eudi.addEndUser(newUser);
		
		return new ResponseEntity<EndUser>(newUser, HttpStatus.OK);
	}
	
	
	
	@PostMapping("/profile")
	@ResponseBody
	public ResponseEntity<EndUser> profile(@RequestParam("userID") int userID,
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			@RequestParam("email") String email,
			@RequestParam("password") String password
			) {

		
		CommonLookupDao cldi = new CommonLookupDaoImpl();
		EndUserDao eudi = new EndUserDaoImpl();
		EndUser user = eudi.getEndUserById(userID);
		
		//Change info if fields aren't blank
		if(!firstName.equals("")) {
			user.setFirstname(firstName);
		}
		if(!lastName.equals("")) {
			user.setLastname(lastName);
		}
		if(!email.equals("")) {
			user.setEmail(email);
		}
		if(!password.equals("")) {
			user.setPassword(password);
		}
		
		eudi.updateEndUser(user);
		
		return new ResponseEntity<EndUser>(user, HttpStatus.OK);
	}
	
	
	@PostMapping("/reset")
	@ResponseBody
	public ResponseEntity<EndUser> profile(@RequestParam("email") String email,
			@RequestParam("firstAnswer") String answer1,
			@RequestParam("secondAnswer") String answer2,
			@RequestParam("thirdAnswer") String answer3,
			@RequestParam("password") String password) {

		
		CommonLookupDao cldi = new CommonLookupDaoImpl();
		EndUserDao eudi = new EndUserDaoImpl();
		EndUser user = eudi.getEndUserByEmail(email);
		
		//Change info if fields aren't blank
		if(user.getSecretAnswer1().equals(answer1) &&
				user.getSecretAnswer2().equals(answer2) &&
				user.getSecretAnswer3().equals(answer3)) {
			user.setPassword(password);
		}
		
		eudi.updateEndUser(user);


		return new ResponseEntity<EndUser>(user, HttpStatus.OK);
	}
	
	
	@RequestMapping("/login")
	@ResponseBody
	public ResponseEntity<LoginInfo> login(@RequestParam("email") String email,
						@RequestParam("password") String password) {
		EndUserDao eud = new EndUserDaoImpl();
		LoginInfo thisUser = null;
		
		EndUser toCheck = eud.getEndUserByEmail(email);
		
		if(email.equals(toCheck.getEmail()) && password.equals(toCheck.getPassword())) {
			thisUser = new LoginInfo(toCheck.getFirstname(), toCheck.getId(), toCheck.getTypeId());
			return new ResponseEntity<LoginInfo>(thisUser, HttpStatus.OK);
		}
		
		return new ResponseEntity<LoginInfo>(thisUser, HttpStatus.UNAUTHORIZED);
	}
	
	
	//Pending our decision on session management
	
	@RequestMapping("/logout")
	@ResponseBody
	public String logout() {
		return null;
	}
	
	
	@RequestMapping("/admin/nofly")
	@ResponseBody
	public ResponseEntity<EndUser> noFly(@RequestParam("userID") int userID) {
		EndUserDao eud = new EndUserDaoImpl();
		EndUser user = eud.getEndUserById(userID);
		
		user.setNoFly(true);
		eud.updateEndUser(user);


		return new ResponseEntity<EndUser>(user, HttpStatus.OK);
	}
	
	
	/*
	 * Feedback Controller Methods
	 */
	
	@PostMapping("/feedback")
	@ResponseBody
	public ResponseEntity<Feedback> addFeedback(@RequestParam("userID") int userID,
								@RequestParam("message") String message) {
		FeedbackDao fd = new FeedbackDaoImpl();
		EndUserDao eud = new EndUserDaoImpl();
		Feedback feedback = new Feedback(eud.getEndUserById(userID), message);
		fd.addFeedback(feedback);
		
		return new ResponseEntity<Feedback>(feedback, HttpStatus.OK);
	}
	
	
	// Still needs to be sorted when queried
	
	@PostMapping("/admin/feedback")
	@ResponseBody
	public ResponseEntity<List<Feedback>> getAllFeedback() {
		FeedbackDao fd = new FeedbackDaoImpl();
		List<Feedback> feedbackList = fd.getAllFeedback();

		return new ResponseEntity<List<Feedback>>(feedbackList, HttpStatus.OK);
	}
	
	
	@PostMapping("/admin/read")
	@ResponseBody
	public ResponseEntity<Feedback> deleteFeedback(@RequestParam("feedbackID") int feedbackID) {
		FeedbackDao fd = new FeedbackDaoImpl();
		Feedback feedback = fd.getFeedbackById(feedbackID);
		fd.deleteFeedback(feedback);

		//Can simply return nothing if we want to.
		return new ResponseEntity<Feedback>(feedback, HttpStatus.OK);
	}
	

	/*
	 * Flight Controller Methods
	 */
	
	@PostMapping("/flight-search")
	@ResponseBody
	public ResponseEntity<List<Flight>> flightSearch(@RequestParam("earliestDate") Date earliestDate,
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
		
		return new ResponseEntity<List<Flight>>(flightList, HttpStatus.OK);
	}
	
	
	@PostMapping("/flight-details")
	@ResponseBody
	public ResponseEntity<Flight> flightDetails(@RequestParam("flightID") int flightID) {
		FlightDao fd = new FlightDaoImpl();
		Flight flight = fd.getFlightById(flightID);
		

		return new ResponseEntity<Flight>(flight, HttpStatus.OK);
	}
	
	
	/*
	 * Returns a JSON string containing a list of 10 soonest arrivals, followed by 10 soonest departures
	 */
	@PostMapping("/all-flights")
	@ResponseBody
	public ResponseEntity<List<Flight>> allFlights() {
		FlightDao fd = new FlightDaoImpl();
		List<Flight> flightList = fd.getMostRecent10Arrivals();
		List<Flight> flightListDepartures = fd.getMostRecent10Departures();
		for(Flight f : flightListDepartures) {
			flightList.add(f);
		}
		
		return new ResponseEntity<List<Flight>>(flightList, HttpStatus.OK);
	}
	
	
	@PostMapping("/reserve")
	@ResponseBody
	public ResponseEntity<Flight> reserveFlight(@RequestParam("flightID") int flightID,
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
			//Return a key-value pair that lets front-end know that flight is full
		}
		

		return new ResponseEntity<Flight>(flight, HttpStatus.OK);
	}
	
	

	/*
	 * Reservation Controller Methods
	 */
	
	@PostMapping("/checkin")
	@ResponseBody
	public ResponseEntity<Reservation> checkIn(@RequestParam("flightID") int flightID) {
		ReservationDao rd = new ReservationDaoImpl();
		FlightDao fd = new FlightDaoImpl();
		Reservation reservation = rd.checkIn(flightID);
		
		return new ResponseEntity<Reservation>(reservation, HttpStatus.OK);
		
	}
	
	
	@PostMapping("/cancel")
	@ResponseBody
	public ResponseEntity<Reservation> cancel(@RequestParam("flightID") int flightID) {
		ReservationDao rd = new ReservationDaoImpl();
		FlightDao fd = new FlightDaoImpl();
		Reservation reservation = rd.cancel(flightID);
		
		Flight flight = fd.getFlightById(flightID);

		return new ResponseEntity<Reservation>(reservation, HttpStatus.OK);
	}
}
