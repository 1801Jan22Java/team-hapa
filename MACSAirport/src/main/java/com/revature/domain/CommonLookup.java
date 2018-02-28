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
@Table(name="COMMON_LOOKUP")
public class CommonLookup implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public CommonLookup(String refKey, String refValue) {
		super();
		this.refKey = refKey;
		this.refValue = refValue;
	}

	public CommonLookup() {
		super();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="commonLookupSequence")
	@SequenceGenerator(allocationSize=1,name="commonLookupSequence",sequenceName="COMMON_LOOKUP_S1")
	@Column(name="COMMON_LOOKUP_ID")
	private int id;
	
	@Column(name="REF_KEY")
	private String refKey;
	
	@Column(name="REF_VALUE")
	private String refValue;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRefKey() {
		return refKey;
	}

	public void setRefKey(String refKey) {
		this.refKey = refKey;
	}
	
	public String getRefValue() {
		return refValue;
	}

	public void setRefValue(String refValue) {
		this.refValue = refValue;
	}

	@Override
	public String toString() {
		return "CommonLookup [id=" + id + ", refKey=" + refKey + ", refValue=" + refValue + "]";
	}


}
