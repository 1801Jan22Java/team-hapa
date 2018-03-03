package com.revature.tests;

import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.revature.dao.*;
import com.revature.domain.*;
import com.revature.exception.FullFlightException;

public class FlightDaoTests {
	
	@Test
	final public void testAddFlight() {
		// Tested by other functions
	}
	
	//@Test
	final public void testSearchFlight() {
		FlightDao fd = new FlightDaoImpl();
		StateDao sd = new StateDaoImpl();
		CityDao cd = new CityDaoImpl();
		CountryDao cod = new CountryDaoImpl();
		CommonLookupDao cld = new CommonLookupDaoImpl();
		
		City arrivalCity = null;
		State arrivalState = null;
		Country arrivalCountry = null;
		
		arrivalState = sd.getStateByName("California");
		arrivalCountry = cod.getCountryByName("United States");
		arrivalCity = cd.getCityByName("Los Angeles", arrivalState, arrivalCountry);
		System.out.println(arrivalCity.toString());
		
		CommonLookup cl1 = cld.getCommonLookupByName("FLIGHT_TYPE", "Departure");
		
		Flight f1 = new Flight(3, new Date(System.currentTimeMillis() + 100000), 105.85, 180, cl1, arrivalCity);
		f1.setId(fd.addFlight(f1));
		Flight f2 = new Flight(3, new Date(System.currentTimeMillis() - 100000), 105.85, 180, cl1, arrivalCity);
		f2.setId(fd.addFlight(f2));
		
		boolean containsF1 = false;
		boolean doesNotContainF2 = true;
		for (Flight f : fd.searchFlight(new Date(), arrivalCity)) {
			containsF1 |= f.getId() == f1.getId();
			doesNotContainF2 &= f.getId() != f2.getId();
		}
		Assert.assertTrue(containsF1);
		Assert.assertTrue(doesNotContainF2);
	}
	
	//@Test
	final public void testMakeReservation() {
		FlightDao fd = new FlightDaoImpl();
		StateDao sd = new StateDaoImpl();
		CityDao cd = new CityDaoImpl();
		CountryDao cod = new CountryDaoImpl();
		CommonLookupDao cld = new CommonLookupDaoImpl();
		EndUserDao eud = new EndUserDaoImpl();
		
		City arrivalCity = null;
		State arrivalState = null;
		Country arrivalCountry = null;
		
		arrivalState = sd.getStateByName("California");
		arrivalCountry = cod.getCountryByName("United States");
		arrivalCity = cd.getCityByName("Los Angeles", arrivalState, arrivalCountry);
		System.out.println(arrivalCity.toString());
		
		CommonLookup cl1 = cld.getCommonLookupByName("FLIGHT_TYPE", "Departure");
		
		Flight f1 = new Flight(3, new Date(System.currentTimeMillis() + 100000), 105.85, 180, cl1, arrivalCity);
		f1.setId(fd.addFlight(f1));
		
		// This dude will be buying all the seats on the plane
		EndUser u = eud.getEndUserByEmail("blah@blah.com");
		for (int i = 0; i < 10; i++) {
			try {
				fd.makeReservation(u, f1, false);
			} catch (FullFlightException e) {
				Assert.assertTrue(false);
			}
		}
		try {
			fd.makeReservation(u, f1, false);
			Assert.assertTrue(false);
		} catch (FullFlightException e) {
			Assert.assertTrue(true);
		}
		for (int i = 0; i < 2; i++) {
			try {
				fd.makeReservation(u, f1, true);
			} catch (FullFlightException e) {
				Assert.assertTrue(false);
			}
		}
		try {
			fd.makeReservation(u, f1, true);
			Assert.assertTrue(false);
		} catch (FullFlightException e) {
			Assert.assertTrue(true);
		}
	}
	
	//@Test
	final public void testCancelReservation() {
		FlightDao fd = new FlightDaoImpl();
		StateDao sd = new StateDaoImpl();
		CityDao cd = new CityDaoImpl();
		CountryDao cod = new CountryDaoImpl();
		CommonLookupDao cld = new CommonLookupDaoImpl();
		EndUserDao eud = new EndUserDaoImpl();
		
		City arrivalCity = null;
		State arrivalState = null;
		Country arrivalCountry = null;
		
		arrivalState = sd.getStateByName("California");
		arrivalCountry = cod.getCountryByName("United States");
		arrivalCity = cd.getCityByName("Los Angeles", arrivalState, arrivalCountry);
		System.out.println(arrivalCity.toString());
		
		CommonLookup cl1 = cld.getCommonLookupByName("FLIGHT_TYPE", "Departure");
		
		Flight f1 = new Flight(3, new Date(System.currentTimeMillis() + 100000), 105.85, 180, cl1, arrivalCity);
		f1.setId(fd.addFlight(f1));
		
		EndUser u = eud.getEndUserByEmail("blah@blah.com");
		
		try {
			fd.makeReservation(u, f1, false);
		} catch (FullFlightException e) {
			Assert.assertTrue(false);
		}
		
		fd.cancelReservation(u, f1);
		boolean resultant = true;
		for (Reservation r : eud.getReservationHistory(u)) {
			Flight f = r.getFlight();
			
			if (f != null && f.getId() == f1.getId()) {
				resultant &= r.getStatus().getRefValue().equals("Cancelled");
			}
			
		}
		Assert.assertTrue(resultant);
	}
	
	//@Test
	final public void testgetMostRecent10Departures() {
		FlightDao fd = new FlightDaoImpl();
		StateDao sd = new StateDaoImpl();
		CityDao cd = new CityDaoImpl();
		CountryDao cod = new CountryDaoImpl();
		CommonLookupDao cld = new CommonLookupDaoImpl();
		
		City arrivalCity = null;
		State arrivalState = null;
		Country arrivalCountry = null;
		
		arrivalState = sd.getStateByName("California");
		arrivalCountry = cod.getCountryByName("United States");
		arrivalCity = cd.getCityByName("Los Angeles", arrivalState, arrivalCountry);
		System.out.println(arrivalCity.toString());
		
		CommonLookup cl1 = cld.getCommonLookupByName("FLIGHT_TYPE", "Departure");
		
		List<Integer> ids = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			ids.add(fd.addFlight(new Flight(3, new Date(System.currentTimeMillis() + 10000), 105.85, 180, cl1, arrivalCity)));
		}
		List<Integer> testIds = new ArrayList<>();
		List<Flight> fs = fd.getMostRecent10Departures();
		for (Flight f : fs) {
			testIds.add(f.getId());
		}
		Collections.sort(ids);
		Collections.sort(testIds);
		
		for (int i = 0; i < 10; i++) {
			Assert.assertEquals(testIds.get(i), ids.get(i));
		}
	}
	
	@Test
	final public void testgetMostRecent10Arrivals() {
		FlightDao fd = new FlightDaoImpl();
		StateDao sd = new StateDaoImpl();
		CityDao cd = new CityDaoImpl();
		CountryDao cod = new CountryDaoImpl();
		CommonLookupDao cld = new CommonLookupDaoImpl();
		
		City arrivalCity = null;
		State arrivalState = null;
		Country arrivalCountry = null;
		
		arrivalState = sd.getStateByName("California");
		arrivalCountry = cod.getCountryByName("United States");
		arrivalCity = cd.getCityByName("Los Angeles", arrivalState, arrivalCountry);
		System.out.println(arrivalCity.toString());
		
		CommonLookup cl1 = cld.getCommonLookupByName("FLIGHT_TYPE", "Arrival");
		
		List<Integer> ids = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			ids.add(fd.addFlight(new Flight(3, new Date(System.currentTimeMillis() - 10000), 105.85, 180, cl1, arrivalCity)));
		}
		List<Integer> testIds = new ArrayList<>();
		List<Flight> fs = fd.getMostRecent10Arrivals();
		for (Flight f : fs) {
			testIds.add(f.getId());
		}
		Collections.sort(ids);
		Collections.sort(testIds);
		
		for (int i = 0; i < 10; i++) {
			Assert.assertEquals(testIds.get(i), ids.get(i));
		}
	}
	
}
