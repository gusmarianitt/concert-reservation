package com.example.concertreservation.repositories;

import com.example.concertreservation.models.Concert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertRepository extends JpaRepository<Concert, Long> {
}
