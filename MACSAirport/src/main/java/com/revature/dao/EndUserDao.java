package com.revature.dao;

import java.util.List;

import com.revature.domain.EndUser;

public interface EndUserDao {
	
	public void createEndUser(EndUser u);
	
	public void updateEndUser(EndUser u);

	public EndUser readUserById(int endUserID);
	
	public List<EndUser> viewAllEndUsers();
}
