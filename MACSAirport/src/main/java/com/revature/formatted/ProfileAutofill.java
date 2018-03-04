package com.revature.formatted;

import org.springframework.stereotype.Component;

@Component("profileAutofill")
public class ProfileAutofill {
	public ProfileAutofill() {
		super();
	}

	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
