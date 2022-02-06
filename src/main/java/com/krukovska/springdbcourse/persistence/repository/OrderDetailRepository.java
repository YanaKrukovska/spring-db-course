package com.krukovska.springdbcourse.persistence.repository;

import com.krukovska.springdbcourse.persistence.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
