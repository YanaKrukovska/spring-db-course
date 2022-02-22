package com.krukovska.task1.service;

import com.krukovska.task1.persistence.model.Product;

import java.util.List;

public interface ProductService {

    Product getById(long id);

    List<Product> findAll();
}
