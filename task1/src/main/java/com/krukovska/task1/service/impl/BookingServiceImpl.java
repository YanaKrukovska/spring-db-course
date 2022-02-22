package com.krukovska.task1.service.impl;

import com.krukovska.task1.persistence.model.Booking;
import com.krukovska.task1.persistence.repository.BookingRepository;
import com.krukovska.task1.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository repository;

    @Autowired
    public BookingServiceImpl(BookingRepository repository) {
        this.repository = repository;
    }

    @Override
    public Booking create(Booking booking) {
        return repository.save(booking);
    }

    @Override
    public List<Booking> findAll() {
        return repository.findAll();
    }
}
