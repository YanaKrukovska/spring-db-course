package com.krukovska.springdbcourse.service.impl;

import com.krukovska.springdbcourse.persistence.model.Product;
import com.krukovska.springdbcourse.persistence.repository.ProductRepository;
import com.krukovska.springdbcourse.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Autowired
    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product getById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }
}
