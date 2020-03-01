package com.restaurant.reservation.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TABLES")
public class ResturantTable implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TABLE_ID")
	private int id;
	
	@Column(name="TABLE_CAPACITY")
	private int capacity;

	public ResturantTable() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResturantTable(int capacity) {
		super();
		this.capacity = capacity;
	}

	public ResturantTable(int id, int capacity) {
		super();
		this.id = id;
		this.capacity = capacity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	
	
}
