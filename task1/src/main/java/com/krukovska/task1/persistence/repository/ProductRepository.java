package com.krukovska.task1.persistence.repository;

import com.krukovska.task1.persistence.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findById(long id);

}
