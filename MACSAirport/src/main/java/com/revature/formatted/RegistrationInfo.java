package com.revature.formatted;

import org.springframework.stereotype.Component;

import com.revature.dao.CommonLookupDaoImpl;
import com.revature.domain.EndUser;

@Component("registrationInfo")
public class RegistrationInfo {
	
	
	public RegistrationInfo() {
		super();
	}
	public RegistrationInfo(String firstname, String lastname, String email, String password, String type,
			String answer1, String answer2, String answer3) {
		this();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.type = type;
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.answer3 = answer3;
	}
	
	
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private String type;
	private String answer1;
	private String answer2;
	private String answer3;
	
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstName) {
		this.firstname = firstName;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastName) {
		this.lastname = lastName;
	}
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	
	public EndUser createUser() {
		CommonLookupDaoImpl cldi = new CommonLookupDaoImpl();
		EndUser user = new EndUser(this.firstname,
									this.lastname,
									this.email,
									this.password,
									cldi.getCommonLookupByName("END_USER_TYPE", this.type),
									this.answer1,
									this.answer2,
									this.answer3);
		
		return user;
	}
}
