package com.revature.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

@Component
@Entity 
@Table(name="RESERVATION")
public class Reservation implements Serializable {

	public Reservation(EndUser endUser, Flight flight, CommonLookup status, CommonLookup type) {
		super();
		this.endUser = endUser;
		this.flight = flight;
		this.status = status;
		this.type = type;
	}

	private static final long serialVersionUID = 1L;
	
	

	public Reservation() {
		super();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="reservationSequence")
	@SequenceGenerator(allocationSize=1,name="reservationSequence",sequenceName="RESERVATION_S1")
	@Column(name="RESERVATION_ID")
	private int id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="END_USER_ID")
	private EndUser endUser;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="FLIGHT_ID")
	private Flight flight;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATION_DATE")
	private Date creationDate;
	
	@OneToOne
	@JoinColumn(name="STATUS_ID")
	private CommonLookup status; // Reserved or Cancelled
	
	@OneToOne
	@JoinColumn(name="TYPE_ID")
	private CommonLookup type; // Economy or First Class
	
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

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public CommonLookup getStatus() {
		return status;
	}

	public void setStatusId(CommonLookup status) {
		this.status = status;
	}

	public CommonLookup getType() {
		return type;
	}

	public void setTypeId(CommonLookup type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Reservation [endUser=" + endUser + ", flight=" + flight + ", status=" + status + ", type=" + type + "]";
	}

	

}
