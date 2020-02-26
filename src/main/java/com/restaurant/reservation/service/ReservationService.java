package com.restaurant.reservation.service;

import java.util.Date;
import java.util.List;

import com.restaurant.reservation.entity.Reservation;
import com.restaurant.reservation.entity.ResturantTable;

public interface ReservationService {
	
	public List<Reservation> getAllReservations();

	public Reservation reserveTable(ResturantTable table , String customerName , double paymentAmount,Date date,int numOfPersons);
	
	public List<Reservation> getReservationByDate(Date date);
}
