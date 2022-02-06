package com.krukovska.springdbcourse.service;

import com.krukovska.springdbcourse.persistence.model.Order;

import java.util.List;

public interface OrderService {

    List<Order> findAll();

    Order getById(long id);
}
