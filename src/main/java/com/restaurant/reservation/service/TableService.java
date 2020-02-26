package com.restaurant.reservation.service;
import java.util.Date;
import java.util.List;

import com.restaurant.reservation.entity.ResturantTable;


public interface TableService {
       
	public List<ResturantTable> findAll();
	
	public ResturantTable findTableById(int tableId);
	
	public ResturantTable add(int capacity);
	
	public void deleteById(int tableId);
	
	public List<ResturantTable> getAvailableTables(Date date);
	
	public boolean checkCapacityForTable(int tableId , int capacity);
	
	public boolean checkTableAvailability(ResturantTable table , Date date);
	
}
