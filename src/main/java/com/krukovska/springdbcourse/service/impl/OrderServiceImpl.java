package com.krukovska.springdbcourse.service.impl;

import com.krukovska.springdbcourse.persistence.repository.OrderRepository;
import com.krukovska.springdbcourse.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    @Autowired
    public OrderServiceImpl(OrderRepository repository) {
        this.repository = repository;
    }
}
