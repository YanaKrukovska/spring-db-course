package com.krukovska.springdbcourse.persistence.repository;

import com.krukovska.springdbcourse.persistence.model.StorageProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StorageProductRepository extends JpaRepository<StorageProduct, Long> {
}
