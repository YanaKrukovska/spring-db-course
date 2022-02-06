package com.krukovska.springdbcourse.service.impl;

import com.krukovska.springdbcourse.persistence.repository.StorageRepository;
import com.krukovska.springdbcourse.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StorageServiceImpl implements StorageService {

    private final StorageRepository repository;

    @Autowired
    public StorageServiceImpl(StorageRepository repository) {
        this.repository = repository;
    }
}
