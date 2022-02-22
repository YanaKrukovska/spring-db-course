package com.krukovska.task1.service.impl;

import com.krukovska.task1.persistence.model.Storage;
import com.krukovska.task1.persistence.repository.StorageRepository;
import com.krukovska.task1.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorageServiceImpl implements StorageService {

    private final StorageRepository repository;

    @Autowired
    public StorageServiceImpl(StorageRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Storage> findAll() {
        return repository.findAll();
    }
}
