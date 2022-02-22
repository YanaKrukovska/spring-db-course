package com.krukovska.task1.controller;

import com.krukovska.task1.persistence.model.Order;
import com.krukovska.task1.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService service;

    @Autowired
    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Order>> findAll() {
        List<Order> result = service.findAll();
        log.info("Found orders, list size= {}", result.size());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable Long orderId) {
        Order order = service.getById(orderId);
        log.info("Found order {}", order);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
