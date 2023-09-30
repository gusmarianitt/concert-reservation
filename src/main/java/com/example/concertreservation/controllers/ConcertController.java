package com.example.concertreservation.controllers;

import com.example.concertreservation.models.Concert;
import com.example.concertreservation.repositories.ConcertRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/concert")
@Slf4j
public class ConcertController {
    @Autowired
    private ConcertRepository concertRepository;

    @GetMapping
    public List<Concert> list(){
        return concertRepository.findAll();
    }

    @GetMapping("{id}")
    public Concert get(@PathVariable Long id) {
        return concertRepository.getReferenceById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Concert create(@RequestBody final Concert concert){
        return concertRepository.saveAndFlush(concert);
    }

    @PutMapping(value = "{id}")
    public Concert update(@PathVariable Long id, @RequestBody Concert concert){
        Concert existingConcert = concertRepository.getReferenceById(id);
        BeanUtils.copyProperties(concert, existingConcert, "concert_id");
        return concertRepository.saveAndFlush(existingConcert);
    }
}
