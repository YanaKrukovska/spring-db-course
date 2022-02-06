package com.krukovska.springdbcourse.service.impl;

import com.krukovska.springdbcourse.persistence.model.Order;
import com.krukovska.springdbcourse.persistence.repository.OrderRepository;
import com.krukovska.springdbcourse.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    @Autowired
    public OrderServiceImpl(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Order> findAll() {
        return repository.findAll();
    }

    @Override
    public Order getById(long id) {
        return repository.findById(id);
    }
}
