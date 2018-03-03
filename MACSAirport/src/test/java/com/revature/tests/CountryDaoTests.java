package com.revature.tests;

import org.junit.Test;
import org.junit.Assert;

import com.revature.dao.*;
import com.revature.domain.*;

public class CountryDaoTests {

	@Test
	final public void testAddCountry() {
		CountryDao cdi = new CountryDaoImpl();
		Country c = new Country("Fakedom of lies");
		cdi.addCountry(c);
		Assert.assertEquals(cdi.getCountryByName("Fakedom of lies").getName(), c.getName());
	}
	
	@Test
	final public void testGetCountryByName() {
		// Tested by previous method
	}
	
}
