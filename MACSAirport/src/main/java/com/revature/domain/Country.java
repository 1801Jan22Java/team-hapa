package com.revature.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity 
@Table(name="COUNTRY")
public class Country implements Serializable {

	private static final long serialVersionUID = 1L;

	public Country(String name) {
		super();
		this.name = name;
	}

	public Country() {
		super();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="countrySequence")
	@SequenceGenerator(allocationSize=1,name="countrySequence",sequenceName="COUNTRY_S1")
	@Column(name="COUNTRY_ID")
	private int id;
	
	@Column(name="COUNTRY_NAME", unique = true)
	private String name;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "State [id=" + id + ", name=" + name + "]";
	}
}
