package com.revature.domain;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

@Entity 
@Table(name="END_USER")
public class EndUser implements Serializable {

	public EndUser(String firstname, String lastname, String email, String password, CommonLookup type,
			String secretAnswer1, String secretAnswer2, String secretAnswer3) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.type = type;
		this.secretAnswer1 = secretAnswer1;
		this.secretAnswer2 = secretAnswer2;
		this.secretAnswer3 = secretAnswer3;
	}

	private static final long serialVersionUID = 1L;
	
	

	public EndUser() {
		super();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="endUserSequence")
	@SequenceGenerator(allocationSize=1,name="endUserSequence",sequenceName="END_USER_S1")
	@Column(name="END_USER_ID")
	private int id;
	
	@Column(name="FIRSTNAME")
	private String firstname;
	
	@Column(name="LASTNAME")
	private String lastname;
	
	@Column(name="EMAIL", unique = true)
	private String email;
	
	@Column(name="PASSWORD")
	private String password;
	
	@OneToOne
	@JoinColumn(name="TYPE_ID")
	private CommonLookup type; // Employee or Passenger
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATION_DATE")
	private Date creationDate;
	
	@Column(name="SECRET_ANSWER_1")
	private String secretAnswer1;
	
	@Column(name="SECRET_ANSWER_2")
	private String secretAnswer2;
	
	@Column(name="SECRET_ANSWER_3")
	private String secretAnswer3;
	
	@Column(name="NO_FLY")
	private boolean noFly;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public CommonLookup getTypeId() {
		return type;
	}

	public void setTypeId(CommonLookup type) {
		this.type = type;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getSecretAnswer1() {
		return secretAnswer1;
	}

	public void setSecretAnswer1(String secretAnswer1) {
		this.secretAnswer1 = secretAnswer1;
	}

	public String getSecretAnswer2() {
		return secretAnswer2;
	}

	public void setSecretAnswer2(String secretAnswer2) {
		this.secretAnswer2 = secretAnswer2;
	}

	public String getSecretAnswer3() {
		return secretAnswer3;
	}

	public void setSecretAnswer3(String secretAnswer3) {
		this.secretAnswer3 = secretAnswer3;
	}

	public boolean isNoFly() {
		return noFly;
	}

	public void setNoFly(boolean noFly) {
		this.noFly = noFly;
	}

	@Override
	public String toString() {
		return "EndUser [firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ", password="
				+ password + ", type=" + type + ", secretAnswer1=" + secretAnswer1 + ", secretAnswer2=" + secretAnswer2
				+ ", secretAnswer3=" + secretAnswer3 + ", noFly=" + noFly + "]";
	}

}
