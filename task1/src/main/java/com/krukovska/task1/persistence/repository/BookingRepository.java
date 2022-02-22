package com.krukovska.task1.persistence.repository;

import com.krukovska.task1.persistence.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
