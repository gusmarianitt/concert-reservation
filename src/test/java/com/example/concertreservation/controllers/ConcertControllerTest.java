package com.example.concertreservation.controllers;

import com.example.concertreservation.models.Concert;
import com.example.concertreservation.repositories.ConcertRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class ConcertControllerTest {
    @Mock
    ConcertRepository concertRepository;

    @InjectMocks
    ConcertController concertController;

    Concert concert1, concert2, concert3;

    List<Concert> concertList;

    @BeforeEach
    void setData(){
        concert1 = new Concert();
        concert1.setConcertId(1L);
        concert1.setConcertCd("concert1");
        concert1.setConcertName("test");
        concert1.setTotalSeat(100);

        concert2 = new Concert();
        concert2.setConcertId(2L);
        concert2.setConcertCd("concert2");
        concert2.setConcertName("test2");
        concert2.setTotalSeat(150);

        concert3 = new Concert();
        concert3.setConcertId(3L);
        concert3.setConcertCd("concert3");
        concert3.setConcertName("test3");
        concert3.setTotalSeat(110);

        concertList = new ArrayList<>();
        concertList.add(concert1);
        concertList.add(concert2);

        when(concertRepository.findAll()).thenReturn(concertList);
        when(concertRepository.getReferenceById(1L)).thenReturn(concert1);
        when(concertRepository.getReferenceById(2L)).thenReturn(concert2);
        when(concertRepository.saveAndFlush(any(Concert.class))).thenReturn(concert3);
    }

    @Test
    void testList(){
        List<Concert> res = concertController.list();
        assertEquals(concertList, res);
    }

    @Test
    void testGetById(){
        Concert res = concertController.get(1L);
        assertEquals(concert1, res);
    }

    @Test
    void testCreate(){
        Concert res = concertController.create(concert3);
        assertEquals(concert3, res);
    }
}
