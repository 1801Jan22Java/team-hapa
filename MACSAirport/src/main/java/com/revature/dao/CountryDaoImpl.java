package com.revature.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.revature.domain.Country;
import com.revature.util.HibernateUtil;

@Repository("countryDaoImpl")
public class CountryDaoImpl implements CountryDao {

	@Override
	public Country getCountryById(int id) {
		Session s = HibernateUtil.getSession();
		Country thisCountry = (Country) s.get(Country.class, id);
		s.close();
		return thisCountry;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Country getCountryByName(String name) {
		
		Session s = HibernateUtil.getSession();
		Country thisCountry = null;
		try {
			Criteria c = s.createCriteria(Country.class);
			c.add(Restrictions.eq("name", name));
			List<Country> countries = c.list();
			thisCountry = countries.get(0);
			System.out.println(thisCountry.toString());
		} catch (Exception e) {
			// This state could not be found
			thisCountry = null;
		}
		s.close();
		return thisCountry;
	}

	@Override
	public int addCountry(Country thisCountry) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		int result = (int) s.save(thisCountry);
		try {
			tx.commit();
		} catch (Exception e) {
			result = 0;
			tx.rollback();
		}
		return result;
	}
}
