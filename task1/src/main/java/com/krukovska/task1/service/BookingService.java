package com.krukovska.task1.service;

import com.krukovska.task1.persistence.model.Booking;

import java.util.List;

public interface BookingService {

    Booking create(Booking booking);

    List<Booking> findAll();
}
