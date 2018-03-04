package com.revature.tests;

import org.junit.Assert;
import org.junit.Test;

import com.revature.dao.*;
import com.revature.domain.*;

public class CommonLookupDaoTests {

	//@Test
	final public void testGetCommonLookupByName() {
		CommonLookupDao cldi = new CommonLookupDaoImpl();
		CommonLookup cl = cldi.getCommonLookupByName("FLIGHT_TYPE", "Arrival");
		Assert.assertEquals(cl.getRefKey(), "FLIGHT_TYPE");
		Assert.assertEquals(cl.getRefValue(), "Arrival");
	}
	
	@Test
	final public void testAddCommonLookup() {
		CommonLookupDao cldi = new CommonLookupDaoImpl();
		CommonLookup cl = new CommonLookup("TEST", "ALSO_TEST");
		cldi.addCommonLookup(cl);
		CommonLookup test = cldi.getCommonLookupByName("TEST", "ALSO_TEST");
		Assert.assertEquals(test.getRefKey(), "TEST");
		Assert.assertEquals(test.getRefValue(), "ALSO_TEST");
	}
	
}
