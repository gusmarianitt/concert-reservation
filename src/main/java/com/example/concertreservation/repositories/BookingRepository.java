package com.example.concertreservation.repositories;

import com.example.concertreservation.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
