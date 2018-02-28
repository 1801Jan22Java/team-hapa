package com.revature.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity 
@Table(name="FLIGHT")
public class Flight implements Serializable {

	public Flight(int gate, Date time, double cost, int duration, CommonLookup type, City city) {
		super();
		this.gate = gate;
		this.time = time;
		this.cost = cost;
		this.duration = duration;
		this.type = type;
		this.city = city;
	}

	private static final long serialVersionUID = 1L;
	
	

	public Flight() {
		super();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="flightSequence")
	@SequenceGenerator(allocationSize=1,name="flightSequence",sequenceName="FLIGHT_S1")
	@Column(name="FLIGHT_ID")
	private int id;
	
	@Column(name="GATE")
	private int gate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="TIME")
	private Date time; // arrival time or departure time
	
	@Column(name="COST")
	private double cost; // calculated from distance
	
	@Column(name="DURATION")
	private int duration; // seconds calculated from distance
	
	@OneToOne
	@JoinColumn(name="TYPE_ID")
	private CommonLookup type; // arrival or departure
	
	@OneToOne
	@JoinColumn(name="CITY_ID")
	private City city; // City departing to or arriving from
	
	@OneToMany(mappedBy="flight",fetch=FetchType.EAGER)
	private List<Reservation> reservations;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGate() {
		return gate;
	}

	public void setGate(int gate) {
		this.gate = gate;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public CommonLookup getTypeId() {
		return type;
	}

	public void setTypeId(CommonLookup type) {
		this.type = type;
	}

	public City getUserId() {
		return city;
	}

	public void setUserId(City city) {
		this.city = city;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public CommonLookup getType() {
		return type;
	}

	public void setType(CommonLookup type) {
		this.type = type;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Flight [gate=" + gate + ", time=" + time + ", cost=" + cost + ", duration=" + duration + ", type="
				+ type + ", city=" + city + "]";
	}

	

}
