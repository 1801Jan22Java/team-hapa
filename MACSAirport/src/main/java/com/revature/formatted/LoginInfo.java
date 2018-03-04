package com.revature.formatted;

import org.springframework.stereotype.Component;

import com.revature.domain.CommonLookup;

@Component("loginInfo")
public class LoginInfo {
	public LoginInfo() {
		super();
	}
	public LoginInfo(String firstName, int userID, CommonLookup type) {
		this();
		this.firstName = firstName;
		this.userID = userID;
		this.type = type;
	}
	
	
	private String firstName;
	private int userID;
	private CommonLookup type;
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public CommonLookup getType() {
		return type;
	}
	public void setType(CommonLookup type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "LoginInfo [firstName=" + firstName + ", userID=" + userID + ", type=" + type + "]";
	}
}
