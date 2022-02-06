package com.krukovska.springdbcourse.service;

import com.krukovska.springdbcourse.persistence.model.Booking;

import java.util.List;

public interface BookingService {

    Booking create(Booking booking);

    List<Booking> findAll();
}
