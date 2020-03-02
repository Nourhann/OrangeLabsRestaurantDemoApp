package com.restaurant.reservation.controllerTest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.restaurant.reservation.controller.ReservationController;
import com.restaurant.reservation.entity.Reservation;
import com.restaurant.reservation.entity.ResturantTable;
import com.restaurant.reservation.service.ReservationService;
import com.restaurant.reservation.service.TableService;

@RunWith(SpringRunner.class)
@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TableService tableService;

	@MockBean
	private ReservationService reservationService;

	@Test
	public void getReservationByDate() throws Exception {
		ResturantTable table = new ResturantTable(5,8);
    	Date date = new Date();
    	List<Reservation> reservations = new ArrayList<Reservation>();
        reservations.add(new Reservation(5,"Ahmed", 1000, date, 3, table));
        when(reservationService.getReservationByDate(date)).thenReturn(reservations);
	
		RequestBuilder request = MockMvcRequestBuilders.get("/tables/"+date.toString()).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().json("{id:5,customerName:Ahmed,paymentAmount:1000,reservationDate:"
						+ date+"numOfPersons:3,table{id:5,"
								+ "capacity:8}}")).andReturn();
		// String expected;
		// JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(),
		// false);

	}
	
	@Test
	public void reserveTable_AvailableCase() throws Exception {
		ResturantTable table = new ResturantTable(5,5);
		Date date = new Date();
		Reservation resrvation = new Reservation(4,"Ahmed", 1000, date, 3, table);
		when(reservationService.reserveTable(table, "Ahmed", 
				1000, date, 2)).thenReturn(
				resrvation);
		
		RequestBuilder request = MockMvcRequestBuilders.post("/reservations/reserve/")
	    .contentType(MediaType.APPLICATION_JSON)
	    .content(reservationObjectToJson(resrvation,table));

		MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();
		// JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(),
		// false);

	}
	@Test
	public void reserveTable_NotavailableCase() throws Exception {
		ResturantTable table = new ResturantTable(5,5);
		Date date = new Date();
		Reservation resrvation = new Reservation(4,"Ahmed", 1000, date, 3, table);
		when(reservationService.reserveTable(table, "Ahmed", 
				1000, date, 2)).thenReturn(null);
		
		RequestBuilder request = MockMvcRequestBuilders.post("/reservations/reserve/")
	    .contentType(MediaType.APPLICATION_JSON)
	    .content(reservationObjectToJson(resrvation,table));

		MvcResult result = mockMvc.perform(request).andExpect(content().json("Table is not available")).andReturn();
		// JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(),
		// false);

	}
	@Test
	public void reserveTable_CapacityNotEnoughCase() throws Exception {
		ResturantTable table = new ResturantTable(5,2);
		Date date = new Date();
		Reservation resrvation = new Reservation(4,"Ahmed", 1000, date, 3, table);
		
		RequestBuilder request = MockMvcRequestBuilders.post("/reservations/reserve/")
	    .contentType(MediaType.APPLICATION_JSON)
	    .content(reservationObjectToJson(resrvation,table));

		MvcResult result = mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().json("{The number of people is greater than the table capacity}"))
				.andReturn();
		// JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(),
		// false);

	}
	
	private String reservationObjectToJson(Reservation reservation , ResturantTable table) {
		return "{id:"+reservation.getId()+",customerName:"+reservation.getCustomerName()+""
				+ ",paymentAmount:"+reservation.getPaymentAmount()
			    + ",reservationDate:"+reservation.getReservationDate()
				+ ",numOfPersons:"+reservation.getNumOfPersons()
				+ "table{id:"+table.getId()
				+ "capacity:"+table.getCapacity()+"}}";
	}
	
}