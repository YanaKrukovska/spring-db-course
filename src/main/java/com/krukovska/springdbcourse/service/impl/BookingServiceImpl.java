package com.krukovska.springdbcourse.service.impl;

import com.krukovska.springdbcourse.persistence.repository.BookingRepository;
import com.krukovska.springdbcourse.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository repository;

    @Autowired
    public BookingServiceImpl(BookingRepository repository) {
        this.repository = repository;
    }
}
