package com.revature.tests;

import org.junit.Test;
import org.junit.Assert;

import com.revature.dao.*;
import com.revature.domain.*;

public class CityDaoTests {
	
	@Test
	final public void testAddCity() {
		CityDao cdi = new CityDaoImpl();
		StateDao sd = new StateDaoImpl();
		CountryDao cod = new CountryDaoImpl();
		State Iowa = sd.getStateByName("Iowa");
		Country US = cod.getCountryByName("United States");
		City c = new City("Fakeville", 10, 10, 10, Iowa, US);
		cdi.addCity(c);
		Assert.assertEquals("Fakeville", cdi.getCityByName("Fakeville", Iowa, US).getName());
	}
	
	@Test
	final public void testDistanceBetween() {
		CityDao cdi = new CityDaoImpl();
		City London = cdi.getCityById(221);
		City Paris = cdi.getCityById(86);
		double dist = cdi.distanceBetween(London, Paris);
		Assert.assertTrue(dist < 215);
		Assert.assertTrue(dist > 214);
	}
	
	@Test
	final public void testGetCityByName() {
		CityDao cdi = new CityDaoImpl();
		StateDao sd = new StateDaoImpl();
		CountryDao cod = new CountryDaoImpl();
		State Il = sd.getStateByName("Illinois");
		Country US = cod.getCountryByName("United States");
		Assert.assertEquals("Chicago", cdi.getCityByName("Chicago", Il, US).getName());
		Country UK = cod.getCountryByName("United Kingdom");
		Assert.assertEquals("London", cdi.getIntlCityByName("London", UK).getName());
	}
	
}
