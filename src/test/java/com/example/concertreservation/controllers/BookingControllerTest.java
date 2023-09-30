package com.example.concertreservation.controllers;

import com.example.concertreservation.models.Booking;
import com.example.concertreservation.models.Concert;
import com.example.concertreservation.models.Usr;
import com.example.concertreservation.repositories.BookingRepository;
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
class BookingControllerTest {
    @Mock
    BookingRepository bookingRepository;

    @Mock
    ConcertRepository concertRepository;

    @InjectMocks
    BookingController bookingController;

    Booking booking1, booking2, booking3;
    Concert concert1, concert2, concert3;

    List<Booking> bookingList;

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

        Usr usr = new Usr();
        usr.setUserId(1L);
        usr.setUserName("user1");
        usr.setUserEmail("user1@gmail.com");

        booking1 = new Booking();
        booking1.setBookingId(1L);
        booking1.setConcert(concert1);
        booking1.setUsr(usr);
        booking1.setSeatBooked(1);
        booking1.setBookingStatus("sukses");

        booking2 = new Booking();
        booking2.setBookingId(2L);
        booking2.setConcert(concert2);
        booking2.setUsr(usr);
        booking2.setSeatBooked(2);
        booking1.setBookingStatus("sukses");

        booking3 = new Booking();
        booking3.setBookingId(3L);
        booking3.setConcert(concert3);
        booking3.setUsr(usr);
        booking3.setSeatBooked(1);
        booking3.setBookingStatus("sukses");

        bookingList = new ArrayList<>();
        bookingList.add(booking1);
        bookingList.add(booking2);
        bookingList.add(booking3);

        when(bookingRepository.findAll()).thenReturn(bookingList);
        when(bookingRepository.getReferenceById(1L)).thenReturn(booking1);
        when(bookingRepository.getReferenceById(2L)).thenReturn(booking2);
        when(bookingRepository.getReferenceById(3L)).thenReturn(booking3);
        when(concertRepository.getReferenceById(1L)).thenReturn(concert1);
        when(concertRepository.getReferenceById(2L)).thenReturn(concert2);
        when(concertRepository.getReferenceById(3L)).thenReturn(concert3);
        when(concertRepository.saveAndFlush(any(Concert.class))).thenReturn(concert3);
        when(bookingRepository.saveAndFlush(any(Booking.class))).thenReturn(booking3);

    }

    @Test
    void testList(){
        List<Booking> res = bookingController.list();
        assertEquals(bookingList, res);
    }

    @Test
    void testGetById(){
        Booking res = bookingController.get(1L);
        assertEquals(booking1, res);
    }

    @Test
    void testCreate(){
        Booking res = bookingController.create(booking3);
        assertEquals(booking3, res);
    }

    @Test
    void testUpdate(){
        Booking res = bookingController.update(3L, booking3);
        assertEquals(booking3, res);
    }
}
