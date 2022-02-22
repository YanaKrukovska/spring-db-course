package com.krukovska.task1.persistence.repository;

import com.krukovska.task1.persistence.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findById(long id);
}
