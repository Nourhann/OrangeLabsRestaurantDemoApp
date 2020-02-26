package com.restaurant.reservation.restaurant;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.restaurant.reservation.controller.ReservationController;
import com.restaurant.reservation.entity.Reservation;
import com.restaurant.reservation.entity.ResturantTable;
import com.restaurant.reservation.service.ReservationService;
import com.restaurant.reservation.service.TableService;

@RunWith(SpringRunner.class)
@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {

	    @Autowired
	    private MockMvc mvc;
	 
	    @MockBean
	    private ReservationService reservationService;
	    
	    @MockBean
	    private TableService tableService;
	    
	    @Test
	    public void givenEmployees_whenGetEmployees_thenReturnJsonArray()
	      throws Exception {
	         
	    	ResturantTable table = new ResturantTable(5);
	    	Date date = new Date();
	    	String customerName = "Ahmed";
	    	double paymentAmount = 1200.0;
	    	int numOfPersons = 4;
	    	
         	reservationService.reserveTable(table, customerName, paymentAmount, date, numOfPersons);
	        //given(reservationService.getAllReservations()).willReturn(allReservations);
	     
	        mvc.perform(get("/reservations/")
	          .contentType(MediaType.APPLICATION_JSON))
	          .andExpect(status().isOk())
	          .andExpect(jsonPath("$[0].customerName", is(customerName)))
	          .andExpect(jsonPath("$[0].paymentAmount", is(paymentAmount)))
	          .andExpect(jsonPath("$[0].numOfPersons", is(numOfPersons)))
	          .andExpect(jsonPath("$[0].reservationDate", is(date)))
	          .andExpect(jsonPath("$[0].table.capacity", is(table.getCapacity())));
	    }
}
