package com.revature.dao;

import java.util.List;

<<<<<<< HEAD
import com.revature.domain.*;

public interface EndUserDao {
	public EndUser getEndUserById(int id);
	public EndUser getEndUserByEmail(String email);
	public List<EndUser> getAllEndUsers();
	public int addEndUser(EndUser thisEndUser);
	public void updateEndUser(EndUser u);
	public List<Reservation> getReservationHistory(EndUser user);
=======
import com.revature.domain.EndUser;

public interface EndUserDao {
	
	public void createEndUser(EndUser u);
	
	public void updateEndUser(EndUser u);

	public EndUser readUserById(int endUserID);
	
	public List<EndUser> viewAllEndUsers();
>>>>>>> 985d324dec09397d93546b147d2511f320e47857
}
