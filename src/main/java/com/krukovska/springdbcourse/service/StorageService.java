package com.krukovska.springdbcourse.service;

import com.krukovska.springdbcourse.persistence.model.Storage;

import java.util.List;

public interface StorageService {

    List<Storage> findAll();
}
