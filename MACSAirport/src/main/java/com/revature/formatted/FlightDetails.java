package com.revature.formatted;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.revature.domain.City;
import com.revature.domain.CommonLookup;
import com.revature.domain.EndUser;
import com.revature.domain.Flight;
import com.revature.domain.Reservation;

public class FlightDetails {

	
	private Flight flight;
	private CommonLookup status;	//refValue = Reserved or Cancelled
									//null = default (not reserved)
	
}
