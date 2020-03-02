package com.restaurant.reservation.serviceTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.restaurant.reservation.dao.TableRepository;
import com.restaurant.reservation.entity.ResturantTable;
import com.restaurant.reservation.service.TableService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class TableServiceTest {

    @Mock
    private TableRepository tableRepository;

    @InjectMocks
    TableService tableService;


    @Test
    public void testGetAllTables() {
        List<ResturantTable> tables = new ArrayList<ResturantTable>();
        tables.add(new ResturantTable(4));
        tables.add(new ResturantTable(5));
        tables.add(new ResturantTable(3));
        when(tableRepository.findAll()).thenReturn(tables);

        List<ResturantTable> result = tableService.findAll();
        assertEquals(3, result.size());
    }

    @Test
    public void testGetTableById() {
    	ResturantTable table = new ResturantTable(10,8);
        when(tableRepository.findById(8)).thenReturn(Optional.of(table));
        ResturantTable resultTable = tableService.findTableById(10);
        assertEquals(10, resultTable.getId());
        assertEquals(8, resultTable.getCapacity());
    }

    @Test
    public void saveTable() {
    	ResturantTable table = new ResturantTable(8);
        when(tableRepository.save(table)).thenReturn(table);
        ResturantTable result = tableService.add(8);
        assertEquals(8, result.getCapacity());
    }

    @Test
    public void removeTable() {
    	ResturantTable table = new ResturantTable(12,8);
    	tableService.deleteById(12);
        verify(tableRepository, times(1)).delete(table);
    }


}