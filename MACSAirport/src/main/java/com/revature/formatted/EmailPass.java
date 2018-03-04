package com.revature.formatted;

import org.springframework.stereotype.Component;

@Component("emailPass")
public class EmailPass {

	public EmailPass() {}
	
	public EmailPass(String email, String password) {
		this();
		this.email = email;
		this.password = password;
	}
	
	private String email;
	private String password;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
