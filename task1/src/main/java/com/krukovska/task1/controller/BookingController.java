package com.krukovska.task1.controller;

import com.krukovska.task1.persistence.model.Booking;
import com.krukovska.task1.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/booking")
public class BookingController {

    private final BookingService service;

    @Autowired
    public BookingController(BookingService service) {
        this.service = service;
    }

    @PostMapping("/")
    public ResponseEntity<Booking> create(@RequestBody Booking booking) {
        Booking createdBooking = service.create(booking);
        log.info("Created new booking {}", booking);
        return new ResponseEntity<>(createdBooking, HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Booking>> findAll() {
        List<Booking> bookings = service.findAll();
        log.info("Found bookings, list size= {}", bookings.size());
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }
}
