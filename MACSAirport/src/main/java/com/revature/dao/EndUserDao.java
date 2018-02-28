package com.revature.dao;

import java.util.List;

import com.revature.domain.*;

public interface EndUserDao {
	public EndUser getEndUserById(int id);
	public EndUser getEndUserByEmail(String email);
	public List<EndUser> getAllEndUsers();
	public int addEndUser(EndUser thisEndUser);
	public void updateEndUser(EndUser u);
	public List<Reservation> getReservationHistory(EndUser user);
}
