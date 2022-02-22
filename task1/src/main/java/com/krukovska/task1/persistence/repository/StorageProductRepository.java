package com.krukovska.task1.persistence.repository;

import com.krukovska.task1.persistence.model.StorageProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StorageProductRepository extends JpaRepository<StorageProduct, Long> {
}
