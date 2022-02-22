package com.krukovska.task1.service.impl;

import com.krukovska.task1.persistence.repository.OrderDetailRepository;
import com.krukovska.task1.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailRepository repository;

    @Autowired
    public OrderDetailServiceImpl(OrderDetailRepository repository) {
        this.repository = repository;
    }
}
