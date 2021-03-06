package com.restaurant.reservation.dao;

import com.restaurant.reservation.entity.Reservation;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
	
	@Query("SELECT r FROM Reservation r WHERE r.reservationDate =:date ")
	public List<Reservation> getReservationByDate(@Param("date") Date date);

}
