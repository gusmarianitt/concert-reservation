package com.example.concertreservation.controllers;

import com.example.concertreservation.models.Booking;
import com.example.concertreservation.models.Concert;
import com.example.concertreservation.repositories.BookingRepository;
import com.example.concertreservation.repositories.ConcertRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
@Slf4j
public class BookingController {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ConcertRepository concertRepository;

    @GetMapping
    public List<Booking> list(){
        return bookingRepository.findAll();
    }

    @GetMapping("{id}")
    public Booking get(@PathVariable Long id) {
        return bookingRepository.getReferenceById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Booking create(@RequestBody final Booking booking){
        Booking booking1 = new Booking();
        Concert concert = booking.getConcert();
        Long concertId = concert.getConcertId();
        int seatBooked = booking.getSeatBooked();

        log.debug("concertId : "+concertId);

        concert = concertRepository.getReferenceById(concertId);

        //race booking
        if (seatBooked > concert.getTotalSeat()){
            log.error("seat not available");
        } else {
            booking.setBookingStatus("Pending");
            booking1 = bookingRepository.saveAndFlush(booking);

            int newTotalSeat = concert.getTotalSeat() - seatBooked;
            Concert newConcert = new Concert();
            newConcert.setTotalSeat(newTotalSeat);
            newConcert.setConcertId(concertId);
            newConcert.setConcertCd(concert.getConcertCd());
            newConcert.setConcertName(concert.getConcertName());

            log.debug("newConcert.setTotalSeat : "+newTotalSeat);
            log.debug("newConcert.setConcertId : "+concertId);

            BeanUtils.copyProperties(newConcert, concert, "concert_id");
            concertRepository.saveAndFlush(concert);
        }

        return booking1;
    }

    @PutMapping(value = "{id}")
    public Booking update(@PathVariable Long id, @RequestBody Booking booking){
        Booking existingBooking = bookingRepository.getReferenceById(id);

        Booking newBook = new Booking();
        newBook.setUsr(existingBooking.getUsr());
        newBook.setConcert(existingBooking.getConcert());
        newBook.setSeatBooked(existingBooking.getSeatBooked());
        newBook.setBookingId(id);
        newBook.setBookingStatus(booking.getBookingStatus());

        log.debug("id : "+id);
        log.debug("seatBooked : "+existingBooking.getSeatBooked());

        BeanUtils.copyProperties(newBook, existingBooking, "booking_id");
        Booking booking1 = bookingRepository.saveAndFlush(existingBooking);

        if (!booking.getBookingStatus().equalsIgnoreCase("sukses")){
            booking1 = bookingRepository.saveAndFlush(existingBooking);

            Concert concert = existingBooking.getConcert();
            Long concertId = concert.getConcertId();

            int seatBooked = existingBooking.getSeatBooked();

            concert = concertRepository.getReferenceById(concertId);

            int newTotalSeat = concert.getTotalSeat() + seatBooked;
            Concert newConcert = new Concert();
            newConcert.setTotalSeat(newTotalSeat);
            newConcert.setConcertId(concertId);
            newConcert.setConcertCd(concert.getConcertCd());
            newConcert.setConcertName(concert.getConcertName());

            log.debug("newConcert.setTotalSeat : "+newTotalSeat);
            log.debug("newConcert.setConcertId : "+concertId);

            BeanUtils.copyProperties(newConcert, concert, "concert_id");
            concertRepository.saveAndFlush(concert);
        }

        return booking1;
    }
}
