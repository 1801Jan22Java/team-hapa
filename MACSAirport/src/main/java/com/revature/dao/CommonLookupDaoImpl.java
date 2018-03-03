package com.revature.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.revature.domain.CommonLookup;
import com.revature.util.HibernateUtil;

@Repository("commonLookupDaoImpl")
public class CommonLookupDaoImpl implements CommonLookupDao {

	@Override
	public CommonLookup getCommonLookupById(int id) {
		Session s = HibernateUtil.getSession();
		CommonLookup thisCommonLookup = (CommonLookup) s.get(CommonLookup.class, id);
		s.close();
		return thisCommonLookup;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public CommonLookup getCommonLookupByName(String refKey, String refValue) {
		Session s = HibernateUtil.getSession();
		CommonLookup thisCommonLookup = null;
		try {
			Criteria c = s.createCriteria(CommonLookup.class);
			c.add(Restrictions.eq("refKey", refKey));
			c.add(Restrictions.eq("refValue", refValue));
			List<CommonLookup> commonLookups = c.list();
			thisCommonLookup = commonLookups.get(0);
		} catch (Exception e) {
			// This state could not be found
			thisCommonLookup = null;
		}
		s.close();
		return thisCommonLookup;
	}

	@Override
	public int addCommonLookup(CommonLookup thisCommonLookup) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		int result = (int) s.save(thisCommonLookup);
		try {
			tx.commit();
		} catch (Exception e) {
			result = 0;
			tx.rollback();
		}
		return result;
	}

}
