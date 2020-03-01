package com.restaurant.reservation.controllerTest;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Date;

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

import com.restaurant.reservation.controller.TableController;
import com.restaurant.reservation.entity.ResturantTable;
import com.restaurant.reservation.service.TableService;



@RunWith(SpringRunner.class)
@WebMvcTest(TableController.class)
public class TableControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private TableService tableService;

	@Test
	public void getTableByIdt() throws Exception {
		when(tableService.findTableById(5)).thenReturn(
				new ResturantTable(2,5));
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/tables/5")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().json("{id:2,capacity:5}"))
				.andReturn();
		//String expected;
		//JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
		
	}
	
	@Test
	public void getAllTables() throws Exception {
		when(tableService.findAll()).thenReturn(
				Arrays.asList(new ResturantTable(2,5),
						new ResturantTable(3,6))
				);
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/tables/")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().json("[{id:2,capacity:5}, {id:3,capacity:6}]"))
				.andReturn();
		//JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
		
	}
	@Test
	public void getAvailableTables() throws Exception {
		Date date = new Date();
		when(tableService.getAvailableTables(date)).thenReturn(
				Arrays.asList(new ResturantTable(2,5),
						new ResturantTable(3,6))
				);
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/tables/available/"+date.toString())
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().json("[{id:2,capacity:5}, {id:3,capacity:6}]"))
				.andReturn();
		//JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
		
	}
}