package com.revature.dao;

import com.revature.domain.EndUser;

public interface EndUserDao {
	public EndUser getEndUserById(int id);
	public EndUser getEndUserByEmail(String email);
	public List<EndUser> getAllEndUsers();
	public int addEndUser(EndUser thisEndUser);
	public void updateEndUser(EndUser u);
	
}
