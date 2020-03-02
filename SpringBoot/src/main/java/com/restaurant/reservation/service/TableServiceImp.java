package com.restaurant.reservation.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.reservation.dao.TableRepository;
import com.restaurant.reservation.entity.ResturantTable;

@Service
public class TableServiceImp implements TableService {

	private TableRepository tableRepository;
	
	@Autowired
	public TableServiceImp(TableRepository tableRepository){
		this.tableRepository = tableRepository;
	}
	
	@Override
	public List<ResturantTable> findAll() {
		return tableRepository.findAll();
	}

	@Override
	public ResturantTable add(int capacity) {
		ResturantTable table = new ResturantTable();
		table.setCapacity(capacity);	
		return tableRepository.save(table);
	}

	@Override
	public void deleteById(int tableId) {
		tableRepository.deleteById(tableId);
	}

	@Override
	public List<ResturantTable> getAvailableTables(Date date) {
		return tableRepository.getAvailableTables(date);
	}

	@Override
	public ResturantTable findTableById(int tableId) {
		return tableRepository.getTableById(tableId);
	}

	@Override
	public boolean checkCapacityForTable(int tableId, int capacity) {
		ResturantTable table = this.findTableById(tableId);
		if(table.getCapacity() < capacity ){
			return false;
		}
		return true;
	}
	
	@Override
	public boolean checkTableAvailability(ResturantTable table , Date date){
		List<ResturantTable> tables = this.getAvailableTables(date);
		return tables.contains(table);
	}

}
