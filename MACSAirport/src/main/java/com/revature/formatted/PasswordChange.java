package com.revature.formatted;


public class PasswordChange {
	
	public PasswordChange(int id, String answer1, String answer2, String answer3, String newpassword) {
		super();
		this.id = id;
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.answer3 = answer3;
		this.newpassword = newpassword;
	}
	
	
	private int id;
	private String answer1;
	private String answer2;
	private String answer3;
	private String newpassword;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAnswer1() {
		return answer1;
	}
	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}
	public String getAnswer2() {
		return answer2;
	}
	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}
	public String getAnswer3() {
		return answer3;
	}
	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}
	public String getNewpassword() {
		return newpassword;
	}
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
}
