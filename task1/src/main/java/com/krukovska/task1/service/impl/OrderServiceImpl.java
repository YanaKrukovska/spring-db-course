package com.krukovska.task1.service.impl;

import com.krukovska.task1.persistence.model.Order;
import com.krukovska.task1.persistence.repository.OrderRepository;
import com.krukovska.task1.service.OrderService;
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
