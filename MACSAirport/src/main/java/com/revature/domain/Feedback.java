package com.revature.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

@Entity 
@Table(name="FEEDBACK")
public class Feedback implements Serializable {

	public Feedback(EndUser endUser, String message) {
		super();
		this.endUser = endUser;
		this.message = message;
	}

	private static final long serialVersionUID = 1L;
	
	

	public Feedback() {
		super();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="feedbackSequence")
	@SequenceGenerator(allocationSize=1,name="feedbackSequence",sequenceName="FEEDBACK_S1")
	@Column(name="FEEDBACK_ID")
	private int id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="END_USER_ID")
	private EndUser endUser;
	
	@Lob
	@Column(name="MESSAGE")
	private String message;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATION_DATE")
	private Date creationDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public EndUser getEndUser() {
		return endUser;
	}

	public void setEndUser(EndUser endUser) {
		this.endUser = endUser;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Feedback [endUser=" + endUser + ", message=" + message + "]";
	}

	
}
