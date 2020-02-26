package com.restaurant.reservation.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.reservation.dao.ReservationRepository;
import com.restaurant.reservation.entity.Reservation;
import com.restaurant.reservation.entity.ResturantTable;

@Service
public class ReservationServiceImp implements ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;

	@Override
	public List<Reservation> getAllReservations() {
		return reservationRepository.findAll();
	}

	@Override
	public Reservation reserveTable(ResturantTable table, String customerName, double paymentAmount, Date date,
			int numOfPersons) {
		Reservation reservation = new Reservation();
		reservation.setCustomerName(customerName);
		reservation.setPaymentAmount(paymentAmount);
		reservation.setTable(table);
		reservation.setReservationDate(date);
		reservation.setNumOfPersons(numOfPersons);
		return reservationRepository.save(reservation);
	}

	@Override
	public List<Reservation> getReservationByDate(Date date) {
		return reservationRepository.getReservationByDate(date);
	}

}
