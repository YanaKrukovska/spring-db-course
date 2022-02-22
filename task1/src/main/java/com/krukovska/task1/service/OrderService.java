package com.krukovska.task1.service;

import com.krukovska.task1.persistence.model.Order;

import java.util.List;

public interface OrderService {

    List<Order> findAll();

    Order getById(long id);
}
