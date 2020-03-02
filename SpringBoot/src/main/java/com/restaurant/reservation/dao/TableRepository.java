package com.restaurant.reservation.dao;

import com.restaurant.reservation.entity.ResturantTable;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends JpaRepository<ResturantTable, Integer> {

	@Query("SELECT t FROM ResturantTable t WHERE t.id NOT IN ("
			+ "SELECT r.table.id FROM Reservation r WHERE r.reservationDate =:date )")
	public List<ResturantTable> getAvailableTables(@Param("date") Date date);
	
	@Query("SELECT t FROM ResturantTable t WHERE t.id =:id")
	public ResturantTable getTableById(@Param("id") int id);
}

