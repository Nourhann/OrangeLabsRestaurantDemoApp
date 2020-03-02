package com.restaurant.reservation.serviceTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.restaurant.reservation.dao.ReservationRepository;
import com.restaurant.reservation.entity.Reservation;
import com.restaurant.reservation.entity.ResturantTable;
import com.restaurant.reservation.service.ReservationService;
import com.restaurant.reservation.service.TableService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    TableService tableService;

    @InjectMocks
    ReservationService reservationService;


    @Test
    public void testGetAllReservations() {
        ResturantTable table1 = new ResturantTable(5);
        ResturantTable table2 = new ResturantTable(6);
        List<Reservation> reservations = new ArrayList<Reservation>();
        reservations.add(new Reservation("Ahmed", 1000, new Date(), 3, table1));
        reservations.add(new Reservation("Mohamed", 1000, new Date(), 3, table2));
        when(reservationRepository.findAll()).thenReturn(reservations);

        List<Reservation> result = reservationService.getAllReservations();
        assertEquals(2, result.size());
    }

    @Test
    public void testGetReservationByDate() {
    	ResturantTable table = new ResturantTable(8);
    	Date date = new Date();
    	List<Reservation> reservations = new ArrayList<Reservation>();
        reservations.add(new Reservation("Ahmed", 1000, date, 3, table));
        when(reservationRepository.getReservationByDate(date)).thenReturn(reservations);
        List<Reservation> resultReservations  = reservationService.getReservationByDate(date);
        assertEquals("Ahmed", resultReservations.get(0).getCustomerName());
        assertEquals(1000, resultReservations.get(0).getPaymentAmount());
        assertEquals(3, resultReservations.get(0).getNumOfPersons());
        assertEquals(8, resultReservations.get(0).getTable().getCapacity());
    }



}