package com.krukovska.springdbcourse.service;

import com.krukovska.springdbcourse.persistence.model.StorageProduct;

import java.util.List;

public interface StorageProductService {

    List<StorageProduct> findAll();
}
