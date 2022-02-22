package com.krukovska.task1.service;

import com.krukovska.task1.persistence.model.Storage;

import java.util.List;

public interface StorageService {

    List<Storage> findAll();
}
