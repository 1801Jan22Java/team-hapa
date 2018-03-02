package com.revature.dao;

import com.revature.domain.Country;

public interface CountryDao {

	public Country getCountryById(int id);
	public int addCountry(Country c);
	public Country getCountryByName(String name);
}
