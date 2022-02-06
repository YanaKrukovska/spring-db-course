package com.krukovska.springdbcourse.persistence.repository;

import com.krukovska.springdbcourse.persistence.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
