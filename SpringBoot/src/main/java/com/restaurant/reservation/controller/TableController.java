package com.restaurant.reservation.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.reservation.entity.ResturantTable;
import com.restaurant.reservation.response.MessageResponse;
import com.restaurant.reservation.service.TableService;

@RestController
@RequestMapping("/tables")
public class TableController {

	@Autowired
	TableService tableService;

	@GetMapping("/")
	public ResponseEntity<List<ResturantTable>> findAll() {
		List<ResturantTable> tables = tableService.findAll();
		return new ResponseEntity<List<ResturantTable>>(tables, HttpStatus.OK);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity add(@RequestParam("capacity") int capacity) {
		if (capacity < 1 || capacity > 100) {
			return getResponse("InvalidCapacity");
		}
		tableService.add(capacity);
		return getResponse("TableAded");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity deleteById(@PathVariable("id") int tableId) {

		if (tableId > 0) {
			ResturantTable tbl = tableService.findTableById(tableId);
			if (tbl != null) {
				tableService.deleteById(tableId);

				return getResponse("TableDeleted");
			} else {

				return getResponse("TableNotFound");
			}

		} else {

			return getResponse("InvalidId");
		}
	}

	@RequestMapping(value = "/available/{date}", method = RequestMethod.GET)
	public ResponseEntity<List<ResturantTable>> getAvailableTables(
			@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
		List<ResturantTable> tables = tableService.getAvailableTables(date);
		return new ResponseEntity<List<ResturantTable>>(tables, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ResturantTable> findTableById(@PathVariable("id") int tableId) {
		ResturantTable table = tableService.findTableById(tableId);
		return new ResponseEntity<ResturantTable>(table, HttpStatus.OK);
	}

	public ResponseEntity getResponse(String msg) {
		MessageResponse messageResponse = new MessageResponse();
		if (msg.equalsIgnoreCase("InvalidCapacity")) {
			messageResponse.setMessage("Invalid capacity number");
			return new ResponseEntity<>(messageResponse, HttpStatus.OK);
		} else if (msg.equalsIgnoreCase("TableAded")) {
			messageResponse.setMessage("Table added successfully!");
			return new ResponseEntity<>(messageResponse, HttpStatus.OK);
		} else if (msg.equalsIgnoreCase("TableDeleted")) {
			messageResponse.setMessage("Table deleted successfully!");
			return new ResponseEntity<>(messageResponse, HttpStatus.OK);
		} else if (msg.equalsIgnoreCase("TableNotFound")) {
			messageResponse.setMessage("Table Not Found");
			return new ResponseEntity<>(messageResponse, HttpStatus.OK);
		} else if (msg.equalsIgnoreCase("InvalidId")) {
			messageResponse.setMessage("Invalid table id");
			return new ResponseEntity<>(messageResponse, HttpStatus.OK);
		} else {
			messageResponse.setMessage("Can not get table");
			return new ResponseEntity<>(messageResponse, HttpStatus.OK);
		}

	}
}
