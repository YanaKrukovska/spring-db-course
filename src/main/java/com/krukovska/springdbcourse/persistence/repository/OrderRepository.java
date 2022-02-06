package com.krukovska.springdbcourse.persistence.repository;

import com.krukovska.springdbcourse.persistence.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
