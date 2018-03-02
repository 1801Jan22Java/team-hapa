package com.revature.dao;

import com.revature.domain.State;

public interface StateDao {

	public State getStateById(int id);
	public int addState(State s);
	public State getStateByName(String name);
}
