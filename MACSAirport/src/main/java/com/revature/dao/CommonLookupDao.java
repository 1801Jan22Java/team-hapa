package com.revature.dao;

import com.revature.domain.CommonLookup;

public interface CommonLookupDao {
	public CommonLookup getCommonLookupById(int id);
	public CommonLookup getCommonLookupByName(String refKey, String refValue);
	public int addCommonLookup(CommonLookup thisCommonLookup);
}
