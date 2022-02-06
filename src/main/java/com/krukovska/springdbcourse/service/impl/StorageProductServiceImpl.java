package com.krukovska.springdbcourse.service.impl;

import com.krukovska.springdbcourse.persistence.repository.StorageProductRepository;
import com.krukovska.springdbcourse.service.StorageProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StorageProductServiceImpl implements StorageProductService {

    private final StorageProductRepository repository;

    @Autowired
    public StorageProductServiceImpl(StorageProductRepository repository) {
        this.repository = repository;
    }
}
