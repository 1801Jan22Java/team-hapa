package com.revature.controller;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.dao.CityDao;
import com.revature.dao.CityDaoImpl;
import com.revature.dao.CommonLookupDaoImpl;
import com.revature.dao.CountryDao;
import com.revature.dao.CountryDaoImpl;
import com.revature.dao.EndUserDao;
import com.revature.dao.EndUserDaoImpl;
import com.revature.dao.FeedbackDao;
import com.revature.dao.FeedbackDaoImpl;
import com.revature.dao.FlightDaoImpl;
import com.revature.dao.ReservationDao;
import com.revature.dao.ReservationDaoImpl;
import com.revature.dao.StateDao;
import com.revature.dao.StateDaoImpl;
import com.revature.domain.City;
import com.revature.domain.CommonLookup;
import com.revature.domain.Country;
import com.revature.domain.EndUser;
import com.revature.domain.Feedback;
import com.revature.domain.Flight;
import com.revature.domain.Reservation;
import com.revature.domain.State;
import com.revature.exception.FullFlightException;
import com.revature.formatted.LoginInfo;

@Controller("endUserController")
@RequestMapping("/util")
public class EndUserController {
	
	@Autowired
	CommonLookupDaoImpl cldi;
	
	@Autowired
	EndUserDao eudi;
	
	@Autowired
	FeedbackDao fd;
	
	@Autowired
	FlightDaoImpl fdi;
	
	@Autowired
	ReservationDao rdi;
	
	/*
	 * EndUser Controller Methods
	 */
	
	//Returns the EndUser who just registered their account, parsed into JSON
	@PostMapping("/register")
	@ResponseBody
	public ResponseEntity<LoginInfo> registerAccount(@RequestParam("firstName") String firstName,
									@RequestParam("lastName") String lastName,
									@RequestParam("email") String email,
									@RequestParam("password") String password,
									@RequestParam("type") String type,
									@RequestParam("answer1") String answer1,
									@RequestParam("answer2") String answer2,
									@RequestParam("answer3") String answer3
									) {

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
		
		return new ResponseEntity<LoginInfo>(newUser.convertToLoginInfo(), HttpStatus.OK);
	}
	
	
	
	@PostMapping("/profile")
	@ResponseBody
	public ResponseEntity<EndUser> profile(@RequestParam("userID") int userID,
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			@RequestParam("email") String email,
			@RequestParam("password") String password
			) {

		
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
		LoginInfo thisUser = null;
		
		EndUser toCheck = eudi.getEndUserByEmail(email);
		
		if(email.equals(toCheck.getEmail()) && password.equals(toCheck.getPassword())) {
			thisUser = toCheck.convertToLoginInfo();
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
		EndUser user = eudi.getEndUserById(userID);
		
		user.setNoFly(true);
		eudi.updateEndUser(user);


		return new ResponseEntity<EndUser>(user, HttpStatus.OK);
	}
	
	
	/*
	 * Feedback Controller Methods
	 */
	
	@PostMapping("/feedback")
	@ResponseBody
	public ResponseEntity<Feedback> addFeedback(@RequestParam("userID") int userID,
								@RequestParam("message") String message) {
		Feedback feedback = new Feedback(eudi.getEndUserById(userID), message);
		fd.addFeedback(feedback);
		
		return new ResponseEntity<Feedback>(feedback, HttpStatus.OK);
	}
	
	
	// Still needs to be sorted when queried
	
	@PostMapping("/admin/feedback")
	@ResponseBody
	public ResponseEntity<List<Feedback>> getAllFeedback() {
		List<Feedback> feedbackList = fd.getAllFeedback();

		return new ResponseEntity<List<Feedback>>(feedbackList, HttpStatus.OK);
	}
	
	
	@PostMapping("/admin/read")
	@ResponseBody
	public ResponseEntity<Feedback> deleteFeedback(@RequestParam("feedbackID") int feedbackID) {
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

		StateDao sd = new StateDaoImpl();
		CityDao cd = new CityDaoImpl();
		CountryDao cod = new CountryDaoImpl();
		List<Flight> flightList = null;


		if(co.equals("United States")) {
			State state = sd.getStateByName(s);
			Country country = cod.getCountryByName(co);
			City city = cd.getCityByName(ci, state, country);
			flightList = fdi.searchFlight(earliestDate, city);
		} else {
			Country country = cod.getCountryByName(co);
			City city = cd.getIntlCityByName(ci, country);
			flightList = fdi.searchFlight(earliestDate, city);
		}
		
		return new ResponseEntity<List<Flight>>(flightList, HttpStatus.OK);
	}
	
	
	@PostMapping("/flight-details")
	@ResponseBody
	public ResponseEntity<Flight> flightDetails(@RequestParam("flightID") int flightID) {
		Flight flight = fdi.getFlightById(flightID);
		

		return new ResponseEntity<Flight>(flight, HttpStatus.OK);
	}
	
	
	/*
	 * Returns a JSON string containing a list of 10 soonest arrivals, followed by 10 soonest departures
	 */
	@PostMapping("/all-flights")
	@ResponseBody
	public ResponseEntity<List<Flight>> allFlights() {
		List<Flight> flightList = fdi.getMostRecent10Arrivals();
		List<Flight> flightListDepartures = fdi.getMostRecent10Departures();
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
		
		EndUser user = eudi.getEndUserById(userID);
		Flight flight = fdi.getFlightById(flightID);
		
		try {
			fdi.makeReservation(user, flight, fClass);
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
		Reservation reservation = rdi.checkIn(flightID);
		
		return new ResponseEntity<Reservation>(reservation, HttpStatus.OK);
		
	}
	
	
	@PostMapping("/cancel")
	@ResponseBody
	public ResponseEntity<Reservation> cancel(@RequestParam("flightID") int flightID) {
		Reservation reservation = rdi.cancel(flightID);

		return new ResponseEntity<Reservation>(reservation, HttpStatus.OK);
	}
}
