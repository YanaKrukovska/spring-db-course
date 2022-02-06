package com.krukovska.springdbcourse.service.impl;

import com.krukovska.springdbcourse.persistence.repository.OrderDetailRepository;
import com.krukovska.springdbcourse.service.OrderDetailService;
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
