package com.krukovska.springdbcourse.service.impl;

import com.krukovska.springdbcourse.persistence.model.StorageProduct;
import com.krukovska.springdbcourse.persistence.repository.StorageProductRepository;
import com.krukovska.springdbcourse.service.StorageProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorageProductServiceImpl implements StorageProductService {

    private final StorageProductRepository repository;

    @Autowired
    public StorageProductServiceImpl(StorageProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<StorageProduct> findAll() {
        return repository.findAll();
    }
}
