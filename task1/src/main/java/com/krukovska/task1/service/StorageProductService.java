package com.krukovska.task1.service;

import com.krukovska.task1.persistence.model.StorageProduct;

import java.util.List;

public interface StorageProductService {

    List<StorageProduct> findAll();
}
