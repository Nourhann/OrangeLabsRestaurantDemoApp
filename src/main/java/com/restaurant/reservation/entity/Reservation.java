package com.restaurant.reservation.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="RESRVATIONS")
public class Reservation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="RESERVATION_ID")
	private int id;
	
	@Column(name="CUSTOMER_NAME")
	private String customerName;
	
	@Column(name="PAYMENT")
	private double paymentAmount;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name="RESERVATION_DATE")
	private Date reservationDate;
	
	@Column(name="NUM_OF_PERSONS")
	private int numOfPersons;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="TABLE_ID")
	private ResturantTable table;

	
	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reservation(String customerName, double paymentAmount, Date reservationDate, int numOfPersons,
			ResturantTable table) {
		super();
		this.customerName = customerName;
		this.paymentAmount = paymentAmount;
		this.reservationDate = reservationDate;
		this.numOfPersons = numOfPersons;
		this.table = table;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}

	public ResturantTable getTable() {
		return table;
	}

	public void setTable(ResturantTable table) {
		this.table = table;
	}

	public int getNumOfPersons() {
		return numOfPersons;
	}

	public void setNumOfPersons(int numOfPersons) {
		this.numOfPersons = numOfPersons;
	}
	
	
}
