package com.revature.dao;

import java.util.List;

import com.revature.domain.City;
import com.revature.domain.Country;
import com.revature.domain.State;

public interface CityDao {

	public City getCityById(int id);
	public int addCity(City c);
	public double distanceBetween(City departure, City arrival);
	public City getCityByName(String cityName, State thisState, Country thisCountry);
	public List<City> getAllCities();
	City getIntlCityByName(String cityName, Country thisCountry);
	City getCityByOnlyName(String cityName);
}
