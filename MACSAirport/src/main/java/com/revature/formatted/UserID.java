package com.revature.formatted;

import org.springframework.stereotype.Component;

@Component("userID")
public class UserID {
	public UserID() {
		super();
	}

	public UserID(int id) {
		this();
		this.id = id;
	}

	
	private int id;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
