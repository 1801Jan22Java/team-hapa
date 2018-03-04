package com.revature.formatted;

import org.springframework.stereotype.Component;

@Component("profileChange")
public class ProfileChange {
	
	public ProfileChange() {
		super();
	}

	public ProfileChange(String firstname, String lastname, String email, String newpassword, String answer1,
			String answer2, String answer3) {
		this();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.newpassword = newpassword;
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.answer3 = answer3;
	}
	
	private String firstname;
	private String lastname;
	private String email;
	private String newpassword;
	private String answer1;
	private String answer2;
	private String answer3;
	
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNewpassword() {
		return newpassword;
	}
	public void setNewpassword(String password) {
		this.newpassword = password;
	}
	public String getAnswer1() {
		return answer1;
	}
	public void setAnswer1(String secretAnswer1) {
		this.answer1 = secretAnswer1;
	}
	public String getAnswer2() {
		return answer2;
	}
	public void setAnswer2(String secretAnswer2) {
		this.answer2 = secretAnswer2;
	}
	public String getAnswer3() {
		return answer3;
	}
	public void setAnswer3(String secretAnswer3) {
		this.answer3 = secretAnswer3;
	}
}
