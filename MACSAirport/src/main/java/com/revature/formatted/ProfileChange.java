package com.revature.formatted;


public class ProfileChange {
	
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
