package com.krukovska.springdbcourse.service.impl;

import com.krukovska.springdbcourse.persistence.model.Booking;
import com.krukovska.springdbcourse.persistence.repository.BookingRepository;
import com.krukovska.springdbcourse.service.BookingService;
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
