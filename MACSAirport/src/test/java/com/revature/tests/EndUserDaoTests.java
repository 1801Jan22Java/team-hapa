package com.revature.tests;

import org.junit.Test;
import org.junit.Assert;

import java.util.List;

import com.revature.dao.*;
import com.revature.domain.*;

public class EndUserDaoTests {

	@Test
	final public void testAddEndUser() {
		EndUserDao eudi = new EndUserDaoImpl();
		CommonLookupDao cldi = new CommonLookupDaoImpl();
		EndUser u = new EndUser("First", "Last", "blah@blah.com", "1", cldi.getCommonLookupByName("TEST", "ALSO_TEST"), "a", "b", "c");
		eudi.addEndUser(u);
		Assert.assertEquals(eudi.getEndUserByEmail("blah@blah.com").getSecretAnswer1(), u.getSecretAnswer1());
	}
	
	@Test
	final public void testUpdateEndUser() {
		EndUserDao eudi = new EndUserDaoImpl();
		EndUser u = eudi.getEndUserByEmail("blah@blah.com");
		u.setFirstname("Johnny");
		eudi.updateEndUser(u);
		u = eudi.getEndUserByEmail("blah@blah.com");
		Assert.assertEquals(u.getFirstname(), "Johnny");
		u.setFirstname("First");
		eudi.updateEndUser(u);
	}
	
	@Test
	final public void testGetReservationHistory() {
		EndUserDao eudi = new EndUserDaoImpl();
		//ReservationDao rdi = new ReservationDaoImpl();
		//CommonLookupDao cldi = new CommonLookupDaoImpl();
		//FlightDao fdi = new FlightDaoImpl();
		//Flight f = fdi.getFlightById(7);
		EndUser u = eudi.getEndUserByEmail("blah@blah.com");
		//Reservation thisReservation = new Reservation(u, f, cldi.getCommonLookupByName("RESERVATION_STATUS", "Reserved"), cldi.getCommonLookupByName("RESERVATION_TYPE", "Economy"));
		//rdi.addReservation(thisReservation);
		
		List<Reservation> reservations = eudi.getReservationHistory(u);
		System.out.println(reservations);
		boolean resultant = false;
		for (Reservation r : reservations) {
			resultant |= r.getEndUser().getFirstname().equals("First");
		}
		Assert.assertTrue(resultant);
	}
	
}
