package com.krukovska.springdbcourse.service;

import com.krukovska.springdbcourse.persistence.model.Product;

import java.util.List;

public interface ProductService {

    Product getById(long id);

    List<Product> findAll();
}
