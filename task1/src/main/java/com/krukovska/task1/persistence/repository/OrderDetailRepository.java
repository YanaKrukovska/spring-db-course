package com.krukovska.task1.persistence.repository;

import com.krukovska.task1.persistence.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
