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

import com.restaurant.reservation.entity.Reservation;
import com.restaurant.reservation.entity.ResturantTable;
import com.restaurant.reservation.response.MessageResponse;
import com.restaurant.reservation.service.ReservationService;
import com.restaurant.reservation.service.TableService;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private TableService tableService;
	
	@GetMapping("/")
	public ResponseEntity<List<Reservation>> getAllReservations() {
		List<Reservation> reservations = reservationService.getAllReservations();
		return new ResponseEntity<List<Reservation>>(reservations,HttpStatus.OK);
	}

	@RequestMapping(value = "/reserve", method = RequestMethod.POST)
	public ResponseEntity reserveTable(@RequestParam("tableId") int tableId,
			@RequestParam("customerName") String customerName, @RequestParam("paymentAmount") double paymentAmount,
			@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
			@RequestParam("numOfPersons") int numOfPersons) {
		ResturantTable table = tableService.findTableById(tableId);
		if (table != null) {
			if (table.getCapacity() > numOfPersons) {
				if (tableService.checkTableAvailability(table, date)) {
					reservationService.reserveTable(table, customerName, paymentAmount, date, numOfPersons);
					return getResponse("Reserved");
				} else {

					return getResponse("NotAvailable");
				}
			} else {

				return getResponse("CapacityNotEnough");
			}
		} else {

			return getResponse("TableNotFound");
		}

	}

	@RequestMapping(value = "/{date}",method = RequestMethod.GET)
	public ResponseEntity<List<Reservation>> getReservationByDate(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
		List<Reservation> reservations = reservationService.getReservationByDate(date);
		return new ResponseEntity<List<Reservation>>(reservations,HttpStatus.OK);
	}
	
	public ResponseEntity getResponse(String msg){
		MessageResponse messageResponse = new MessageResponse();
		if(msg.equalsIgnoreCase("Reserved")){
			messageResponse.setMessage("Table is reserved successfully");
			return new ResponseEntity<>(messageResponse,HttpStatus.OK); 
		}
		else if(msg.equalsIgnoreCase("NotAvailable")){
			messageResponse.setMessage("Table is not available");
			return new ResponseEntity<>(messageResponse,HttpStatus.OK);
		}
		else if(msg.equalsIgnoreCase("CapacityNotEnough")){
			messageResponse.setMessage("The number of people is greater than the table capacity");
			return new ResponseEntity<>(messageResponse,HttpStatus.OK);
		}
		else if(msg.equalsIgnoreCase("TableNotFound")){
			messageResponse.setMessage("Table Not Found");
			return new ResponseEntity<>(messageResponse,HttpStatus.OK);
		}
		else {
			messageResponse.setMessage("Can NOT Reserve");
			return new ResponseEntity<>(messageResponse,HttpStatus.OK);
		}
		
	}
}
